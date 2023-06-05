import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
// import './styles/App.css';
import { DashboardSidebar } from '../src/components/SideBar/index';
import Overview from './pages/overview/index';
import CustomerList  from './pages/customers/index';
import CustomersDetail  from './pages/customers/customerId/index';
import Analytics  from './pages/analytics/index';
import Logistics  from './pages/logistics/index';
import Products  from './pages/products/index';
import CreateProduct  from './pages/products/new';
import Orders  from './pages/orders/index';
import OrdersId  from './pages/orders/orderId';

import { Grid } from '@mui/material';


function App() {
  return (
    <>
    <Router>
        <Grid 
            container
            spacing={3}
            justifyContent="space-between">
              <Grid item 
              xs={2}>
              <DashboardSidebar />

              </Grid>
              <Grid item 
              xs={10}>
              <Routes>
            <Route key="/overview" path="/overview" element={<Overview/>} />
            <Route key="/analytics" path="/analytics" element={<Analytics/>} />
            <Route key="/logistics" path="/logistics" element={<Logistics/>} />
            <Route key="/customers" path="/customers" element={<CustomerList/>} />
            <Route key="/customers/1" path="/customers/1" element={<CustomersDetail/>} />
            <Route key="/products" path="/products" element={<Products/>} />
            <Route key="/products/new" path="/products/new" element={<CreateProduct/>} />
            <Route key="/orders" path="/orders" element={<Orders/>} />
            <Route key="/orders/1" path="/orders/1" element={<OrdersId/>} />
            
        </Routes>

              </Grid>
          
        </Grid>
          
    </Router>
    </>
  );
}

export default App;
