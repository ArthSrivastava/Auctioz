import {
  Card,
  Input,
  Typography,
  Button,
  Textarea,
} from "@material-tailwind/react";
import React, { useContext, useEffect, useState } from "react";
import { toast } from "react-toastify";
import Base from "../components/Base";
import ParticleBackground from "../components/ParticleBackground";
import { retrieveAllCategories } from "../services/CategoryService";
import { uploadImage } from "../services/ImageService";
import { listProduct } from "../services/ProductService";
import { createSellerBidding } from "../services/SellerBiddingService";
import otherParticleConfig from "../components/config/other-particle-config";
import { UserContext } from "../contexts/UserContext";
import { regexValidation, serverSideErrors } from "../services/ValidationMethods";
import { useNavigate } from "react-router-dom";

const ListProduct = () => {
  const [productData, setProductData] = useState({
    name: "",
    description: "",
    categoryId: ""
  });
  const [biddingData, setBiddingData] = useState({
    startBidPrice: "",
    deadline: ""
  });
  const [categories, setCategories] = useState([]);
  const { currentUserData } = useContext(UserContext);
  const [image, setImage] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    populateCategory();
  }, []);


  const populateCategory = async () => {
    try {
      const categoryData = await retrieveAllCategories();
      setCategories(categoryData.data);
    } catch {
      toast.error("Failed to fetch the categories!");
    }
  };

  const handleFormChange = (event) => {
    setProductData({
      ...productData,
      [event.target.name]: event.target.value,
    });
  };

  const handleFileChange = (event) => {
    if (!event.target.files[0].type.startsWith("image")) {
      toast.error("Please select an image file!");
      return;
    }
    setImage(event.target.files[0]);
  };

  const handleBiddingFormData = (event) => {
    setBiddingData({
      ...biddingData,
      [event.target.name]: event.target.value,
    });
  };

  const validateProductDataObj = () => {
    let errMessage = "";
    if (productData.name.length < 3 || productData.name.length > 40) {
      errMessage = "Name must be of length 3 to 40 characters!";
    } else if (productData.description.length < 15 || productData.description.length > 2000) {
      errMessage = "Description must be 15 to 2000 characters long!";
    } else if(productData.categoryId === "") {
      errMessage = "Select a category!";
    } else if(image === "") {
      errMessage = "Select an image for the product!";
    }
    if (errMessage !== "") {
      toast.error(errMessage);
    }
    return errMessage === "";
  };

  const validateBiddingDataObj = () => {
    let errMessage = "";
    if (!regexValidation(biddingData.startBidPrice, /^\d+$/)) {
      errMessage = "Enter valid start bid price";
    } else if (!regexValidation(biddingData.deadline, /^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2})\.(\d{3})$/)) {
      errMessage = "Enter valid deadline in ISO8601 date format!";
    }
    if (errMessage !== "") {
      toast.error(errMessage);
    }
    return errMessage === "";
  };

  const resetForm = () => {
    setProductData({
      name: "",
      description: "",
      categoryId: "",
    });

    setBiddingData({
      startBidPrice: "",
      deadline: ""
    });
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();
    if (!validateProductDataObj() || !validateBiddingDataObj()) {
      return;
    }
    listProduct(productData, currentUserData.userId)
      .then((data) => {
        createSellerBidding(biddingData, currentUserData.userId, data.id)
          .then(async (biddingResponse) => {
            try {
              const resp = await uploadImage(image, data.sellerId, data.id);
            } catch {
              toast.error("Image upload failed!");
            }
            toast.success("Product listing successful!");
          })
          .catch((error) => serverSideErrors(error));
          resetForm();
          navigate("/");
      })
      .catch((error) => serverSideErrors(error));
  };

  const listingForm = () => {
    return (
      <Card
        color="transparent"
        className="w-auto border-2 border-limeShade p-4 text-[#080808] rounded-2xl drop-shadow-lg flex items-center bg-[#ffffff]"
      >
        <Typography variant="h1" className="sm:text-3xl md:text-4xl text-xl">List product</Typography>
        <Typography className="mt-2 font-normal sm:text-2xl md:text-3xl text-lg" variant="h4">
          Enter product details
        </Typography>
        <form className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
          <div className="mb-4 flex flex-col gap-6 text-lg">
            <Input
              size="lg"
              color="teal"
              label="Name"
              className="text-lg"
              name="name"
              onChange={handleFormChange}
            />
            <Textarea
              size="lg"
              color="teal"
              label="Description"
              className="text-lg"
              onChange={handleFormChange}
              name="description"
            />
            <Input
              size="lg"
              color="teal"
              label="Start Bid Price"
              className="text-lg"
              onChange={handleBiddingFormData}
              name="startBidPrice"
            />
            <Input
              size="lg"
              color="teal"
              label="Deadline"
              className="text-lg"
              onChange={handleBiddingFormData}
              name="deadline"
            />
            <Input
              size="lg"
              color="teal"
              label="Image"
              className="text-lg"
              type="file"
              onChange={handleFileChange}
              name="image"
            />
            <select
              name="categoryId"
              onChange={handleFormChange}
              defaultValue={0}
              className="peer w-full h-full bg-transparent text-blue-gray-700 font-sans font-normal text-left outline outline-0 focus:outline-0 disabled:bg-blue-gray-50 disabled:border-0 transition-all border text-sm px-3 py-2.5 rounded-[7px] border-blue-gray-200"
            >
              <option value={0}>Select Category</option>
              {categories.map((category) => {
                return (
                  <option key={category.id} value={category.id}>
                    {category.name}
                  </option>
                );
              })}
            </select>
          </div>
          <Button
            className="mt-6 border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
            variant="outlined"
            fullWidth
            ripple={true}
            onClick={handleFormSubmit}
          >
            Submit
          </Button>
        </form>
      </Card>
    );
  };

  return (
    <Base>
      <div className=" h-screen flex justify-center items-center">
        <ParticleBackground particleOptions={otherParticleConfig}/>
        {listingForm()}
      </div>
    </Base>
  );
};

export default ListProduct;
