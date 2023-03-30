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
    <Card className="w-96 text-white border-2 border-limeShade" color="transparent">
    <CardHeader color="blue" className="relative h-56 bg-cover">
      <img src="./src/assets/courage.jpg" className="bg-cover" />
    </CardHeader>
    <CardBody className="text-center">
    <Typography variant="h2" className="mb-2">
        iPhone 14 Pro Max
      </Typography>
      <Typography variant="h6">
        The flagship phone by apple
      </Typography>
    </CardBody>
    <CardFooter className="flex items-center justify-between py-3">
      <Typography variant="medium" className="text-md font-bold">Starting Price</Typography>
      <Typography variant="medium" className="text-md font-bold">200$</Typography>
    </CardFooter>
  </Card>
  )
}

export default ProductCard