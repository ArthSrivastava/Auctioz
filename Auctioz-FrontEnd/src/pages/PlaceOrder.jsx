import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Base from "../components/Base";
import { getProductById } from "../services/ProductService";
import { getBiddingDetailsByProductId } from "../services/SellerBiddingService";
import {
  Card,
  CardHeader,
  CardBody,
  Typography,
  Button,
} from "@material-tailwind/react";
import { toast } from "react-toastify";
import { createOrder, updateOrder } from "../services/PaymentService";
import { RZP_KEY_ID } from "../services/helper";
import { getCurrentUserData } from "../services/auth/auth_service";

const PlaceOrder = () => {
  const [product, setProduct] = useState({});
  const [bidding, setBidding] = useState({});
  const { userId, productId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    populateData();
  }, []);

  const populateData = async () => {
    try {
      if(getCurrentUserData().userId !== userId) {
          navigate("/");
          return;
      }
      const productData = await getProductById(productId);
      const biddingData = await getBiddingDetailsByProductId(
        productData.data.sellerId,
        productId
      );
      setProduct(productData.data);
      setBidding(biddingData.data);
    } catch {
      toast.error("Some error occurred in loading the product!");
    }
  };

  const orderDetailsCard = () => {
    return (
      <Card className="w-96">
        <CardHeader floated={false} className="h-80">
          <Typography variant="h5">
            Congratulations on winning the bid! Pay now for your order!
          </Typography>
          <img src="/src/assets/iphone14.webp" alt="product-picture" />
        </CardHeader>
        <CardBody className="text-center">
          <Typography variant="h4" color="blue-gray" className="mb-2">
            {product.name}
          </Typography>
          <Typography variant="h4" color="blue-gray" className="mb-2">
            {bidding.currentBidPrice}$
          </Typography>
          <Button
            variant="outlined"
            size="lg"
            className="hidden lg:inline-block border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
            ripple={true}
            onClick={payNow}
          >
            Pay Now
          </Button>
        </CardBody>
      </Card>
    );
  };

  const payNow = async () => {
    console.log("HI");
    let amt = bidding.currentBidPrice;
    console.log(amt);

    if (amt === "" || amt === null) {
      alert("Amount is required!");
      return;
    }
    const orderData = {
      userId: bidding.currentBidderId,
      productId: productId,
      amount: amt,
    };

    //create order
    try {
      const res = await createOrder(orderData);
      console.log("data:", res.data);
      const options = {
        key: RZP_KEY_ID,
        amount: res.data.amount,
        currency: "INR",
        name: "Test",
        description: "Test description",
        image: "/src/assets/react.svg",
        order_id: res.data.razorpayOrderId,
        handler: (response) => {
          const updatedOrderStatusData = {
            razorpayOrderId: response.razorpay_order_id,
            paymentId: response.razorpay_payment_id,
            status: "paid",
          };
          updatePaymentStatusOnServer(updatedOrderStatusData);
          toast.success("Payment successful!");
        },
        notes: {
          address: "Razorpay Corporate Office",
        },
        theme: {
          color: "#3399cc",
        },
      };
      const rzp1 = new Razorpay(options);
      rzp1.on("payment.failed", function (response) {
        console.log(response.error.code);
        console.log(response.error.description);
        console.log(response.error.source);
        console.log(response.error.step);
        console.log(response.error.reason);
        console.log(response.error.metadata.order_id);
        console.log(response.error.metadata.payment_id);
        alert("Payment failed!");
      });
      rzp1.open();
    } catch {
      toast.error("Some error occurred on server!");
    }
  };

  //update payment status
  const updatePaymentStatusOnServer = async (updatedOrderStatusData) => {
      try {
          const response = await updateOrder(updatedOrderStatusData);
      } catch {
          toast.error("Some error occurred on server!");
      }
  }

  return (
    <Base>
      <div className="flex justify-center items-center w-full h-[91vh]">
        {orderDetailsCard()}
      </div>
    </Base>
  );
};

export default PlaceOrder;