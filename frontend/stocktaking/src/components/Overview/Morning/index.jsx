import React from "react";
import {
  Box,
  Container,
  Grid,
  Typography,
} from "@mui/material";
import ButtonGeneral from "../../Buttons/ButtonGeneral";
import TimeSelector from "../../Selectors/TimeSelector";
import { times } from '../../../constants/times'
import "./styles.css";

export const Morning = ({nameMenu}) => {

  return (
    <>
      <Box component="main">
        <Container>
          <Box sx={{ mb: 4 }}>
            <Grid container justifyContent="space-between" spacing={3}>
              <Grid item>
                <Typography variant="h4">{nameMenu === ''? 'Buenos días' : nameMenu}</Typography> 
              </Grid>
              <Grid className="Grid"
                item>
                <ButtonGeneral
                  name="REPORTES"
                  variant="contained"
                  color="primary"
                />
                <TimeSelector items={times} />
              </Grid>
            </Grid>
          </Box>
        </Container>
      </Box>
    </>
  );
};
