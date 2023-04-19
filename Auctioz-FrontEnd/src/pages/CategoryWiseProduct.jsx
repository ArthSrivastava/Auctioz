import React, { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Base from "../components/Base";
import ParticleBackground from "../components/ParticleBackground";
import homeParticleConfig from "../components/config/home-particle-config";
import ProductFeed from "../components/ProductFeed";
import { toast } from "react-toastify";
import { getBiddingDetailsByProductId } from "../services/SellerBiddingService";
import { CategoryContext } from "../contexts/CategoryContext";
const CategoryWiseProduct = () => {
  const { categoryId } = useParams();
  const { productsByCategory, categoryIdGlobal, setCategoryIdGlobal } = useContext(CategoryContext);
  const [fullProductDetails, setFullProductDetails] = useState([]);
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    populateProductBiddingDetails(productsByCategory);
  }, [categoryId]);

  useEffect(() => {
    setCategoryIdGlobal(categoryId);
  }, [categoryId]);

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
      <ParticleBackground particleOptions={homeParticleConfig} />
      <div className="p-10 grid grid-cols-4 gap-4">
        {loading ? (
          <h1 className="text-white">Loading...</h1>
        ) : (
          <ProductFeed fullProductDetails={fullProductDetails} />
        )}
      </div>
    </Base>
  );
};

export default CategoryWiseProduct;
