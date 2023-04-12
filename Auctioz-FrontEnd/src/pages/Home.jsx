import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import Base from "../components/Base";
import ProductCard from "../components/ProductCard";
import { getAllProducts } from "../services/ProductService";
import { getBiddingDetailsByProductId } from "../services/SellerBiddingService";
import ProductDetails from "./ProductDetails";
const Home = () => {
  const [fullProductDetails, setFullProductDetails] = useState([]);
  useEffect(() => {
    getAllProducts()
      .then((data) => {
        populateProductBiddingDetails(data);
      })
      .catch(() => {
        toast.error("Some error occurred in loading the products!");
      });
  }, []);

  const populateProductBiddingDetails = (productData) => {
    productData.forEach((product, index) => {
      // console.log(`p${index} `, product);
      const sellerId = product.sellerId;
      const productId = product.id;

      getBiddingDetailsByProductId(sellerId, productId)
        .then((data) => {
          // console.log("bdata:", data)
          product.bidInfo = data;
        })
        .catch(() =>
          toast.error("Some error occurred while fetching bidding details!")
        );
      setFullProductDetails([...fullProductDetails, product]);
    });
  };

  return (
    <Base>
      {console.log("FPD:", fullProductDetails)}
      <div className="p-10 grid grid-cols-4 gap-4">
        {fullProductDetails.map((product) => {
            return (
              <Link
                to={`/product/${product.id}`}
                element={<ProductDetails product={product} />}
              >
                <ProductCard product={product} />
              </Link>
            );
          })}
      </div>
    </Base>
  );
};

export default Home;
