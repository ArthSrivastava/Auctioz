import React, { useContext, useState } from "react";
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
import { Link, useNavigate } from "react-router-dom";
import {
  doLogout,
  getCurrentUserData,
  isLoggedIn,
} from "../services/auth/auth_service";
import { toast } from "react-toastify";
import CategoryWiseProduct from "../pages/CategoryWiseProduct";
import { UserContext } from "../contexts/UserContext";
import { CategoryContext } from "../contexts/CategoryContext";

const CustomNavbar = () => {
  const [openNav, setOpenNav] = React.useState(false);
  const [loggedIn, setLoggedIn] = useState(false);
  const { categories } = useContext(CategoryContext);
  const { user } = useContext(UserContext);
  const { setCategoryIdGlobal } = useContext(CategoryContext);
  const navigate = useNavigate();
  React.useEffect(() => {
    window.addEventListener(
      "resize",
      () => window.innerWidth >= 960 && setOpenNav(false)
    );
    setLoggedIn(isLoggedIn());
  }, []);

  const handleCategoryClick = (categoryId) => {
    setCategoryIdGlobal(categoryId);
  };
  const navList = (
    <ul className="mb-4 mt-2 flex flex-col gap-2 lg:mb-0 lg:mt-0 lg:flex-row lg:items-center lg:gap-6">
      <Menu
        animate={{
          mount: { y: 0 },
          unmount: { y: 25 },
        }}
      >
        <MenuHandler>
          <Typography as="li" variant="h5" className="p-1 font-normal">
            <a className="flex items-center text-limeShade cursor-pointer">
              Shop By Category
            </a>
          </Typography>
        </MenuHandler>
        <MenuList className="bg-[#212121] text-limeShade border-limeShade">
          <MenuItem>
            <Link to="/" value="0">
              All products
            </Link>
          </MenuItem>
          {categories &&
            categories.map((category) => {
              return (
                <Link
                  to={`/products/categories/${category.id}`}
                  onClick={() => handleCategoryClick(category.id)}
                  element={<CategoryWiseProduct />}
                  key={category.id}
                >
                  <MenuItem value={category.id}>{category.name}</MenuItem>
                </Link>
              );
            })}
        </MenuList>
      </Menu>
      <Typography
        as="li"
        variant="h5"
        className="p-1 font-normal text-limeShade"
      >
        <Link to="/mybiddings" className="flex items-center">
          My Biddings
        </Link>
      </Typography>
      <Typography
        as="li"
        variant="h5"
        className="p-1 font-normal text-limeShade"
      >
        <Link to="/products" className="flex items-center">
          List Product
        </Link>
      </Typography>
    </ul>
  );

  const logoutUser = () => {
    doLogout(() => {
      navigate("/");
      toast.success("Logged out successfully!");
    });
  };
  return (
    <>
      <Navbar
        className="sticky inset-0 z-10 h-max max-w-full rounded-none py-0 px-4 lg:px-8 lg:py-4 bg-[#212121] my-0"
        color="transparent"
      >
        <div className="flex items-center justify-between text-blue-gray-900">
          <Link to="/">
            <Typography
              className="mr-4 cursor-pointer py-1.5 font-medium text-limeShade"
              variant="h3"
            >
              Auctioz
            </Typography>
          </Link>
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
            {!loggedIn ? (
              <>
                <Typography
                  as="li"
                  variant="h5"
                  className="p-1 font-normal text-limeShade"
                >
                  <Link to="/login">Log In</Link>
                </Typography>
                {/* <Button
              size="lg"
              variant="outlined"
              color="blue-gray"
              className="flex items-center gap-3 border-limeShade text-limeShade hover:bg-limeShade hover:text-white"
              ripple={true}
            > */}
                <Typography
                  as="li"
                  variant="h5"
                  className="p-1 font-normal text-limeShade"
                >
                  <Link to="/signup">Sign up</Link>
                </Typography>
              </>
            ) : (
              <>
                <Typography as="li" variant="h5" className="p-1 font-normal">
                  <a className="flex items-center text-limeShade">
                    {user?.email}
                  </a>
                </Typography>
                <Typography
                  as="li"
                  variant="h5"
                  className="p-1 font-normal text-limeShade"
                  onClick={logoutUser}
                  style={{ cursor: "pointer" }}
                >
                  Log out
                </Typography>
              </>
            )}
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
        <MobileNav open={openNav}>{navList}</MobileNav>
      </Navbar>
    </>
  );
};
export default CustomNavbar;
