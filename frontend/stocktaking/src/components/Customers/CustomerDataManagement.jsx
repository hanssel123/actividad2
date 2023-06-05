import React from 'react';
import { Box, Button, Card, CardContent, CardHeader, Divider, Typography } from '@mui/material';

export const CustomerDataManagement = (props) => (
  <Card {...props}>
    <CardHeader title="Gestión de datos" />
    <Divider />
    <CardContent>
      <Button
        color="error"
        variant="outlined"
      >
        Borrar cuenta
      </Button>
      <Box sx={{ mt: 1 }}>
        <Typography
          color="textSecondary"
          variant="body2"
        >
          Desea eliminar este ususario?
        </Typography>
      </Box>
    </CardContent>
  </Card>
);
