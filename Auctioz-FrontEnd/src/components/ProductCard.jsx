import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Typography,
} from "@material-tailwind/react";
import React from 'react'

const ProductCard = () => {
  return (
    <Card className="w-96 text-white border-2 border-transparent my-2 bg-[#212121] rounded-lg" >
    <CardBody className="" >
      <img src="./src/assets/courage.jpg" className="mb-2 "/>
    <Typography variant="h3" className="mb-2">
        iPhone 14 Pro Max
      </Typography>
      <Typography variant="h6">
        The flagship phone by apple
      </Typography>
    </CardBody>
    <CardFooter className="flex bg-[#080808] items-center justify-between py-3 rounded-lg">
      <Typography variant="medium" className="text-md font-bold">Starting Price</Typography>
      <Typography variant="medium" className="text-md font-bold">200$</Typography>
    </CardFooter>
  </Card>
  )
}

export default ProductCard