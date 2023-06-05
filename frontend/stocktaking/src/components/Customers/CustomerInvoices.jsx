import React from "react";

import { useCallback, useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { format } from 'date-fns';
import {
  Card,
  CardHeader,
  Divider,
  IconButton,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TablePagination,
  TableRow
} from '@mui/material';
import { customerApi } from '../../constants/customers';
import { useMounted } from '../../hooks/useMounted';
import ArrowRightAltIcon from '@mui/icons-material/ArrowRightAlt';
import { MoreMenu } from '../Selectors/MoreMenu';
import { Scrollbar } from '../ScrollBar/ScrollBar';
import { SeverityPill } from '../SeverityPillRoot';

export const CustomerInvoices = (props) => {
  const isMounted = useMounted();
  const [invoices, setInvoices] = useState([]);

  const getInvoices = useCallback(async () => {
    try {
      const data = await customerApi.getCustomerInvoices();

      if (isMounted()) {
        setInvoices(data);
      }
    } catch (err) {
      console.error(err);
    }
  }, [isMounted]);

  useEffect(() => {
    getInvoices();
  }, [getInvoices]);

  return (
    <Card {...props}>
      <CardHeader
        action={<MoreMenu />}
        title="Facturas recientes"
      />
      <Divider />
      <Scrollbar>
        <Table sx={{ minWidth: 600 }}>
          <TableHead>
            <TableRow>
              <TableCell>
                ID
              </TableCell>
              <TableCell>
                Fecha
              </TableCell>
              <TableCell>
                Total
              </TableCell>
              <TableCell>
                Estado
              </TableCell>
              <TableCell align="right">
                Acciones
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {invoices.map((invoice) => (
              <TableRow key={invoice.id}>
                <TableCell>
                  #
                  {invoice.id}
                </TableCell>
                <TableCell>
                  {format(invoice.issueDate, 'MMM dd,yyyy')}
                </TableCell>
                <TableCell>
                  {invoice.amount}
                </TableCell>
                <TableCell>
                  <SeverityPill variant = 'outlined' color={invoice.status === 'Pagado' ? 'success' : 'error'}>
                    {invoice.status}
                  </SeverityPill>
                </TableCell>
                <TableCell align="right"> 
                  <Link
                    href="/dashboard/invoices/1"
                    passHref
                  >
                    <IconButton component="a">
                      <ArrowRightAltIcon fontSize="small" />
                    </IconButton>
                  </Link>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Scrollbar>
      <TablePagination
        component="div"
        count={invoices.length}
        onPageChange={() => {
        }}
        onRowsPerPageChange={() => {
        }}
        page={0}
        rowsPerPage={5}
        rowsPerPageOptions={[5, 10, 25]}
      />
    </Card>
  );
};
