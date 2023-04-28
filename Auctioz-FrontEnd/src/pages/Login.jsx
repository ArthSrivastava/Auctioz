import React, { useContext } from "react";
import Base from "../components/Base";
import { useState } from "react";
import { Card, Input, Typography, Button } from "@material-tailwind/react";
import { login } from "../services/UserService";
import { doLogin } from "../services/auth/auth_service";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import otherParticleConfig from "../components/config/other-particle-config";
import ParticleBackground from "../components/ParticleBackground";
import { UserContext } from "../contexts/UserContext";
import { serverSideErrors, validateEmail } from "../services/ValidationMethods";

const Login = () => {
  const [data, setData] = useState({
    email: "",
    password: "",
  });
  const { setCurrentUserData } = useContext(UserContext);
  const navigate = useNavigate();
  const handleFormChange = (event) => {
    setData({
      ...data,
      [event.target.name]: event.target.value,
    });
  };

  //validate fields
  const validateData = () => {
    let errMessage = "";
    if (!validateEmail(data.email)) {
      errMessage = "Email should be in correct format!";
    } else if (data.password.length < 3 || data.password.length > 30) {
      errMessage = "Password must be 3 to 30 characters long!";
    }
    if (errMessage !== "") {
      toast.error(errMessage);
    }
    return errMessage === "";
  };

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    // if(!validateData()) {
    //   return;
    // }
    try {
      const responseData = await login(data);
      setCurrentUserData(responseData.data);
      doLogin(responseData.data, () => {
        navigate("/");
        toast.success("Login Successful!");
      });
    } catch (error) {
      if (error.response.status == 401)
        toast.error(error.response.data.message);
      else 
        serverSideErrors(error);
      }
  };

  const loginForm = () => {
    return (
      <Card
        color="transparent"
        className="w-auto h-auto border-2 border-limeShade p-4 text-[#080808] rounded-2xl drop-shadow-lg flex items-center bg-[#ffffff]"
      >
        <Typography variant="h1">Log In</Typography>
        <form className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
          <div className="mb-4 flex flex-col gap-6 text-lg">
            <Input
              size="lg"
              color="teal"
              label="Email"
              className="text-lg"
              onChange={handleFormChange}
              name="email"
            />
            <Input
              size="lg"
              color="teal"
              label="Password"
              className="text-lg"
              type="password"
              onChange={handleFormChange}
              name="password"
            />
          </div>
          <Button
            className="mt-10 border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
            variant="outlined"
            fullWidth
            ripple={true}
            onClick={handleFormSubmit}
          >
            Login
          </Button>
        </form>
      </Card>
    );
  };
  return (
    <Base>
      <div className="h-[91vh] flex justify-center items-center">
        <ParticleBackground particleOptions={otherParticleConfig} />
        {loginForm()}
      </div>
    </Base>
  );
};

export default Login;
