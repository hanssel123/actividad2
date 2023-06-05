import React from 'react';
import { ProductCreate } from '../../components/Products/ProductCreate'

const CreateProduct = () => {
  
  return (
    <>
    <ProductCreate />
    </>
  );
};

CreateProduct.getLayout = (page) => (
  {page}
);

export default CreateProduct;
