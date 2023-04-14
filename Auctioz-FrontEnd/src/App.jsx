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
function App() {
  return (
    <BrowserRouter>
      <ToastContainer position="bottom-center" />
      <Routes>
        <Route path="/" element={<Navigate to="home" />} />
        <Route path="home" element={<Home />} />
        <Route path="user/register" element={<UserRegistration />} />
        <Route path="signup" element={<Signup />} />
        <Route path="login" element={<Login />} />
        <Route path="product/:productId" element={<ProductDetails />} />
        <Route path="/" element={<PrivateRoutes />}>
          <Route path="products" element={<ListProduct />} />
          <Route path="bids/:productId" element={<BidNowPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
