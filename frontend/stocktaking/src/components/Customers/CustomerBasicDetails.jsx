import React from "react";
import PropTypes from "prop-types";
import { Button, Card, CardActions, CardHeader, Divider } from "@mui/material";
import { PropertyList } from "../Selectors/PropertyList";
import { PropertyListItem } from "../Selectors/PropertyListItem";

const CustomerBasicDetails = (props) => {
	const {
		address1,
		address2,
		country,
		email,
		isVerified,
		phone,
		state,
		...other
	} = props;

	return (
		<Card {...other}>
			<CardHeader title="Detalles Generales" />
			<Divider />
			<PropertyList>
				<PropertyListItem divider label="Correo electrónico" value={email} />
				<PropertyListItem divider label="Teléfono" value={phone} />
				<PropertyListItem divider label="Pais" value={country} />
				<PropertyListItem divider label="Estado/Región" value={state} />
				<PropertyListItem divider label="Dirección 1" value={state} />
				<PropertyListItem divider label="Direccón 2" value={address2} />
			</PropertyList>
			<CardActions
				sx={{
					flexWrap: "wrap",
					px: 3,
					py: 2,
					m: -1,
				}}
			>
				<Button sx={{ m: 1 }} variant="outlined">
					Reiniciar &amp; Enviar la contraseña
				</Button>
				<Button sx={{ m: 1 }}>Ingreso como cliente</Button>
			</CardActions>
		</Card>
	);
};

CustomerBasicDetails.propTypes = {
	address1: PropTypes.string,
	address2: PropTypes.string,
	country: PropTypes.string,
	email: PropTypes.string.isRequired,
	isVerified: PropTypes.bool.isRequired,
	phone: PropTypes.string,
	state: PropTypes.string,
};

export default CustomerBasicDetails;
