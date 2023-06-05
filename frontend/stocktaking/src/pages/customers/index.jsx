import React from 'react';
import { Morning } from '../../components/Overview/Morning'
import { CustomerList } from '../../components/Customers/CustomerList'

const Customers = () => {
  
  return (
    <>
     <Morning nameMenu = 'Clientes'/>
     <CustomerList/>
    </>
  );
};

Customers.getLayout = (page) => (
  {page}
);

export default Customers;
