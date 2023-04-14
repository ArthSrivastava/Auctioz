import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Typography,
} from "@material-tailwind/react";
import React from "react";

const ProductCard = ({ product }) => {
  return (
    <Card className="w-96 text-white border-2 border-transparent my-2 bg-[#212121] rounded-lg">
      <CardBody className="">
        <img src="./src/assets/courage.jpg" className="mb-2 " />
        <Typography variant="h3" className="mb-2">
          {product.name}
        </Typography>
        <Typography variant="h6">{product.description}</Typography>
      </CardBody>
      <CardFooter className="flex bg-[#080808] items-center justify-between py-3 rounded-lg">
        <Typography variant="paragraph" className="text-md font-bold">
          Starting Price
        </Typography>
        <Typography variant="paragraph" className="text-md font-bold">
          {product.bidInfo && product.bidInfo.startBidPrice}$
        </Typography>
      </CardFooter>
    </Card>
  );
};

export default ProductCard;
