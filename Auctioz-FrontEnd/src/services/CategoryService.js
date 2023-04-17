import { CATEGORIES_URL, myAxios } from "./helper";

export const retrieveAllCategories = async () => {
  return await myAxios.get(CATEGORIES_URL);
};

export const retrieveAllProductsByCategory = async (categoryId) => {
  return await myAxios.get(`/categories/${categoryId}/products`);
}
