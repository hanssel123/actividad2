import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { format } from 'date-fns';
import {
  Box,
  Button,
  Card,
  CardHeader,
  Divider,
  TextField,
  Typography,
} from '@mui/material';
import { PropertyList } from '../Selectors/PropertyList';
import { PropertyListItem } from '../Selectors/PropertyListItem';

const statusOptions = ['Cancelado', 'Completado', 'Rechazado'];

export const OrderSummary = (props) => {
  const { order, ...other } = props;
  const [status, setStatus] = useState(statusOptions[0]);

  const handleChange = (event) => {
    setStatus(event.target.value);
  };

  return (
    <Card {...other}>
      <CardHeader title="Información Básica" />
      <Divider />
      <PropertyList>
        <PropertyListItem
          label="Cliente"
        >
          <Typography
            color="primary"
            variant="body2"
          >
            {order.customer.name}
          </Typography>
          <Typography
            color="textSecondary"
            variant="body2"
          >
            {order.customer.address1}
          </Typography>
          <Typography
            color="textSecondary"
            variant="body2"
          >
            {order.customer.city}
          </Typography>
          <Typography
            color="textSecondary"
            variant="body2"
          >
            {order.customer.country}
          </Typography>
        </PropertyListItem>
        <Divider />
        <PropertyListItem
          label="ID"
          value={order.id}
        />
        <Divider />
        <PropertyListItem
          label="Factura"
          value={order.number}
        />
        <Divider />
        <PropertyListItem
          label="Fecha"
          value={format(order.createdAt, 'dd/MM/yyyy HH:mm')}
        />
        <Divider />
        <PropertyListItem
          label="Código de promoción"
          value={order.promotionCode}
        />
        <Divider />
        <PropertyListItem
          label="Cantidad total"
          value={`${order.currency}${order.totalAmount}`}
        />
        <Divider />
        <PropertyListItem
          label="Estado"
        >
          <Box
            sx={{
              alignItems: {
                sm: 'center'
              },
              display: 'flex',
              flexDirection: {
                xs: 'column',
                sm: 'row'
              },
              mx: -1
            }}
          >
            <TextField
              label="Estado"
              margin="normal"
              name="status"
              onChange={handleChange}
              select
              SelectProps={{ native: true }}
              sx={{
                flexGrow: 1,
                m: 1,
                minWidth: 150
              }}
              value={status}
            >
              {statusOptions.map((statusOption) => (
                <option
                  key={statusOption}
                  value={statusOption}
                >
                  {statusOption}
                </option>
              ))}
            </TextField>
            <Button
              sx={{ m: 1 }}
              variant="contained"
            >
              Guardar
            </Button>
            <Button sx={{ m: 1 }}>
              Cancelar
            </Button>
          </Box>
        </PropertyListItem>
      </PropertyList>
    </Card>
  );
};

OrderSummary.propTypes = {
  order: PropTypes.object.isRequired
};
