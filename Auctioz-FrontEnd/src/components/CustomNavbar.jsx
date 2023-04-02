import React, { useState } from "react";
import {
  Navbar,
  MobileNav,
  Typography,
  Button,
  IconButton,
  Menu,
  MenuHandler,
  MenuList,
  MenuItem,
} from "@material-tailwind/react";
import { Link, Navigate } from "react-router-dom";

const CustomNavbar = () => {
  const [openNav, setOpenNav] = React.useState(false);

  React.useEffect(() => {
    window.addEventListener(
      "resize",
      () => window.innerWidth >= 960 && setOpenNav(false)
    );
  }, []);

  const navList = (
    <ul className="mb-4 mt-2 flex flex-col gap-2 lg:mb-0 lg:mt-0 lg:flex-row lg:items-center lg:gap-6">
      <Typography as="li" variant="h5" className="p-1 font-normal">
        <a href="#" className="flex items-center text-limeShade">
          My Biddings
        </a>
      </Typography>
      <Menu
        animate={{
          mount: { y: 0 },
          unmount: { y: 25 },
        }}
      >
        <MenuHandler>
          <Typography as="li" variant="h5" className="p-1 font-normal">
            <a href="#" className="flex items-center text-limeShade">
              Shop By Category
            </a>
          </Typography>
        </MenuHandler>
        <MenuList className="bg-[#212121] text-limeShade border-limeShade">
          <MenuItem>Item 1</MenuItem>
          <MenuItem>Item 2</MenuItem>
          <MenuItem>Item 3</MenuItem>
        </MenuList>
      </Menu>
      <Typography
        as="li"
        variant="h5"
        className="p-1 font-normal text-limeShade"
      >
        <a href="#" className="flex items-center">
          Account
        </a>
      </Typography>
      <Typography
        as="li"
        variant="h5"
        className="p-1 font-normal text-limeShade"
      >
        <Link to="/list/product" className="flex items-center">
          List Product
        </Link>
      </Typography>
    </ul>
  );

  return (
    <>
      <Navbar
        className="sticky inset-0 z-10 h-max max-w-full rounded-none py-0 px-4 lg:px-8 lg:py-4 bg-[#212121]"
        color=""
      >
        <div className="flex items-center justify-between text-blue-gray-900">
          <Typography
            as="a"
            href="#"
            className="mr-4 cursor-pointer py-1.5 font-medium text-limeShade"
            variant="h3"
          >
            Auctioz
          </Typography>
          <div className="flex items-center gap-4">
            <div className="mr-4 hidden lg:block">{navList}</div>
            {/* <Button
              variant="outlined"
              size="sm"
              className="hidden lg:inline-block border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
              ripple={true}
            >
              <span>Sign in</span>
            </Button> */}
            <Button
              size="lg"
              variant="outlined"
              color="blue-gray"
              className="flex items-center gap-3 border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
              ripple={true}
            >
              <img
                src="/src/assets/google-icon.svg"
                alt="google"
                className="h-6 w-6"
              />
              Sign in with Google
            </Button>
            <IconButton
              variant="text"
              className="ml-auto h-6 w-6 text-inherit hover:bg-transparent focus:bg-transparent active:bg-transparent lg:hidden"
              ripple={false}
              onClick={() => setOpenNav(!openNav)}
            >
              {openNav ? (
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  fill="none"
                  className="h-6 w-6"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                  strokeWidth={2}
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M6 18L18 6M6 6l12 12"
                  />
                </svg>
              ) : (
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  className="h-6 w-6"
                  fill="none"
                  stroke="currentColor"
                  strokeWidth={2}
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M4 6h16M4 12h16M4 18h16"
                  />
                </svg>
              )}
            </IconButton>
          </div>
        </div>
        <MobileNav open={openNav}>
          {navList}
          <Button variant="gradient" size="sm" fullWidth className="mb-2">
            <span>Buy Now</span>
          </Button>
        </MobileNav>
      </Navbar>
    </>
  );
};
export default CustomNavbar;
