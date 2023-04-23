import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import Base from "../components/Base";
import { getAllProducts } from "../services/ProductService";
import { getBiddingDetailsByProductId } from "../services/SellerBiddingService";
import ParticleBackground from "../components/ParticleBackground";
import homeParticleConfig from "../components/config/home-particle-config";
import { retrieveAllCategories } from "../services/CategoryService";
import ProductFeed from "../components/ProductFeed";

const Home = () => {
  const [fullProductDetails, setFullProductDetails] = useState([]);
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    populateProducts();
  }, []);

  const populateProducts = async () => {
    try {
      const productData = await getAllProducts();
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
    <ParticleBackground particleOptions={homeParticleConfig}/>
      <div className="p-10 grid 2xl:grid-cols-4 xl:grid-cols-3 lg:grid-cols-2 sm:grid-rows- gap-4 ">
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
