import React from 'react'
import Base from '../components/Base'
import ProductCard from '../components/ProductCard'

const Home = () => {

  return (
    <Base>
      <div className='p-10 grid grid-cols-4 gap-4'>
        <ProductCard />
        <ProductCard />
        <ProductCard />
        <ProductCard />
        <ProductCard />
        <ProductCard />
        <ProductCard />
      </div>
    </Base>
  )
}

export default Home