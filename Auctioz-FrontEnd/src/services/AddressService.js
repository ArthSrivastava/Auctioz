import { myAxios } from "./helper";

export const createAddress = async (addressData, userId) => {
  return await myAxios
    .post(`/users/${userId}/addresses`, addressData)
    .then((response) => response.data);
};
