import React from 'react'
import Base from '../components/Base'
import ProductCard from '../components/ProductCard'

const Home = () => {
  return (
    <Base>
      <h1 className="text-white">Hello world</h1>
      <ProductCard />
    </Base>
  )
}

export default Home