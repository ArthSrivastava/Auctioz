import React, { useContext, useEffect, useState } from "react";

import { Card, Input, Typography, Button } from "@material-tailwind/react";
import Base from "../components/Base";
import { getAddressById, updateAddress } from "../services/AddressService";
import { createNewBid } from "../services/UserBidService";
import { useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { UserContext } from "../contexts/UserContext";
import otherParticleConfig from "../components/config/other-particle-config";
import ParticleBackground from "../components/ParticleBackground";
const BidNowPage = () => {
  const [address, setAddress] = useState({});
  const { user } = useContext(UserContext);
  const [amount, setAmount] = useState();
  const { productId } = useParams();

  const [loading, setLoading] = useState(true);
  useEffect(() => {
    populateData();
  }, []);

  const populateData = async () => {
    const addressData = await getAddressById(
      user.id,
      user.addressId
    );
    setAddress(addressData.data);
    setLoading(false);
  };

  const handleFormChange = (event) => {
    setAmount({
      ...amount,
      [event.target.name]: event.target.value,
    });
  };

  const handleAddressFieldFormChange = (event) => {
    setAddress({
      ...address,
      [event.target.name]: event.target.value,
    });
  };

  const handleFormSubmit = async () => {
    //create the bid
    try {
      await createNewBid(user.id, productId, amount);
      toast.success("Bid successful!");
    } catch {
      toast.error("Bid failed!");
    }

    //update address
    try {
      await updateAddress(user.id, address.id, address);
      toast.success("Address updated successfully!");
    } catch {
      toast.error("Address updation failed!");
    }
  };

  const bidNow = () => {
    return (
      <Card
        color="transparent"
        className="w-auto border-2 border-limeShade p-4 text-[#080808] rounded-2xl drop-shadow-lg flex items-center bg-[#e2e2e2]"
      >
        <Typography variant="h1">Bid Now</Typography>
        <Typography className="mt-2 font-normal" variant="h4">
          Enter your details
        </Typography>
        <form className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
          <div className="mb-4 flex flex-col gap-6 text-lg">
            <Input
              size="lg"
              color="teal"
              label="Amount"
              className="text-lg"
              onChange={handleFormChange}
              name="biddingAmount"
            />
            <Input
              size="lg"
              color="teal"
              label="Address Line 1"
              className="text-lg"
              onChange={handleAddressFieldFormChange}
              name="line1"
              value={address && address.line1}
            />
            <Input
              size="lg"
              color="teal"
              label="Address Line 2"
              className="text-lg"
              onChange={handleAddressFieldFormChange}
              name="line2"
              value={address.line2}
            />
            <Input
              size="lg"
              color="teal"
              label="City"
              className="text-lg"
              onChange={handleAddressFieldFormChange}
              name="city"
              value={address.city}
            />
            <Input
              size="lg"
              color="teal"
              label="State"
              className="text-lg"
              onChange={handleAddressFieldFormChange}
              name="state"
              value={address.state}
            />
            <Input
              size="lg"
              color="teal"
              label="Pincode"
              className="text-lg"
              onChange={handleAddressFieldFormChange}
              name="pincode"
              value={address.pincode}
            />
          </div>
          <Button
            className="mt-6 border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
            variant="outlined"
            fullWidth
            ripple={true}
            onClick={handleFormSubmit}
          >
            Bid
          </Button>
        </form>
      </Card>
    );
  };
  return (
    <Base>
      {loading ? (
        <h1 className="text-white">Loading...</h1>
      ) : (
        <div className="h-screen flex justify-center items-center">
          <ParticleBackground particleOptions={otherParticleConfig} />
          {bidNow()}
        </div>
      )}
    </Base>
  );
};

export default BidNowPage;
