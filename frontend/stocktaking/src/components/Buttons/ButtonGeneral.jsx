import React from 'react';
import { Button } from '@mui/material';

const ButtonGeneral = ({ name, variant, icon, color }) => {
    return (
        <Button
            variant={variant && "outlined"} color={color && 'primary'}>
                {icon && <span>{icon}</span>}
            {name}
        </Button>
    );
};

export default ButtonGeneral;