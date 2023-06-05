import React from 'react';
import numeral from 'numeral';
import {
  Box,
  Card,
  CardContent,
  Divider,
  List,
  ListItem,
  ListItemText,
  Typography
} from '@mui/material';
import ArrowRight from '@mui/icons-material/ArrowRight';
import { ItemDescriptionValue } from '../../Grid/ItemDescriptionValue';
import './styles.css';
import currencies from '../../../constants/currency';
import ButtonGeneral from '../../Buttons/ButtonGeneral';

export const OverviewTotalBalance = (props) => (
  <Card {...props}>
    <CardContent>
      <Typography
        color="textSecondary"
        variant="overline">
        Balance total
      </Typography>
      <Typography variant="h4">
        {numeral(3787681).format('$0,0.00')}
      </Typography>
      <Divider sx={{ my: 2 }} />
      <Typography
        color="textSecondary"
        variant="overline">
        Productos 
      </Typography>
      <List className='ListItem'
        disablePadding>
        {currencies.map((item) => (
          <ListItem className='ListItem'
            disableGutters
            key={item.name}>
            <ListItemText 
              disableTypography
              primary={(
                <ItemDescriptionValue 
                name={item.name}
                color={item.color}
                amount={item.amount}/>
              )}/>
          </ListItem>
        ))}
      </List>
      <Divider />
      <Box className= 'Box-s'>
        <ButtonGeneral name = 'Ver detalles'  icon={<ArrowRight fontSize="small" color='primary'/>}/>
      </Box>
    </CardContent>
  </Card>
);
