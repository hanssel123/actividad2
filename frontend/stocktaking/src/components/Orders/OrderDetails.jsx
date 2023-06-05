import React, { useCallback, useEffect, useState } from 'react';
import { Link} from 'react-router-dom';
import { format } from 'date-fns';
import { Box, Button, Container, Grid, Typography } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { orderApi } from '../../constants/orders';
import { OrderItems } from './OrderItems';
import { OrderLogs } from './OrderLogs';
import { OrderSummary } from './OrderSummary';
import { useMounted } from '../../hooks/useMounted';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import CreateIcon from '@mui/icons-material/Create';

export const OrderDetails = () => {
  const isMounted = useMounted();
  const [order, setOrder] = useState(null);

  const getOrder = useCallback(async () => {
    try {
      const data = await orderApi.getOrder();

      if (isMounted()) {
        setOrder(data);
      }
    } catch (err) {
      console.error(err);
    }
  }, [isMounted]);

  useEffect(() => {
      getOrder();
    },
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []);

  if (!order) {
    return null;
  }

  return (
    <>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          py: 8
        }}
      >
        <Container maxWidth="md">
          <Box sx={{ mb: 4 }}>
            <Link
              href="orders"
              passHref
            >
                <ArrowBackIcon
                  fontSize="small"
                  sx={{ mr: 1 }}
                />
                <Typography variant="subtitle2">
                  Ordenes
                </Typography>
              </Link>
          </Box>
          <Box sx={{ mb: 4 }}>
            <Grid
              container
              justifyContent="space-between"
              spacing={3}
            >
              <Grid item>
                <Typography variant="h4">
                  {order.number}
                </Typography>
                <Box
                  sx={{
                    alignItems: 'center',
                    display: 'flex',
                    ml: -1,
                    mt: 1
                  }}
                >
                  <Typography
                    color="textSecondary"
                    variant="body2"
                    sx={{ ml: 1 }}
                  >
                    Colocado en
                  </Typography>
                  <CalendarMonthIcon
                    color="action"
                    fontSize="small"
                    sx={{ ml: 1 }}
                  />
                  <Typography
                    variant="body2"
                    sx={{ ml: 1 }}
                  >
                    {format(order.createdAt, 'dd/MM/yyyy HH:mm')}
                  </Typography>
                </Box>
              </Grid>
              <Grid
                item
                sx={{ ml: -2 }}
              >
                <Button
                  endIcon={(
                    <CreateIcon fontSize="small" />
                  )}
                  variant="outlined"
                  sx={{ ml: 2 }}
                >
                  Editar
                </Button>
                <Button
                  endIcon={(
                    <ExpandMoreIcon fontSize="small" />
                  )}
                  variant="contained"
                  sx={{ ml: 2 }}
                >
                  Acciones
                </Button>
              </Grid>
            </Grid>
          </Box>
          <OrderSummary order={order} />
          <Box sx={{ mt: 4 }}>
            <OrderItems orderItems={order.items || []} />
          </Box>
          <Box sx={{ mt: 4 }}>
            <OrderLogs order={order} />
          </Box>
        </Container>
      </Box>
    </>
  );
};
