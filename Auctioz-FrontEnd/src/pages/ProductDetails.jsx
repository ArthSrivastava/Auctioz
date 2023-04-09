import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Typography,
  Button,
} from "@material-tailwind/react";
import { CheckIcon } from "@heroicons/react/24/outline";

import React from "react";
import Base from "../components/Base";
const ProductDetails = () => {
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
            // color="white"
            className="font-normal uppercase text-[#080808]"
          >
            Iphone 14
          </Typography>
          <Typography
            variant="h1"
            // color="white"
            className="mt-6 flex justify-center gap-1 text-7xl font-normal text-[#080808]"
          >
            <Typography variant="h4">
              <span className="text-md">Current Price</span>
            </Typography>
            <span className="mt-2 text-4xl">$</span>
            <span classname="text-sm">200</span>{" "}
          </Typography>
        </CardHeader>
        <CardBody className="p-0">
          <ul className="flex flex-col gap-4">
            <li className="flex items-center gap-4">
              <span className="rounded-full border border-white/20 bg-white/20 p-1">
                <CheckIcon strokeWidth={2} className="h-3 w-3" />
              </span>
              <Typography className="font-normal">Seller - Test Seller 1</Typography>
            </li>
            <li className="flex items-center gap-4">
              <span className="rounded-full border border-white/20 bg-white/20 p-1">
                <CheckIcon strokeWidth={2} className="h-3 w-3" />
              </span>
              <Typography className="font-normal">Deadline - 20th April, 2023</Typography>
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
              <Typography className="font-normal">
                Free Shipping
              </Typography>
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
          <Button
            variant="outlined"
            size="sm"
            className="hidden lg:inline-block border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
            ripple={true}
            fullWidth={true}
          >
            Buy Now
          </Button>
        </CardFooter>
      </Card>
    );
  };
  const detailCard = () => {
    return (
      <div className="h-full w-[70vw] bg-white flex flex-col gap-4 mt-3">
        <div className="grid grid-cols-3 gap-8 p-4">
          <div className="col-span-2">
            <img src="/src/assets/iphone14.webp" className="max-h-content" />
          </div>

          <div>{buyNowCard()}</div>
        </div>
        <div>
          <Typography variant="h3">
            <span className="mx-3">About this item</span>
          </Typography>

          <Typography variant="h5" className="font-thin">
            <p className="mx-3">
              Lorem ipsum dolor sit amet, consectetur adipisicing elit.
              Pariatur, at temporibus repudiandae porro provident esse odio
              harum vitae voluptatibus aliquid suscipit fuga unde id praesentium
              iste dicta corrupti beatae. Voluptates sapiente dolor iste,
              explicabo laboriosam sequi amet nihil suscipit necessitatibus
              exercitationem mollitia aliquid repellendus nam iusto debitis nisi
              dolorem quo possimus. Laborum non delectus ipsum cum in, similique
              repellendus modi eligendi veritatis assumenda a nostrum, ea,
              aliquam odit molestiae error corrupti! Laborum tempora nostrum
              temporibus esse nobis at voluptatem quasi quidem veniam, a libero?
              Possimus expedita ducimus consequatur natus? Saepe eum quaerat
              nemo tempora doloremque in, unde deserunt incidunt. Architecto
              sequi impedit assumenda hic vitae illum temporibus dolore! Nemo a
              harum hic aut voluptatem molestias, rerum error reprehenderit
              magnam sapiente natus dolorum, facere dolore incidunt neque sint.
            </p>
          </Typography>
        </div>
      </div>
    );
  };
  return (
    <Base>
      <div className="flex h-full w-full bg-limeShade justify-center items-center">
        {detailCard()}
      </div>
    </Base>
  );
};

export default ProductDetails;
