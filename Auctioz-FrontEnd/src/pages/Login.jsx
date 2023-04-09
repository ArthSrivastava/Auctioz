import React from "react";
import Base from "../components/Base";
import { Card, Input, Typography, Button } from "@material-tailwind/react";

const Login = () => {
  const loginForm = () => {
    return (
      <Card
        color="transparent"
        className="w-[30vw] h-[40vh] border-2 border-limeShade p-4 text-[#080808] rounded-2xl drop-shadow-lg flex items-center bg-[#e2e2e2]"
      >
        <Typography variant="h1">Log In</Typography>
        <form className="mt-8 mb-2 w-80 max-w-screen-lg sm:w-96">
          <div className="mb-4 flex flex-col gap-6 text-lg">
            <Input size="lg" color="teal" label="Email" className="text-lg" />
            <Input
              size="lg"
              color="teal"
              label="Password"
              className="text-lg"
            />
          </div>
          <Button
            className="mt-10 border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
            variant="outlined"
            fullWidth
            ripple={true}
          >
            Login
          </Button>
        </form>
      </Card>
    );
  };
  return (
    <Base>
      <div className="bg-limeShade h-[91vh] flex justify-center items-center">
        {loginForm()}
      </div>
    </Base>
  );
};

export default Login;
