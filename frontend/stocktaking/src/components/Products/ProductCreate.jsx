import React from 'react';
import {Link} from 'react-router-dom';
import { Box, Breadcrumbs, Container, Typography } from '@mui/material';
import { ProductCreateForm } from './ProductCreateForm';

export const ProductCreate = () => {

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
          <Box sx={{ mb: 3 }}>
            <Typography variant="h4">
              Crear un nuevo producto
            </Typography>
            <Breadcrumbs
              separator="/"
              sx={{ mt: 1 }}
            >
              <Link
                href="/dashboard"
                passHref
              >
                  Principal
              </Link>
              <Link
                href="/dashboard"
                passHref
              >
                  Gestión
                </Link>
              <Typography
                color="textSecondary"
                variant="subtitle2"
              >
                Productos
              </Typography>
            </Breadcrumbs>
          </Box>
          <ProductCreateForm />
        </Container>
      </Box>
    </>
  );
};

