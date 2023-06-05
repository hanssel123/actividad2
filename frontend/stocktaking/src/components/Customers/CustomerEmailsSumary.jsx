import { useCallback, useEffect, useState } from 'react';
import { format } from 'date-fns';
import {
  Box,
  Button,
  Card,
  CardContent,
  CardHeader,
  Divider,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  TextField,
  Typography
} from '@mui/material';
import { customerApi } from '../../constants/customers';
import { useMounted } from '../../hooks/useMounted';
import ArrowRightAltIcon from '@mui/icons-material/ArrowRightAlt';

const emailOptions = [
  'Reenviar ultima factura',
  'Enviar nueva contraseña',
  'Enviar verificación'
];

export const CustomerEmailsSummary = (props) => {
  const isMounted = useMounted();
  const [emailOption, setEmailOption] = useState(emailOptions[0]);
  const [emails, setEmails] = useState([]);

  const getEmails = useCallback(async () => {
    try {
      const data = await customerApi.getCustomerEmails();

      if (isMounted()) {
        setEmails(data);
      }
    } catch (err) {
      console.error(err);
    }
  }, [isMounted]);

  useEffect(() => {
    getEmails();
  }, [getEmails]);

  return (
    <Card {...props}>
      <CardHeader title="Correos" />
      <Divider />
      <CardContent>
        <TextField
          name="option"
          onChange={(event) => setEmailOption(event.target.value)}
          select
          SelectProps={{ native: true }}
          sx={{
            width: 320,
            maxWidth: '100%'
          }}
          value={emailOption}
        >
          {emailOptions.map((option) => (
            <option
              key={option}
              value={option}
            >
              {option}
            </option>
          ))}
        </TextField>
        <Box sx={{ mt: 2 }}>
          <Button
            endIcon={<ArrowRightAltIcon fontSize="small" />}
            variant="contained"
          >
            Enviar correo electrónico
          </Button>
        </Box>
      </CardContent>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>
              Tipo de correo electrónico
            </TableCell>
            <TableCell>
              Fecha
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {emails.map((email) => (
            <TableRow
              key={email.id}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell>
                <Typography variant="subtitle2">
                  {email.description}
                </Typography>
              </TableCell>
              <TableCell>
                {format(email.createdAt, 'dd/MM/yyyy | HH:mm')}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Card>
  );
};
