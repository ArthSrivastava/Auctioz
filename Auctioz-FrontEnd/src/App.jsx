import Home from './pages/Home'
import "./index.css"
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import ListProduct from './pages/ListProduct'
function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Navigate to="home" />}/>
        <Route path='home' element={<Home />} />
        <Route path='list/product' element={<ListProduct />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
