import React from 'react';
import numeral from 'numeral';
import {
    Grid,
    Box,
    Typography
} from '@mui/material';


export const ItemDescriptionValue = ({ name, color, amount }) => {

    return (
        <Grid container className='Box-p'>
            <Grid item xs={2} className='Box-p'>
                <Box
                    sx={{
                        border: 3,
                        borderColor: color,
                        borderRadius: '50%',
                        height: 16,
                        mr: 1,
                        width: 16
                    }}
                />
            </Grid>
            <Grid item xs={5}>
                <Typography variant="subtitle2">
                    {name}
                </Typography>
            </Grid>
            <Grid item xs={5}>
                <Typography
                    color="textSecondary"
                    variant="subtitle2">
                    {numeral(amount).format('$0,0.00')}
                </Typography>
            </Grid>
        </Grid>
    )
};

