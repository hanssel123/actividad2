import React from 'react';
import { ProductList} from '../../components/Products/ProductList';

const Products = () => {
  
  return (
    <>
    <ProductList />
    </>
  );
};

Products.getLayout = (page) => (
  {page}
);

export default Products;
