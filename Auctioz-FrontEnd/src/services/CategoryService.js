import { CATEGORIES_URL, myAxios } from "./helper";

export const retrieveAllCategories = async () => {
  return await myAxios.get(CATEGORIES_URL).then((response) => response.data);
};
