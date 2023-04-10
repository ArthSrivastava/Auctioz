import { myAxios, SIGNUP_URL } from "./helper";

export const signup = async (data) => {
  return await myAxios
    .post(SIGNUP_URL, data)
    .then((response) => {
        console.log(response)
        return response.data
    });
};
