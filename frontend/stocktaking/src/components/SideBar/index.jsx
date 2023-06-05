import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Box, Chip, Container, Divider, Typography } from '@mui/material';
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Collapse from '@mui/material/Collapse';
import { Sections } from '../../constants/sideBar'
import KeyboardArrowDownTwoToneIcon from '@mui/icons-material/KeyboardArrowDownTwoTone';
import KeyboardArrowUpTwoToneIcon from '@mui/icons-material/KeyboardArrowUpTwoTone';
import './styles.css'

  const SidebarItem = ({ item }) => {
    const [open, setOpen] = useState(false);
  
    const handleClick = () => {
      setOpen(!open);
    };
  
    if (item.children) {
      return (
        <Container>
          <ListItemButton onClick={handleClick}>
            <ListItemIcon>{item.icon}</ListItemIcon>
            <ListItemText className="Typography" primary={item.title} />
            {open ? <KeyboardArrowUpTwoToneIcon className='Icons' fontSize="small" /> 
            : <KeyboardArrowDownTwoToneIcon className='Icons' fontSize="small" />}
          </ListItemButton>
          <Divider />
          <Collapse in={open} timeout="auto" unmountOnExit>
            <List className='List' component="div" disablePadding>
              {item.children.map((child) => (
                <SidebarItem key={child.title} item={child} nested={true} />
              ))}
            </List>
          </Collapse>
        </Container>
      );
    }
  
    return (
      <Link to = {item.path}>
      <ListItemButton  className='Link' >
        <ListItemIcon>{item.icon}</ListItemIcon>
        <ListItemText className="Typography" primary={item.title} />
        {
                              item.chip !== '' ? <Chip
                                color="primary"
                                label={(
                                  <Typography className="Typography">
                                    {item.chip}
                                  </Typography>
                                )}
                                size="small"
                              /> : null}
      </ListItemButton>
      </Link>
    );
  }
  
  export const DashboardSidebar= () => {
  
    return (
      <Box className="Box">
        <List component="nav">
          {Sections.map((section) => (
            <Box className="Typography" key={section.title}>
              <ListItemButton className='Link' disabled>
                <ListItemText className="Typography" primary={section.title} />
              </ListItemButton>
              <Divider />
              {section.items.map((item) => (
                <SidebarItem key={item.title} item={item} />
              ))}
            </Box>
          ))}
        </List>
      </Box>
    );
  };

