import React, { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import Base from "../components/Base";
import ProductCard from "../components/ProductCard";
import { getAllProducts } from "../services/ProductService";
import { getBiddingDetailsByProductId } from "../services/SellerBiddingService";
import ProductDetails from "./ProductDetails";
import ParticleBackground from "../components/ParticleBackground";
import homeParticleConfig from "../components/config/home-particle-config";
import { retrieveAllCategories } from "../services/CategoryService";
import ProductFeed from "../components/ProductFeed";
import { UserContext } from "../contexts/UserContext";

const Home = () => {
  const [fullProductDetails, setFullProductDetails] = useState([]);
  const [loading, setLoading] = useState(true);
  const [categories, setCategories] = useState([]);
  useEffect(() => {
    populateProducts();
    populateCategories();
  }, []);

  const populateProducts = async () => {
    try {
      const productData = await getAllProducts();
      populateProductBiddingDetails(productData.data);
    } catch {
      toast.error("Some error occurred in loading the products!");
    }
  };

  const populateCategories = async () => {
    const categoryData = await retrieveAllCategories();
    setCategories(categoryData.data);
  }

  const populateProductBiddingDetails = async (productData) => {
    const promises = productData.map(async (product) => {
      const sellerId = product.sellerId;
      const productId = product.id;
      try {
        const biddingData = await getBiddingDetailsByProductId(
          sellerId,
          productId
        );
        product.bidInfo = biddingData.data;
        return product;
      } catch {
        toast.error("Some error occurred in loading the bidding info!");
        return;
      }
    });

    const products = await Promise.all(promises);
    setFullProductDetails(products);
    setLoading(false);
  };

  return (
    <Base>
    <ParticleBackground particleOptions={homeParticleConfig}/>
      <div className="p-10 grid grid-cols-4 gap-4">
        {loading ? (
          <h1 className="text-white">Loading...</h1>
        ) : (
          <ProductFeed fullProductDetails={ fullProductDetails } />
        )}
      </div>
    </Base>
  );
};

export default Home;
