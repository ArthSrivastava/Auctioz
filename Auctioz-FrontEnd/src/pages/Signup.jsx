import React, { useState } from "react";
import Base from "../components/Base";
import {
  Card,
  Input,
  Typography,
  Button,
  CardHeader,
} from "@material-tailwind/react";
import { signup } from "../services/UserService";
import { createAddress } from "../services/AddressService";
import { toast } from "react-toastify";
import otherParticleConfig from "../components/config/other-particle-config";
import ParticleBackground from "../components/ParticleBackground";
import {
  regexValidation,
  serverSideErrors,
  validateEmail,
} from "../services/ValidationMethods";
import { useNavigate } from "react-router-dom";
const Signup = () => {
  //store the signup form data
  const [data, setData] = useState({
    name: "",
    email: "",
    password: "",
  });
  const [addressData, setAddressData] = useState({
    line1: "",
    line2: "",
    city: "",
    state: "",
    pincode: "",
  });

  const navigate = useNavigate();

  //handle fields like email, name, password
  const handleFormChange = (event) => {
    setData({
      ...data,
      [event.target.name]: event.target.value,
    });
  };

  //handle fields of address
  const handleAddressFieldFormChange = (event) => {
    setAddressData({
      ...addressData,
      [event.target.name]: event.target.value,
    });
  };

  //validate user data fields
  const validateDataObj = () => {
    let errMessage = "";
    if (data.name === "" || data.name.length < 3 || data.name.length > 40) {
      errMessage = "Name must be of length 3 to 40 characters!";
    } else if (data.email === "" || !validateEmail(data.email)) {
      errMessage = "Email should be in correct format!";
    } else if (data.password.length < 3 || data.password.length > 30) {
      errMessage = "Password must be 3 to 30 characters long!";
    }
    if (errMessage !== "") {
      toast.error(errMessage);
    }
    return errMessage === "";
  };

  // validate user address fields
  const validateAddressObj = () => {
    let errMessage = "";
    if (addressData.line1.length < 8 || addressData.line1.length > 60) {
      errMessage = "Address line 1 must be of length 3 to 40 characters!";
    } else if (addressData.city.length < 2 || addressData.city.length > 30) {
      errMessage = "City name must be between 2 to 30 characters long!";
    } else if (addressData.state.length < 3 || addressData.state.length > 30) {
      errMessage = "State must be 3 to 30 characters long!";
    } else if (!regexValidation(addressData.pincode, /^[1-9][0-9]{5}$/)) {
      errMessage = "Enter a valid pincode!";
    }
    if (errMessage !== "") {
      toast.error(errMessage);
    }
    return errMessage === "";
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();

    if (!validateDataObj() || !validateAddressObj()) {
      return;
    }

    signup(data)
      .then((responseData) => {
        updateAddress(responseData);
        toast.success("User registered successfully!");
        resetForm();
        navigate("/");
      })
      .catch((error) => serverSideErrors(error));
  };

  const resetForm = () => {
    setData({
      name: "",
      email: "",
      password: "",
    });

    setAddressData({
      line1: "",
      line2: "",
      city: "",
      state: "",
      pincode: "",
    });
  };

  const updateAddress = (responseData) => {
    createAddress(addressData, responseData.id)
      .then((data) => {})
      .catch((error) => serverSideErrors(error));
  };

  const signupForm = () => {
    return (
      <Card
        color="transparent"
        className="w-auto border-2 border-limeShade p-4 text-[#080808] rounded-2xl drop-shadow-lg flex items-center bg-[#ffffff]"
      >
        <Typography variant="h1" className="sm:text-3xl md:text-5xl text-xl">
          Sign Up
        </Typography>
        <Typography
          className="mt-2 font-normal sm:text-2xl md:text-4xl text-sm"
          variant="h4"
        >
          Enter your details
        </Typography>
        <form className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
          <div className="mb-4 flex flex-col gap-6 text-lg">
            <Input
              size="lg"
              color="teal"
              label="Name"
              className="sm:text-lg text-sm"
              onChange={handleFormChange}
              name="name"
              value={data.name}
            />
            <Input
              size="lg"
              color="teal"
              label="Email"
              className="text-lg text-sm"
              onChange={handleFormChange}
              name="email"
              value={data.email}
            />
            <Input
              size="lg"
              color="teal"
              label="Password"
              className="text-lg text-sm"
              type="password"
              onChange={handleFormChange}
              name="password"
              value={data.password}
            />
            <Input
              size="lg"
              color="teal"
              label="Address Line 1"
              className="text-lg text-sm"
              onChange={handleAddressFieldFormChange}
              name="line1"
              value={addressData.line1}
            />
            <Input
              size="lg"
              color="teal"
              label="Address Line 2"
              className="text-lg text-sm"
              onChange={handleAddressFieldFormChange}
              name="line2"
              value={addressData.line2}
            />
            <Input
              size="lg"
              color="teal"
              label="City"
              className="text-lg text-sm"
              onChange={handleAddressFieldFormChange}
              name="city"
              value={addressData.city}
            />
            <Input
              size="lg"
              color="teal"
              label="State"
              className="text-lg text-sm"
              onChange={handleAddressFieldFormChange}
              name="state"
              value={addressData.state}
            />
            <Input
              size="lg"
              color="teal"
              label="Pincode"
              className="text-lg text-sm"
              onChange={handleAddressFieldFormChange}
              name="pincode"
              value={addressData.pincode}
            />
          </div>
          <Button
            className="mt-6 border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
            variant="outlined"
            fullWidth
            ripple={true}
            onClick={handleFormSubmit}
          >
            Signup
          </Button>
        </form>
      </Card>
    );
  };

  return (
    <Base>
      <div className="h-screen flex justify-center items-center">
        <ParticleBackground particleOptions={otherParticleConfig} />
        {signupForm()}
      </div>
    </Base>
  );
};

export default Signup;
