import { toast } from "react-toastify";

//validate email
export const validateEmail = (email) => {
    return String(email)
      .toLowerCase()
      .match(/^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/);
  };

 //validate pincode
 export const regexValidation = (testString, regex) => {

    // Check if the pin code matches the regular expression
    if (regex.test(testString)) {
      // Pin code is valid
      return true;
    } else {
      // Pin code is not valid
      return false;
    }
  };

//for printing validation errors from server side
export const serverSideErrors = (error) => {
    const obj = error.response.data;
    for (let field in obj) {
      toast.error(obj[field]);
    }
  };