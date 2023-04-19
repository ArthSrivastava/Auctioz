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

const Login = () => {
  const [data, setData] = useState({});
  const { setCurrentUserData } = useContext(UserContext);
  const navigate = useNavigate();
  const handleFormChange = (event) => {
    setData({
      ...data,
      [event.target.name]: event.target.value,
    });
  };

  const handleFormSubmit = async (event) => {
    event.preventDefault();

    try {
     const responseData = await login(data);
     setCurrentUserData(responseData.data);
      doLogin(responseData.data, () => {
        navigate("/");
        toast.success("Login Successful!");
      });
    } catch {
      console.log("error occurred");
    }
  };

  const loginForm = () => {
    return (
      <Card
        color="transparent"
        className="w-[30vw] h-[40vh] border-2 border-limeShade p-4 text-[#080808] rounded-2xl drop-shadow-lg flex items-center bg-[#ffffff]"
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
      <ParticleBackground particleOptions={otherParticleConfig}/>
        {loginForm()}
      </div>
    </Base>
  );
};

export default Login;
