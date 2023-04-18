import Home from "./pages/Home";
import "./index.css";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import ListProduct from "./pages/ListProduct";
import UserRegistration from "./pages/UserRegistration";
import Signup from "./pages/Signup";
import Login from "./pages/Login";
import ProductDetails from "./pages/ProductDetails";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import PrivateRoutes from "./components/PrivateRoutes";
import BidNowPage from "./pages/BidNowPage";
import PlaceOrder from "./pages/PlaceOrder";
import CategoryWiseProduct from "./pages/CategoryWiseProduct";
import UserProvider from "./contexts/UserProvider";
import CategoryProvider from "./contexts/CategoryProvider";
function App() {
  return (
    <BrowserRouter>
      <ToastContainer position="bottom-center" />
      <UserProvider>
        <CategoryProvider>
          <Routes>
            <Route path="/" element={<Navigate to="home" />} />
            <Route path="home" element={<Home />} />
            <Route path="user/register" element={<UserRegistration />} />
            <Route path="signup" element={<Signup />} />
            <Route path="login" element={<Login />} />
            <Route path="product/:productId" element={<ProductDetails />} />
            <Route
              path="products/categories/:categoryId"
              element={<CategoryWiseProduct />}
            />
            <Route path="/" element={<PrivateRoutes />}>
              <Route path="products" element={<ListProduct />} />
              <Route path="bids/:productId" element={<BidNowPage />} />
              <Route
                path="users/:userId/products/:productId/orders/pay"
                element={<PlaceOrder />}
              />
            </Route>
          </Routes>
        </CategoryProvider>
      </UserProvider>
    </BrowserRouter>
  );
}

export default App;
