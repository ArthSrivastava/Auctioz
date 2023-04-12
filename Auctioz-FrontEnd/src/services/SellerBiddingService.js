import { BIDDING_SELLER_URL, myAxios, privateAxios } from "./helper";

export const createSellerBidding = async (biddingData, sellerId, productId) => {
  return await privateAxios.post(
    `${BIDDING_SELLER_URL}/${sellerId}/products/${productId}/biddings`,
    biddingData
  );
};

//get product bidding details by product and seller id:
export const getBiddingDetailsByProductId = async (sellerId, productId) => {
  return await myAxios.get(
    `${BIDDING_SELLER_URL}/${sellerId}/products/${productId}/biddings`
  ).then(response => response.data)
}
