import {
  Box,
  Button,
  Container,
  Grid,
  MenuItem,
  TextField,
  Typography
} from '@mui/material';
import { gtm } from '../../lib/gtm';

const Analytics = () => {
  
  return (
    <>

      <Box
        component="main"
        sx={{
          flexGrow: 1,
          py: 8,
          display:'flex'
        }}
      >
        <Container >
          <Box sx={{ mb: 4 }}>
            <Grid
              container
              justifyContent="space-between"
              spacing={3}
            >
              <Grid item>
                <Typography variant="h4">
                  Good Morning
                </Typography>
              </Grid>
              <Grid
                item
                sx={{
                  display: 'flex',
                  alignItems: 'center',
                  m: -1
                }}
              >
                <Button
                  sx={{ m: 1 }}
                  variant="outlined"
                >
                  Reports
                </Button>
                <TextField
                  defaultValue="week"
                  label="Period"
                  select
                  size="small"
                  sx={{ m: 1 }}
                >
                  <MenuItem value="week">
                    Last week
                  </MenuItem>
                  <MenuItem value="month">
                    Last month
                  </MenuItem>
                  <MenuItem value="year">
                    Last year
                  </MenuItem>
                </TextField>
              </Grid>
            </Grid>
          </Box>
        </Container>
      </Box>
    </>
  );
};


export default Analytics;
