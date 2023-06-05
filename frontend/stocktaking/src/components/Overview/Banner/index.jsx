import React from 'react';
import PropTypes from 'prop-types';
import { Box, Card, Typography } from '@mui/material';
import './styles.css'
export const OverviewBanner = () => {

  return (
    <Card className='Card'>
      <Box className='Box'>
      </Box>
      <div>
        <Typography
          color="inherit"
          sx={{ mt: 2 }}
          variant="h4"
        >
          Bienvenido nuevamente
        </Typography>
        <Typography
          color="inherit"
          sx={{ mt: 1 }}
          variant="subtitle2"
        >
          ¡Tu tablero ha sido mejorado! Explore nuevas funciones como notificaciones, búsqueda, plataforma de trabajos y más.
        </Typography>
      </div>
    </Card>

  );
};

OverviewBanner.propTypes = {
  onDismiss: PropTypes.func
};
