import { myAxios, privateAxios, USER_URL } from "./helper";

export const createAddress = async (addressData, userId) => {
  return await myAxios
    .post(`/users/${userId}/addresses`, addressData)
    .then((response) => response.data);
};

export const getAddressById = async (userId, productId) => {
  return await privateAxios.get(`/users/${userId}/addresses/${productId}`);
}

export const updateAddress = async (userId, addressId, addressData) => {
  return await privateAxios.put(USER_URL + `/${userId}/addresses/${addressId}`, addressData);
}
