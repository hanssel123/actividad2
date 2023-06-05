import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import Theme from './theme/ThemeProvider';
ReactDOM.render(<Theme>
    <App />
  </Theme>, document.getElementById('root'));
  