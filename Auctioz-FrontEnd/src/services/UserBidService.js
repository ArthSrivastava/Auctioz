import { privateAxios, USER_URL } from "./helper"

export const createNewBid = async (userId, productId, amount) => {
    return await privateAxios.post(USER_URL + `/${userId}/products/${productId}/bids`, amount);
}

export const getAllBidsByUserId = async (userId) => {
    return await privateAxios.get(USER_URL + `/${userId}/bids`);
}