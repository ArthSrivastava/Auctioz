import { myAxios, privateAxios, PRODUCT_URL } from "./helper";

export const listProduct = async (productData, sellerId) => {
  return privateAxios
    .post(`/sellers/${sellerId}/products`, productData)
    .then((response) => response.data);
};

export const getAllProducts = async () => {
  return await myAxios.get(PRODUCT_URL).then((response) => response.data);
};
