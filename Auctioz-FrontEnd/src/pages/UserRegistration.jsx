import React from "react";
import { Card, Input, Typography, Button, Select, Option } from "@material-tailwind/react";
import Base from "../components/Base";

const UserRegistration = () => {
  const listingForm = () => {
    return (
      <Card
        color=""
        className="w-[60vh] border-2 border-limeShade p-4 text-[#080808] rounded-2xl drop-shadow-lg flex items-center bg-[#e2e2e2]"
      >
        <Typography variant="h1">Enter your information</Typography>
        <form className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
          <div className="mb-4 flex flex-col gap-6 text-lg">
            <Input size="lg" color="teal" label="Name" className="text-lg" />
            <Input
              size="lg"
              color="teal"
              label="Description"
              className="text-lg"
            />
            <Input
              size="lg"
              color="teal"
              label="Address Line 1"
              className="text-lg"
            />
            <Input
              size="lg"
              color="teal"
              label="Address Line 2"
              className="text-lg"
            />
            <Input
              size="lg"
              color="teal"
              label="City"
              className="text-lg"
            />
            <Input
              size="lg"
              color="teal"
              label="State"
              className="text-lg"
            />
            <Input
              size="lg"
              color="teal"
              label="Pincode"
              className="text-lg"
            />
          </div>
          <Button
            className="mt-6 border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
            variant="outlined"
            fullWidth
            ripple={true}
          >
            Register
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

export default UserRegistration;
