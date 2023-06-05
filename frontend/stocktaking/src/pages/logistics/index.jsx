import React from 'react';
import { Morning } from '../../components/Overview/Morning'
import { LogisticsOverview } from '../../components/Logistics/LogisticsOverview'
import { LogisticsConditions } from '../../components/Logistics/LogisticConditions'

const Logistics = () => {
  
  return (
    <>
     <Morning nameMenu = 'Logística'/>
     <LogisticsOverview />
     <LogisticsConditions />
    </>
  );
};

Logistics.getLayout = (page) => (
  {page}
);

export default Logistics;
