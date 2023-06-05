import React from 'react';
import { OrderDetails } from '../../components/Orders/OrderDetails'

const OrdersId = () => {
  
  return (
    <>
      <OrderDetails />
    </>
  );
};

OrdersId.getLayout = (page) => (
  {page}
);

export default OrdersId;
