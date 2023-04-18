import React, { useEffect, useState } from "react";
import { CategoryContext } from "./CategoryContext";
import { retrieveAllCategories } from "../services/CategoryService";
import { toast } from "react-toastify";
const CategoryProvider = ({ children }) => {
  const [categories, setCategories] = useState([]);
  const [categoryIdGlobal, setCategoryIdGlobal] = useState("");
  const [productsByCategory, setProductsByCategory] = useState([]);
  useEffect(() => {
    populateCategories();
  }, []);
  const populateCategories = async () => {
    const categoryData = await retrieveAllCategories();
    setCategories(categoryData.data);
  };

  useEffect(() => {
    populateProducts();
  }, [categoryIdGlobal]);

  const populateProducts = async () => {
    try {
        console.log("cidGlobal:", categoryIdGlobal)
      const productData = await retrieveAllProductsByCategory(categoryIdGlobal);
      console.log("pro:", productsByCategory)
      setProductsByCategory(productData.data);
    } catch {
      toast.error("Some error occurred in loading the products!");
    }
  };
  return (
    <CategoryContext.Provider
      value={{
        categories,
        setCategories,
        categoryIdGlobal,
        setCategoryIdGlobal,
        productsByCategory,
      }}
    >
      {children}
    </CategoryContext.Provider>
  );
};

export default CategoryProvider;
