import React from 'react';
import { Box } from '@mui/material';
import { Morning }  from '../../components/Overview/Morning/index'
import { OverviewBanner }  from '../../components/Overview/Banner/index'
import { OverviewTotalBalance }  from '../../components/Overview/Balance/index'

const Overview = () => {
  return (
<Box>
  <Morning/>
  <OverviewBanner/>
  <OverviewTotalBalance/>
</Box>
  );
};

export default Overview;