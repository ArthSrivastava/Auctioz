import { ORDER_URL, privateAxios } from "./helper"

export const createOrder = async (orderData) => {
    return await privateAxios.post(ORDER_URL, orderData);
}

export const updateOrder = async (orderData) => {
    return await privateAxios.put(ORDER_URL, orderData);
}

