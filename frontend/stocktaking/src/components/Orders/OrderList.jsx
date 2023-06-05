import React, { useCallback, useEffect, useRef, useState } from 'react';
import {
  Box,
  Button,
  Divider,
  Grid,
  InputAdornment,
  Tab,
  Tabs,
  TextField,
  Typography
} from '@mui/material';
import { styled } from '@mui/material/styles';
import { orderApi } from '../../constants/orders';
import { OrderDrawer } from './OrderDrawer';
import { OrderListTable } from './OrderListTable';
import { useMounted } from '../../hooks/useMounted';
import AddIcon from '@mui/icons-material/Add';
import SearchIcon from '@mui/icons-material/Search';

const tabs = [
  {
    label: 'Todo',
    value: 'all'
  },
  {
    label: 'Cancelado',
    value: 'canceled'
  },
  {
    label: 'Completado',
    value: 'complete'
  },
  {
    label: 'Pendiente',
    value: 'pending'
  },
  {
    label: 'Rechazado',
    value: 'rejected'
  }
];

const sortOptions = [
  {
    label: 'Lo más nuevo',
    value: 'desc'
  },
  {
    label: 'Lo más viejo',
    value: 'asc'
  }
];

const applyFilters = (orders, filters) => orders.filter((order) => {
  if (filters.query) {
    // Checks only the order number, but can be extended to support other fields, such as customer
    // name, email, etc.
    const containsQuery = (order.number || '').toLowerCase().includes(filters.query.toLowerCase());

    if (!containsQuery) {
      return false;
    }
  }

  if (typeof filters.status !== 'undefined') {
    const statusMatched = order.status === filters.status;

    if (!statusMatched) {
      return false;
    }
  }

  return true;
});

const applySort = (orders, sortDir) => orders.sort((a, b) => {
  const comparator = a.createdAt > b.createdAt ? -1 : 1;

  return sortDir === 'desc' ? comparator : -comparator;
});

const applyPagination = (orders, page, rowsPerPage) => orders.slice(page * rowsPerPage,
  page * rowsPerPage + rowsPerPage);

const OrderListInner = styled('div',
  { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    flexGrow: 1,
    overflow: 'hidden',
    paddingBottom: theme.spacing(8),
    paddingTop: theme.spacing(8),
    zIndex: 1,
    transition: theme.transitions.create('margin', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen
    }),
    ...(open && {
      transition: theme.transitions.create('margin', {
        easing: theme.transitions.easing.easeOut,
        duration: theme.transitions.duration.enteringScreen
      })
    })
  }));

export const OrderList = () => {
  const isMounted = useMounted();
  const rootRef = useRef(null);
  const queryRef = useRef(null);
  const [currentTab, setCurrentTab] = useState('all');
  const [sort, setSort] = useState('desc');
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const [orders, setOrders] = useState([]);
  const [filters, setFilters] = useState({
    query: '',
    status: undefined
  });
  const [drawer, setDrawer] = useState({
    isOpen: false,
    orderId: undefined
  });

  const getOrders = useCallback(async () => {
    try {
      const data = await orderApi.getOrders();

      if (isMounted()) {
        setOrders(data);
      }
    } catch (err) {
      console.error(err);
    }
  }, [isMounted]);

  useEffect(() => {
      getOrders();
    },
    // eslint-disable-next-line react-hooks/exhaustive-deps
    []);

  const handleTabsChange = (event, value) => {
    setCurrentTab(value);
    setFilters((prevState) => ({
      ...prevState,
      status: value === 'all' ? undefined : value
    }));
  };

  const handleQueryChange = (event) => {
    event.preventDefault();
    setFilters((prevState) => ({
      ...prevState,
      query: queryRef.current?.value
    }));
  };

  const handleSortChange = (event) => {
    const value = event.target.value;
    setSort(value);
  };

  const handlePageChange = (event, newPage) => {
    setPage(newPage);
  };

  const handleRowsPerPageChange = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
  };

  const handleOpenDrawer = (orderId) => {
    setDrawer({
      isOpen: true,
      orderId
    });
  };

  const handleCloseDrawer = () => {
    setDrawer({
      isOpen: false,
      orderId: undefined
    });
  };

  // Usually query is done on backend with indexing solutions
  const filteredOrders = applyFilters(orders, filters);
  const sortedOrders = applySort(filteredOrders, sort);
  const paginatedOrders = applyPagination(sortedOrders, page, rowsPerPage);

  return (
    <>
      <Box
        component="main"
        ref={rootRef}
        sx={{
          backgroundColor: 'background.paper',
          display: 'flex',
          flexGrow: 1,
          overflow: 'hidden'
        }}
      >
        <OrderListInner open={drawer.isOpen}>
          <Box sx={{ px: 3 }}>
            <Grid
              container
              justifyContent="space-between"
              spacing={3}
            >
              <Grid item>
                <Typography variant="h4">
                  Ordenes
                </Typography>
              </Grid>
              <Grid item>
                <Button
                  startIcon={<AddIcon fontSize="small" />}
                  variant="contained"
                >
                  Añadir
                </Button>
              </Grid>
            </Grid>
            <Tabs
              indicatorColor="primary"
              onChange={handleTabsChange}
              scrollButtons="auto"
              textColor="primary"
              value={currentTab}
              sx={{ mt: 3 }}
              variant="scrollable"
            >
              {tabs.map((tab) => (
                <Tab
                  key={tab.value}
                  label={tab.label}
                  value={tab.value}
                />
              ))}
            </Tabs>
          </Box>
          <Divider />
          <Box
            sx={{
              alignItems: 'center',
              display: 'flex',
              flexWrap: 'wrap',
              m: -1.5,
              p: 3
            }}
          >
            <Box
              component="form"
              onSubmit={handleQueryChange}
              sx={{
                flexGrow: 1,
                m: 1.5
              }}
            >
              <TextField
                defaultValue=""
                fullWidth
                inputProps={{ ref: queryRef }}
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">
                      <SearchIcon fontSize="small" />
                    </InputAdornment>
                  )
                }}
                placeholder="Buscar por número de pedido"
              />
            </Box>
            <TextField
              label="Sort By"
              name="order"
              onChange={handleSortChange}
              select
              SelectProps={{ native: true }}
              sx={{ m: 1.5 }}
              value={sort}
            >
              {sortOptions.map((option) => (
                <option
                  key={option.value}
                  value={option.value}
                >
                  {option.label}
                </option>
              ))}
            </TextField>
          </Box>
          <Divider />
          <OrderListTable
            onOpenDrawer={handleOpenDrawer}
            onPageChange={handlePageChange}
            onRowsPerPageChange={handleRowsPerPageChange}
            orders={paginatedOrders}
            ordersCount={filteredOrders.length}
            page={page}
            rowsPerPage={rowsPerPage}
          />
        </OrderListInner>
        <OrderDrawer
          containerRef={rootRef}
          onClose={handleCloseDrawer}
          open={drawer.isOpen}
          order={orders.find((order) => order.id === drawer.orderId)}
        />
      </Box>
    </>
  );
};

