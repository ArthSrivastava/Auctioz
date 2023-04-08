import Home from './pages/Home'
import "./index.css"
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import ListProduct from './pages/ListProduct'
import UserRegistration from './pages/UserRegistration'
function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Navigate to="home" />}/>
        <Route path='home' element={<Home />} />
        <Route path='list/product' element={<ListProduct />} />
        <Route path='user/register' element={<UserRegistration />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
