import { LOGIN_URL, myAxios, privateAxios, SIGNUP_URL } from "./helper";

export const signup = async (data) => {
  return await myAxios.post(SIGNUP_URL, data).then((response) => response.data);
};

export const login = async (data) => {
  return await myAxios.post(LOGIN_URL, data).then((response) => response.data);
};
