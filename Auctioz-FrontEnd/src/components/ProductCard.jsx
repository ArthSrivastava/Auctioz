import {
  Card,
  CardBody,
  CardFooter,
  Typography,
} from "@material-tailwind/react";
import React from "react";
import { BASE_URL } from "../services/helper";

const ProductCard = ({ product }) => {
  return (
    <Card className="w-96 text-white border-2 border-limeShade my-2 bg-transparent rounded-lg">
      <CardBody className="hover:bg-[#080808] ">
        <img
          src={BASE_URL + "/images/" + product.imageName}
          className="mb-2 h-[20vh] w-full object-contain"
        />
        <Typography variant="h3" className="mb-2">
          {product.name.length > 16 ? (product.name.substring(0, 16) + "...") : product.name}
        </Typography>
        <Typography variant="h6" className="">
          {product.description.substring(0, 30)}....
        </Typography>
      </CardBody>
      <CardFooter className="flex flex-col bg-[#080808] items-center justify-between py-3 rounded-lg">
        <div className="flex justify-between w-full">
          <Typography variant="paragraph" className="text-md font-bold">
            Starting Price
          </Typography>
          <Typography variant="paragraph" className="text-md font-bold">
            {product.bidInfo && product.bidInfo.startBidPrice}$
          </Typography>
        </div>
        <div className="flex justify-between w-full">
          <Typography variant="paragraph" className="text-md font-bold">
            Current Bid Price
          </Typography>
          <Typography variant="paragraph" className="text-md font-bold">
            {product.bidInfo && (product.bidInfo.currentBidPrice ? product.bidInfo.currentBidPrice : 0)}$
          </Typography>
        </div>
        <div className="flex justify-between w-full">
          <Typography variant="paragraph" className="text-md font-bold">
            Status
          </Typography>
          <Typography variant="paragraph" className="text-md font-bold">
            {product.isSoldOut ? "Sold Out" : "In-Stock"}
          </Typography>
        </div>
      </CardFooter>
    </Card>
  );
};

export default ProductCard;
