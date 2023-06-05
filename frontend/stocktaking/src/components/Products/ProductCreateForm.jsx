import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import toast from 'react-hot-toast';
import * as Yup from 'yup';
import { useFormik } from 'formik';
import {
  Box,
  Button,
  Card,
  CardContent,
  FormControlLabel,
  FormHelperText,
  Grid,
  MenuItem,
  Switch,
  TextField,
  Typography
} from '@mui/material';
import { FileDropzone } from '../FileDropzone';
import {categoryOptions} from '../../constants/products'

export const ProductCreateForm = (props) => {
  const history = useNavigate();
  const [files, setFiles] = useState([]);
  const formik = useFormik({
    initialValues: {
      barcode: '925487986526',
      category: '',
      description: '',
      images: [],
      name: '',
      newPrice: 0,
      oldPrice: 0,
      sku: 'IYV-8745',
      submit: null
    },
    validationSchema: Yup.object({
      barcode: Yup.string().max(255),
      category: Yup.string().max(255),
      description: Yup.string().max(5000),
      images: Yup.array(),
      name: Yup.string().max(255).required(),
      newPrice: Yup.number().min(0).required(),
      oldPrice: Yup.number().min(0),
      sku: Yup.string().max(255)
    }),
    onSubmit: async (values, helpers) => {
      try {
        // NOTE: Make API request
        toast.success('Producto creado!');
        history.push('/products').catch(console.error);
      } catch (err) {
        console.error(err);
        toast.error('Something went wrong!');
        helpers.setStatus({ success: false });
        helpers.setErrors({ submit: err.message });
        helpers.setSubmitting(false);
      }
    }
  });

  const handleDrop = (newFiles) => {
    setFiles((prevFiles) => [...prevFiles, ...newFiles]);
  };

  const handleRemove = (file) => {
    setFiles((prevFiles) => prevFiles.filter((_file) => _file.path !== file.path));
  };

  const handleRemoveAll = () => {
    setFiles([]);
  };

  return (
    <form
      onSubmit={formik.handleSubmit}
      {...props}>
      <Card>
        <CardContent>
          <Grid
            container
            spacing={3}
          >
            <Grid
              item
              md={4}
              xs={12}
            >
              <Typography variant="h6">
                Detalles  Básicos
              </Typography>
            </Grid>
            <Grid
              item
              md={8}
              xs={12}
            >
              <TextField
                error={Boolean(formik.touched.name && formik.errors.name)}
                fullWidth
                helperText={formik.touched.name && formik.errors.name}
                label="Nombre del producto"
                name="name"
                sx={{ mt: 2 }}
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                value={formik.values.name}
              />

              <TextField
                error={Boolean(formik.touched.description && formik.errors.description)}
                fullWidth
                helperText={formik.touched.description && formik.errors.description}
                label="Descripción"
                name="description"
                sx={{ mt: 2 }}
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                value={formik.values.description}
              />
              {Boolean(formik.touched.description && formik.errors.description) && (
                <Box sx={{ mt: 2 }}>
                  <FormHelperText error>
                    {formik.errors.description}
                  </FormHelperText>
                </Box>
              )}
            </Grid>
          </Grid>
        </CardContent>
      </Card>
      <Card sx={{ mt: 3 }}>
        <CardContent>
          <Grid
            container
            spacing={3}
          >
            <Grid
              item
              md={4}
              xs={12}
            >
              <Typography variant="h6">
                Imagenes
              </Typography>
              <Typography
                color="textSecondary"
                variant="body2"
                sx={{ mt: 1 }}
              >
                Esta imagen aparecera en la parte frontal de la página
              </Typography>
            </Grid>
            <Grid
              item
              md={8}
              xs={12}
            >
              <FileDropzone
                accept={{
                  'image/*': []
                }}
                files={files}
                onDrop={handleDrop}
                onRemove={handleRemove}
                onRemoveAll={handleRemoveAll}
              />
            </Grid>
          </Grid>
        </CardContent>
      </Card>
      <Card sx={{ mt: 3 }}>
        <CardContent>
          <Grid
            container
            spacing={3}
          >
            <Grid
              item
              xl={2}
              md={4}
              xs={6}
            >
              <Typography variant="h6">
                Precio
              </Typography>
            </Grid>
            <Grid
              item
              md={8}
              xs={12}
            >
              <TextField
                error={Boolean(formik.touched.oldPrice && formik.errors.oldPrice)}
                fullWidth
                label="Antiguo precio"
                name="oldPrice"
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                type="number"
                value={formik.values.oldPrice}
              />
              <TextField
                error={Boolean(formik.touched.newPrice && formik.errors.newPrice)}
                fullWidth
                label="Precio Actual"
                name="newPrice"
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                sx={{ mt: 2 }}
                type="number"
                value={formik.values.newPrice}
              />
              <Box sx={{ mt: 2 }}>
                <FormControlLabel
                  control={<Switch />}
                  label="Mantener ventas cuando no exista el producto"
                />
              </Box>
            </Grid>
          </Grid>
        </CardContent>
      </Card>
      <Card sx={{ mt: 3 }}>
        <CardContent>
          <Grid
            container
            spacing={3}
          >
            <Grid
              item
              md={4}
              xs={12}
            >
              <Typography variant="h6">
                Categoría
              </Typography>
            </Grid>
            <Grid
              item
              md={8}
              xs={12}
            >
              <TextField
                error={Boolean(formik.touched.category && formik.errors.category)}
                fullWidth
                label="Categoría"
                name="category"
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                select
                value={formik.values.category}
              >
                {categoryOptions.map((option) => (
                  <MenuItem
                    key={option.value}
                    value={option.value}
                  >
                    {option.label}
                  </MenuItem>
                ))}
              </TextField>
              <TextField
                disabled
                error={Boolean(formik.touched.barcode && formik.errors.barcode)}
                fullWidth
                label="Código de barras"
                name="barcode"
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                sx={{ mt: 2 }}
                value={formik.values.barcode}
              />
              <TextField
                disabled
                error={Boolean(formik.touched.sku && formik.errors.sku)}
                fullWidth
                label="SKU"
                name="sku"
                onBlur={formik.handleBlur}
                onChange={formik.handleChange}
                sx={{ mt: 2 }}
                value={formik.values.sku}
              />
            </Grid>
          </Grid>
        </CardContent>
      </Card>
      <Box
        sx={{
          display: 'flex',
          flexWrap: 'wrap',
          justifyContent: 'space-between',
          mx: -1,
          mb: -1,
          mt: 3
        }}
      >
        <Button
          color="error"
          sx={{
            m: 1,
            mr: 'auto'
          }}
        >
          Borrar
        </Button>
        <Button
          sx={{ m: 1 }}
          variant="outlined"
        >
          Cancelar
        </Button>
        <Button
          sx={{ m: 1 }}
          type="submit"
          variant="contained"
        >
          Crear
        </Button>
      </Box>
    </form>
  );
};
