import React from "react";
import { Link } from "react-router-dom";
import Base from "../components/Base";
import ProductCard from "../components/ProductCard";

const Home = () => {
  return (
    <Base>
      <div className="p-10 grid grid-cols-4 gap-4">
        <Link to="/product/1">
          <ProductCard />
        </Link>
        <ProductCard />
        <ProductCard />
        <ProductCard />
        <ProductCard />
        <ProductCard />
        <ProductCard />
      </div>
    </Base>
  );
};

export default Home;
