import React from 'react';
import { useCallback, useEffect, useState } from 'react';
import { format } from 'date-fns';
import {
  Card,
  CardHeader,
  Divider,
  Table,
  TableBody,
  TableCell,
  TableRow,
  Typography
} from '@mui/material';
import { customerApi } from '../../constants/customers';
import { useMounted } from '../../hooks/useMounted';
import { MoreMenu } from '../Selectors/MoreMenu';
import { Scrollbar } from '../ScrollBar/ScrollBar';
import { SeverityPill } from '../SeverityPillRoot';

export const CustomerLogs = (props) => {
  const isMounted = useMounted();
  const [logs, setLogs] = useState([]);

  const getLogs = useCallback(async () => {
    try {
      const data = await customerApi.getCustomerLogs();

      if (isMounted()) {
        setLogs(data);
      }
    } catch (err) {
      console.error(err);
    }
  }, [isMounted]);

  useEffect(() => {
    getLogs();
  }, [getLogs]);

  return (
    <Card {...props}>
      <CardHeader
        action={<MoreMenu />}
        title="Registros Recientes"
      />
      <Divider />
      <Scrollbar>
        <Table sx={{ minWidth: 700 }}>
          <TableBody>
            {logs.map((log) => (
              <TableRow key={log.id}>
                <TableCell width="100">
                  <Typography
                    color="textSecondary"
                    variant="caption"
                  >
                    {log.method}
                  </Typography>
                </TableCell>
                <TableCell width="64">
                  <SeverityPill
                    color={(log.status >= 200 && log.status < 300)
                      ? 'success'
                      : 'error'}
                  >
                    {log.status}
                  </SeverityPill>
                </TableCell>
                <TableCell>
                  {log.route}
                </TableCell>
                <TableCell>
                  {log.description}
                </TableCell>
                <TableCell>
                  {log.ip}
                </TableCell>
                <TableCell>
                  {format(log.createdAt, 'yyyy/MM/dd HH:mm:ss')}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </Scrollbar>
    </Card>
  );
};
