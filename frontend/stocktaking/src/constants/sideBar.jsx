import Cash  from '@mui/icons-material/Money';
import Home from '@mui/icons-material/Home';
import ReceiptTax from '@mui/icons-material/Receipt';
import ShoppingBag from '@mui/icons-material/ShoppingBag';
import ShoppingCart from '@mui/icons-material/ShoppingCart';
import Truck from '@mui/icons-material/FireTruck';
import Man from '@mui/icons-material/Man';
import '../styles/App.css'

export const Sections = [
  {
    title: 'GENERAL',
    items: [
      {
        title: 'Descripción General',
        path: '/overview',
        icon: <Home className='Icons' fontSize="small" />,
        chip: '',
        children:  ''},
      // },
      // {
      //   title: 'Analítica',
      //   path: '/analytics',
      //   icon: <ChartBar className='Icons' fontSize="small" />,
      //   chip: '',
      //   children:  ''
      // },
      {
        title: 'Logistica',
        path: '/logistics',
        icon: <Truck className='Icons' fontSize="small" />,
        chip: 'Nuevo',
        children:  ''
      },
    ]
  },
  {
    title: 'GESTIÓN',
    items: [
      {
        title: 'Clientes',
        path: '/customers',
        icon: <Man className='Icons' fontSize="small" />,
        children: [
          {
            title: 'Lista',
            path: '/customers',
            chip: '',
            children:  ''
          },
          {
            title: 'Detalles',
            path: '/customers/1',
            chip: '',
            children:  ''
          }
        ],
        chip: ''
      },
      {
        title: 'Productos',
        path: '/products',
        icon: <ShoppingBag className='Icons' fontSize="small" />,
        children: [
          {
            title: 'Lista',
            path: '/products',
            chip: '',
            children:  ''
          },
          {
            title: 'Crear',
            path: '/products/new',
            chip: '',
            children:  ''
          }
        ],
        chip: ''
      },
      {
        title: 'Pedidos',
        icon: <ShoppingCart className='Icons' fontSize="small" />,
        path: '/orders',
        children: [
          {
            title: 'Lista',
            path: '/orders',
            chip: '',
            children:  ''
          },
          {
            title: 'Detalles',
            path: '/orders/1',
            chip: '',
            children:  ''
          }
        ],
        chip: ''
      },
      // {
      //   title: 'Facturas',
      //   path: '/invoices',
      //   icon: <ReceiptTax className='Icons' fontSize="small" />,
      //   chip: '',
      //   children: [
      //     {
      //       title: 'Lista',
      //       path: '/dashboard/invoices',
      //       chip: '',
      //       children:  ''
      //     },
      //     {
      //       title: 'Detalles',
      //       path: '/invoices/1',
      //       chip: '',
      //       children:  ''
      //     }
      //   ]
      // },
      // {
      //   title: 'Checkout',
      //   path: '/checkout',
      //   icon: <Cash className='Icons' fontSize="small" />,
      //   chip: '',
      //   children:  ''
      // },
    ]
  },

]