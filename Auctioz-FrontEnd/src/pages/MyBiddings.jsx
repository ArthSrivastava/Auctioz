import {
  Card,
  CardBody,
  CardHeader,
  Typography,
} from "@material-tailwind/react";
import React, { useContext, useEffect, useState } from "react";
import Base from "../components/Base";
import ParticleBackground from "../components/ParticleBackground";
import { UserContext } from "../contexts/UserContext";
import otherParticleConfig from "../components/config/other-particle-config";
import { getAllBidsByUserId } from "../services/UserBidService";
import { getProductById } from "../services/ProductService";
import { toast } from "react-toastify";
const MyBiddings = () => {
  const { user } = useContext(UserContext);
  const [myProducts, setMyProducts] = useState([]);
  useEffect(() => {
    populateBids();
  }, [user]);
  const populateBids = async () => {
    try {
      const bids = await getAllBidsByUserId(user.id);
      const promises = bids.data.map(async (bid) => {
        const productData = await getProductById(bid.productId);
        bid.product = productData.data;
        return bid;
      });
      const fullData = await Promise.all(promises);
      setMyProducts(fullData);
    } catch {
      toast.error("Some error occurred!");
      return;
    }
  };

  const formatDate = (timestamp) => {

    const date = new Date(timestamp);
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
    return formattedDate;
  }

  const populateDiv = () => {
    return (
      <Card className="w-auto">
        <CardBody>
          <div className="grid grid-cols-4 gap-5 ml-10 mb-3">
            <Typography variant="h2" className="text-lg md:text-2xl">Product Name</Typography>
            <Typography variant="h2" className="col-span-2 mx-6 text-lg md:text-2xl">Your Bidding Amount</Typography>
            <Typography variant="h2" className="text-lg md:text-2xl">Date</Typography>
          </div>
          {myProducts &&
            myProducts.map((fullDetails, index) => {
              return (
                <div className="grid grid-cols-4 gap-x-16 p-3 ml-10" key={index}>
                  <Typography variant="h2" className="text-lg md:text-2xl">
                    {fullDetails.product.name}
                  </Typography>
                  <Typography variant="h2" className="font-normal col-span-2 mx-6 text-lg md:text-2xl">
                    {fullDetails.biddingAmount}$
                  </Typography>
                  <Typography variant="h2" className="font-normal text-sm md:text-2xl">
                    {formatDate(fullDetails.timestamp)}
                  </Typography>
                </div>
              );
            })}
        </CardBody>
      </Card>
    );
  };
  return (
    <Base>
      <ParticleBackground particleOptions={otherParticleConfig} />
      <div className="w-auto flex justify-center items-center w-[100vw] mt-10">
        {populateDiv()}
      </div>
    </Base>
  );
};

export default MyBiddings;
