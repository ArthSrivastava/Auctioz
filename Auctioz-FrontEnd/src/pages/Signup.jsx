import React, { useState } from "react";
import Base from "../components/Base";
import { Card, Input, Typography, Button } from "@material-tailwind/react";
import { signup } from "../services/UserService";

const Signup = () => {
  //store the signup form data
  const [data, setData] = useState({})

  const handleFormChange = (event) => {
    setData({
      ...data,
      [event.target.name]: event.target.value 
    })
  }

  const handleFormSubmit = (event) => {
    event.preventDefault();
    //TODO: make a call to backend to save the data here
    console.log("final object:", data)
    signup(data)
    .then((responseData) => {
      console.log("response:", responseData)
    }).catch((error) => console.log("error:", error))
  }

  const signupForm = () => {
    return (
      <Card
        color="transparent"
        className="w-[30vw] border-2 border-limeShade p-4 text-[#080808] rounded-2xl drop-shadow-lg flex items-center bg-[#e2e2e2]"
      >
        <Typography variant="h1">Sign Up</Typography>
        <Typography className="mt-2 font-normal" variant="h4">
          Enter your details
        </Typography>
        <form className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
          <div className="mb-4 flex flex-col gap-6 text-lg">
            <Input
              size="lg"
              color="teal"
              label="Name"
              className="text-lg"
              onChange={handleFormChange}
              name="name"
            />
            <Input
              size="lg"
              color="teal"
              label="Email"
              className="text-lg"
              onChange={handleFormChange}
              name="email"
            />
            <Input
              size="lg"
              color="teal"
              label="Password"
              className="text-lg"
              onChange={handleFormChange}
              name="password"
            />
            <Input
              size="lg"
              color="teal"
              label="Address Line 1"
              className="text-lg"
              onChange={handleFormChange}
              name="line1"
            />
            <Input
              size="lg"
              color="teal"
              label="Address Line 2"
              className="text-lg"
              onChange={handleFormChange}
              name="line2"
            />
            <Input
              size="lg"
              color="teal"
              label="City"
              className="text-lg"
              onChange={handleFormChange}
              name="city"
            />
            <Input
              size="lg"
              color="teal"
              label="State"
              className="text-lg"
              onChange={handleFormChange}
              name="state"
            />
            <Input
              size="lg"
              color="teal"
              label="Pincode"
              className="text-lg"
              onChange={handleFormChange}
              name="pincode"
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
    <div className="bg-limeShade h-[91vh] flex justify-center items-center">
        {signupForm()}
      </div>
  </Base>
  );
};

export default Signup;
