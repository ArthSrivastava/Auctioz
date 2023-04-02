import { Card, Input, Typography, Button, Select, Option } from "@material-tailwind/react";
import React from "react";
import Base from "../components/Base";

const ListProduct = () => {
  const listingForm = () => {
    return (
      <Card
        color=""
        className="w-[60vh] border-2 border-limeShade p-4 text-[#080808] rounded-2xl drop-shadow-lg flex items-center bg-[#e2e2e2]"
      >
        <Typography variant="h1">List product</Typography>
        <Typography className="mt-2 font-normal" variant="h4">
          Enter product details
        </Typography>
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
              label="Start Bid Price"
              className="text-lg"
            />
            <Input
              size="lg"
              color="teal"
              label="Deadline"
              className="text-lg"
            />
            <Select label="Select Category">
                <Option>C1</Option>
                <Option>C2</Option>
                <Option>C3</Option>
                <Option>C4</Option>
            </Select>
          </div>
          <Button
            className="mt-6 border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
            variant="outlined"
            fullWidth
            ripple={true}
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
