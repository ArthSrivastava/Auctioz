import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Typography,
  Button,
} from "@material-tailwind/react";
import { CheckIcon } from "@heroicons/react/24/outline";

import React, { useEffect, useState } from "react";
import Base from "../components/Base";
import { Link, useLocation } from "react-router-dom";
import BidNowPage from "./BidNowPage";
import { BASE_URL } from "../services/helper";
import { getUserById } from "../services/UserService";
import otherParticleConfig from "../components/config/other-particle-config";
import ParticleBackground from "../components/ParticleBackground";

const ProductDetails = () => {
  const location = useLocation();
  const product = location.state?.product;
  const [deadline, setDeadline] = useState();
  const [seller, setSeller] = useState();

  useEffect(() => {
    const date = new Date(product.bidInfo.deadline);
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let dt = date.getDate();

    if (dt < 10) {
      dt = "0" + dt;
    }
    if (month < 10) {
      month = "0" + month;
    }

    const formattedDate = dt + "-" + month + "-" + year;
    setDeadline(formattedDate);
    populateSeller();
  }, []);

  const populateSeller = async () => {
    const sellerData = await getUserById(product.sellerId);
    setSeller(sellerData.data);
  };

  const buyNowCard = () => {
    return (
      <Card
        color="transparent"
        variant="gradient"
        className="w-full max-w-[21rem] bg-[#e2e2e2] text-[#080808] p-8 mt-10"
      >
        <CardHeader
          floated={false}
          shadow={false}
          color="transparent"
          className="m-0 mb-8 rounded-none border-b border-white/10 pb-8 text-center"
        >
          <Typography
            variant="h3"
            className="font-normal uppercase text-[#080808]"
          >
            {product.name}
          </Typography>
          <Typography
            variant="h1"
            className="mt-6 flex justify-center gap-1 text-7xl font-normal text-[#080808]"
          >
            <Typography variant="h4">
              <span className="text-md">Current Price</span>
            </Typography>
            <span className="mt-2 text-4xl">
              {product.bidInfo.currentBidPrice}$
            </span>
            <span className="text-sm">{}</span>{" "}
          </Typography>
        </CardHeader>
        <CardBody className="p-0">
          <ul className="flex flex-col gap-4">
            <li className="flex items-center gap-4">
              <span className="rounded-full border border-white/20 bg-white/20 p-1">
                <CheckIcon strokeWidth={2} className="h-3 w-3" />
              </span>
              <Typography className="font-normal">
                Seller - {seller?.name}
              </Typography>
            </li>
            <li className="flex items-center gap-4">
              <span className="rounded-full border border-white/20 bg-white/20 p-1">
                <CheckIcon strokeWidth={2} className="h-3 w-3" />
              </span>
              <Typography className="font-normal">
                Deadline - {deadline}
              </Typography>
            </li>
            <li className="flex items-center gap-4">
              <span className="rounded-full border border-white/20 bg-white/20 p-1">
                <CheckIcon strokeWidth={2} className="h-3 w-3" />
              </span>
              <Typography className="font-normal">
                1 week return policy
              </Typography>
            </li>
            <li className="flex items-center gap-4">
              <span className="rounded-full border border-white/20 bg-white/20 p-1">
                <CheckIcon strokeWidth={2} className="h-3 w-3" />
              </span>
              <Typography className="font-normal">Free Shipping</Typography>
            </li>
            <li className="flex items-center gap-4">
              <span className="rounded-full border border-white/20 bg-white/20 p-1">
                <CheckIcon strokeWidth={2} className="h-3 w-3" />
              </span>
              <Typography className="font-normal">
                Auctioz Certified Seller
              </Typography>
            </li>
          </ul>
        </CardBody>
        <CardFooter className="mt-12 p-0">
          
          <Link to={`/bids/${product.id}`} element={<BidNowPage />} style={product.isSoldOut ? {pointerEvents: "none"} : null}>
            <Button
              variant="outlined"
              size="sm"
              className="hidden lg:inline-block border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
              ripple={true}
              fullWidth={true}
              disabled={product.isSoldOut}
            >
              {product.isSoldOut ? "Sold Out" : "Bid Now"}
            </Button>
          </Link>
        </CardFooter>
      </Card>
    );
  };
  const detailCard = () => {
    return (
      <div className="h-full min-h-[91vh] w-[70vw] bg-white flex flex-col gap-4 mt-3">
        <div className="grid grid-cols-3 gap-8 p-4">
          <div className="col-span-2">
            <img
              src={BASE_URL + "/images/" + product.imageName}
              className="h-[60vh] w-full object-contain"
            />
          </div>

          <div>{buyNowCard()}</div>
        </div>
        <div>
          <Typography variant="h3">
            <span className="mx-3">About this item</span>
          </Typography>

          <Typography variant="h5" className="font-thin">
            <p className="mx-3">
              {product && product.description}
            </p>
          </Typography>
        </div>
      </div>
    );
  };
  return (
    <Base>
      <div className="flex h-full w-full justify-center items-center">
        <ParticleBackground particleOptions={otherParticleConfig} />
        {detailCard()}
      </div>
    </Base>
  );
};

export default ProductDetails;
