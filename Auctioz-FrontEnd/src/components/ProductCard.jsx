import { CardBody, CardFooter, CardHeader } from '@material-tailwind/react'
import React from 'react'

const ProductCard = () => {
  return (
      <>
        <Card>
            <CardHeader>Header comes here!</CardHeader>
            <CardBody>Lorem ipsum dolor sit amet consectetur adipisicing elit. Labore laudantium reprehenderit porro, minus quaerat ipsa earum saepe blanditiis dolore, sequi facere iusto sit tempore rerum hic deleniti, error eos eaque quas. Natus, excepturi ad.</CardBody>
            <CardFooter>Footer comes here!</CardFooter>
        </Card>
      </>
  )
}

export default Card