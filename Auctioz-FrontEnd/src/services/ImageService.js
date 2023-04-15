import { BIDDING_SELLER_URL, privateAxios } from "./helper";

export const uploadImage = async (image, sellerId, productId) => {
  const formData = new FormData();
  formData.append("image", image);
  return await privateAxios.post(
    BIDDING_SELLER_URL + `/${sellerId}/products/${productId}/images`,
    formData,
    {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    }
  );
};
