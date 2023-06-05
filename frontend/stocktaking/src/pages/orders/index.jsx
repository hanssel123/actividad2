import React from 'react';
import { OrderList } from '../../components/Orders/OrderList'
const Orders = () => {
  
  return (
    <>
    <OrderList />
    </>
  );
};

Orders.getLayout = (page) => (
  {page}
);

export default Orders;
