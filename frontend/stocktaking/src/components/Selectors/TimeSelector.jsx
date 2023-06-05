import React from "react";
import { TextField, MenuItem } from "@mui/material";

const TimeSelector = ({items}) => {

  return (
    <TextField defaultValue={items[0].value} label="Periodo" select size="small">
      {items.map((item) => (
        <MenuItem key= {item.id} value={item.value}>{item.name}</MenuItem>
      ))}
    </TextField>
  );
};

export default TimeSelector;
