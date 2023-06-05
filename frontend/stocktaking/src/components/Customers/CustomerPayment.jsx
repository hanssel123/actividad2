import React from "react";
import { Button, Card, CardActions, CardHeader, Divider } from '@mui/material';
import { PropertyList } from '../Selectors/PropertyList';
import { PropertyListItem } from '../Selectors/PropertyListItem';

export const CustomerPayment = (props) => {

  return (
    <Card {...props}>
      <CardHeader title="Pago" />
      <Divider />
      <PropertyList>
        <PropertyListItem
          divider
          label="Tarjeta de credito"
          value="**** **** **** **** 4142"
        />
        <PropertyListItem
          divider
          label="Pago"
          value="2 ($50.00)"
        />
        <PropertyListItem
          divider
          label="Servicio"
          value="1 ($5.00)"
        />
        <PropertyListItem
          divider
          label="Estado/Region"
          value="2 ($50.00)"
        />
        <PropertyListItem
          divider
          label="Sin Pago"
          value="1 ($12.00)"
        />
        <PropertyListItem
          divider
          label="Reintegrado"
          value="0 ($0.00)"
        />
        <PropertyListItem
          label="Ingresos brutos"
          value="$1,200.00"
        />
      </PropertyList>
      <Divider />
      <CardActions
        sx={{
          flexWrap: 'wrap',
          px: 3,
          py: 2,
          m: -1
        }}
      >
        <Button
          sx={{ m: 1 }}
          variant="outlined"
        >
          Crear Factura
        </Button>
      </CardActions>
    </Card>
  );
};
