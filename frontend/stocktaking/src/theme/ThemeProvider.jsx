import React from 'react';
import { ThemeProvider } from 'styled-components';
import {createTheme} from './index';

const Theme = ({ children }) => {
  return <ThemeProvider theme={createTheme}>{children}</ThemeProvider>;
};

export default Theme;