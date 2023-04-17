import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Base from "../components/Base";
import ParticleBackground from "../components/ParticleBackground";
import homeParticleConfig from "../components/config/home-particle-config";
import { retrieveAllProductsByCategory } from "../services/CategoryService";
import ProductFeed from "../components/ProductFeed";
import { toast } from "react-toastify";
import { getBiddingDetailsByProductId } from "../services/SellerBiddingService";
const CategoryWiseProduct = () => {
  const { categoryId } = useParams();  //TODO: handle state globally
  const [fullProductDetails, setFullProductDetails] = useState([]);
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    populateProducts();
  }, []);

  const populateProducts = async () => {
    try {
      const productData = await retrieveAllProductsByCategory(categoryId);
      populateProductBiddingDetails(productData.data);
    } catch {
      toast.error("Some error occurred in loading the products!");
    }
  };

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
