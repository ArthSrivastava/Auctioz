import {
  Card,
  Input,
  Typography,
  Button,
  Select,
  Option,
} from "@material-tailwind/react";
import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import Base from "../components/Base";
import { retrieveAllCategories } from "../services/CategoryService";

const ListProduct = () => {
  const [productData, setProductData] = useState({});
  const [biddingData, setBiddingData] = useState({});
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    retrieveAllCategories()
      .then((categoryData) => {
        console.log("Categories:", categoryData);
        setCategories(categoryData);
      })
      .catch((error) => {
        toast.error("Failed to fetch the categories!");
      });
  }, []);

  const handleFormChange = (event) => {
    setProductData({
      ...productData,
      [event.target.name]: event.target.value,
    });
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();

    //TODO: add sellerId also to the data
    //TODO: make a call to backend to save the data here
    console.log("final object:", productData);
  };

  const listingForm = () => {
    return (
      <Card
        color="transparent"
        className="w-[30vw] border-2 border-limeShade p-4 text-[#080808] rounded-2xl drop-shadow-lg flex items-center bg-[#e2e2e2]"
      >
        {console.log(productData)}
        <Typography variant="h1">List product</Typography>
        <Typography className="mt-2 font-normal" variant="h4">
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
            <Input
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
              onChange={handleFormChange}
              name="startBidPrice"
            />
            <Input
              size="lg"
              color="teal"
              label="Deadline"
              className="text-lg"
              onChange={handleFormChange}
              name="deadline"
            />
            <Select
              label="Select Category"
              color="teal"
              name="categoryId"
              onChange={handleFormChange}
            >
              {
              categories?.map((category) => {
                        return (
                          <Option
                            key={category.id}
                            value={category.id}
                          >
                            {category.name}
                          </Option>
                        );
                      })
              }
            </Select>
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
      <div className="bg-limeShade h-[91vh] flex justify-center items-center">
        {listingForm()}
      </div>
    </Base>
  );
};

export default ListProduct;
