import Home from './pages/Home'
import "./index.css"
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import ListProduct from './pages/ListProduct'
import UserRegistration from './pages/UserRegistration'
import Signup from './pages/Signup'
import Login from './pages/Login'
import ProductDetails from './pages/ProductDetails'
function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Navigate to="home" />}/>
        <Route path='home' element={<Home />} />
        <Route path='products' element={<ListProduct />} />
        <Route path='user/register' element={<UserRegistration />} />
        <Route path='signup' element={<Signup />} />
        <Route path='login' element={<Login />} />
        <Route path='product/:productId' element={<ProductDetails />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
