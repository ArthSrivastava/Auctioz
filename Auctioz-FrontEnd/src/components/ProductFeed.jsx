import React from "react";
import { Link } from "react-router-dom";
import ProductCard from "./ProductCard";
import ProductDetails from "../pages/ProductDetails";
const ProductFeed = ({ fullProductDetails }) => {
  return (
    <>
      {fullProductDetails.map((product) => {
        return (
          <Link
            to={`/product/${product.id}`}
            element={<ProductDetails />}
            key={product.id}
            state={{ product: product }}
          >
            <ProductCard product={product} />
          </Link>
        );
      })}
    </>
  );
};

export default ProductFeed;
