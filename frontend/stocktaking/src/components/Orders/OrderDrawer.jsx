import { useState } from "react";
import PropTypes from "prop-types";
import { format } from "date-fns";
import numeral from "numeral";
import {
	Box,
	Button,
	Divider,
	Drawer,
	IconButton,
	Table,
	TableBody,
	TableCell,
	TableHead,
	TableRow,
	TextField,
	Typography,
} from "@mui/material";
import { styled } from "@mui/material/styles";
import EditIcon from "@mui/icons-material/Edit";
import CloseIcon from "@mui/icons-material/Close";
import { PropertyList } from "../Selectors/PropertyList";
import { PropertyListItem } from "../Selectors/PropertyListItem";
import { Scrollbar } from "../ScrollBar/ScrollBar";

const statusOptions = [
	{
		label: "Cancelado",
		value: "canceled",
	},
	{
		label: "Completo",
		value: "complete",
	},
	{
		label: "Pendiente",
		value: "pending",
	},
	{
		label: "Rechazado",
		value: "rejected",
	},
];

const OrderPreview = (props) => {
	const { lgUp, onApprove, onEdit, onReject, order } = props;
	const align = lgUp ? "horizontal" : "vertical";

	return (
		<>
			<Box
				sx={{
					alignItems: "center",
					backgroundColor: (theme) =>
						theme.palette.mode === "dark" ? "neutral.800" : "neutral.100",
					borderRadius: 1,
					display: "flex",
					flexWrap: "wrap",
					justifyContent: "space-between",
					px: 3,
					py: 2.5,
				}}
			>
				<Typography color="textSecondary" sx={{ mr: 2 }} variant="overline">
					Acciones
				</Typography>
				<Box
					sx={{
						alignItems: "center",
						display: "flex",
						flexWrap: "wrap",
						m: -1,
						"& > button": {
							m: 1,
						},
					}}
				>
					<Button onClick={onApprove} size="small" variant="contained">
						Aprovado
					</Button>
					<Button onClick={onReject} size="small" variant="outlined">
						Rechazado
					</Button>
					<Button
						onClick={onEdit}
						size="small"
						startIcon={<EditIcon fontSize="small" />}
					>
						Editado
					</Button>
				</Box>
			</Box>
			<Typography sx={{ my: 3 }} variant="h6">
				Detalles
			</Typography>
			<PropertyList>
				<PropertyListItem
					align={align}
					disableGutters
					label="ID"
					value={order.id}
				/>
				<PropertyListItem
					align={align}
					disableGutters
					label="Número"
					value={order.number}
				/>
				<PropertyListItem align={align} disableGutters label="Cliente">
					<Typography color="primary" variant="body2">
						{order.customer.name}
					</Typography>
					<Typography color="textSecondary" variant="body2">
						{order.customer.address1}
					</Typography>
					<Typography color="textSecondary" variant="body2">
						{order.customer.city}
					</Typography>
					<Typography color="textSecondary" variant="body2">
						{order.customer.country}
					</Typography>
				</PropertyListItem>
				<PropertyListItem
					align={align}
					disableGutters
					label="Fecha"
					value={format(order.createdAt, "dd/MM/yyyy HH:mm")}
				/>
				<PropertyListItem
					align={align}
					disableGutters
					label="Código de promoción"
					value={order.promotionCode}
				/>
				<PropertyListItem
					align={align}
					disableGutters
					label="Cantidad total"
					value={`${order.currency}${order.totalAmount}`}
				/>
				<PropertyListItem
					align={align}
					disableGutters
					label="Estado"
					value={order.status}
				/>
			</PropertyList>
			<Divider sx={{ my: 3 }} />
			<Typography sx={{ my: 3 }} variant="h6">
				Items
			</Typography>
			<Scrollbar>
				<Table sx={{ minWidth: 400 }}>
					<TableHead>
						<TableRow>
							<TableCell>Descripción</TableCell>
							<TableCell>Ciclo de facturación</TableCell>
							<TableCell>Cantidad</TableCell>
						</TableRow>
					</TableHead>
					<TableBody>
						{(order.items || []).map((item) => (
							<TableRow key={item.id}>
								<TableCell>
									{item.name} x {item.quantity}
								</TableCell>
								<TableCell>{item.billingCycle}</TableCell>
								<TableCell>
									{numeral(item.unitAmount).format(`${item.currency}0,0.00`)}
								</TableCell>
							</TableRow>
						))}
					</TableBody>
				</Table>
			</Scrollbar>
		</>
	);
};

const OrderForm = (props) => {
	const { onCancel, onSave, order } = props;

	return (
		<>
			<Box
				sx={{
					alignItems: "center",
					backgroundColor: (theme) =>
						theme.palette.mode === "dark" ? "neutral.800" : "neutral.100",
					borderRadius: 1,
					display: "flex",
					flexWrap: "wrap",
					justifyContent: "space-between",
					px: 3,
					py: 2.5,
				}}
			>
				<Typography variant="overline" sx={{ mr: 2 }} color="textSecondary">
					Ordenes
				</Typography>
				<Box
					sx={{
						alignItems: "center",
						display: "flex",
						m: -1,
						"& > button": {
							m: 1,
						},
					}}
				>
					<Button
						color="primary"
						onClick={onSave}
						size="small"
						variant="contained"
					>
						Guardar cambios
					</Button>
					<Button onClick={onCancel} size="small" variant="outlined">
						Cancelar
					</Button>
				</Box>
			</Box>
			<Typography sx={{ my: 3 }} variant="h6">
				Detalles
			</Typography>
			<TextField
				disabled
				fullWidth
				label="ID"
				margin="normal"
				name="id"
				value={order.id}
			/>
			<TextField
				disabled
				fullWidth
				label="Número"
				margin="normal"
				name="number"
				value={order.number}
			/>
			<TextField
				disabled
				fullWidth
				label="Nombre del cliente"
				margin="normal"
				name="customer_name"
				value={order.customer.name}
			/>
			<TextField
				disabled
				fullWidth
				label="Fecha"
				margin="normal"
				name="date"
				value={format(order.createdAt, "dd/MM/yyyy HH:mm")}
			/>
			<TextField
				fullWidth
				label="Dirección"
				margin="normal"
				name="address"
				value={order.customer.address1}
			/>
			<TextField
				fullWidth
				label="Country"
				margin="normal"
				name="country"
				value={order.customer.country}
			/>
			<TextField
				fullWidth
				label="State/Region"
				margin="normal"
				name="state_region"
				value={order.customer.city}
			/>
			<TextField
				fullWidth
				label="Total Amount"
				margin="normal"
				name="amount"
				value={order.totalAmount}
			/>
			<TextField
				fullWidth
				label="Status"
				margin="normal"
				name="status"
				select
				SelectProps={{ native: true }}
				value={order.status}
			>
				{statusOptions.map((statusOption) => (
					<option key={statusOption.value} value={statusOption.value}>
						{statusOption.label}
					</option>
				))}
			</TextField>
			<Button color="error" sx={{ mt: 3 }}>
				Delete order
			</Button>
		</>
	);
};

// const OrderDrawerDesktop = styled(Drawer)({
//   width: 500,
//   flexShrink: 0,
//   '& .MuiDrawer-paper': {
//     position: 'relative',
//     width: 500
//   }
// });

const OrderDrawerMobile = styled(Drawer)({
	flexShrink: 0,
	maxWidth: "100%",
	height: "calc(100% - 64px)",
	width: 500,
	"& .MuiDrawer-paper": {
		height: "calc(100% - 64px)",
		maxWidth: "100%",
		top: 64,
		width: 500,
	},
});

export const OrderDrawer = (props) => {
	const { containerRef, onClose, open, order, ...other } = props;
	const [isEditing, setIsEditing] = useState(false);

	const handleEdit = () => {
		setIsEditing(true);
	};

	const handleCancel = () => {
		setIsEditing(false);
	};

	// The reason for doing this, is that the persistent drawer has to be rendered, but not it's
	// content if an order is not passed.
	const content = order ? (
		<>
			<Box
				sx={{
					alignItems: "center",
					backgroundColor: "primary.main",
					color: "primary.contrastText",
					display: "flex",
					justifyContent: "space-between",
					px: 3,
					py: 2,
				}}
			>
				<Typography color="inherit" variant="h6">
					{order.number}
				</Typography>
				<IconButton color="inherit" onClick={onClose}>
					<CloseIcon fontSize="small" />
				</IconButton>
			</Box>
			<Box
				sx={{
					px: 3,
					py: 4,
				}}
			>
				{!isEditing ? (
					<OrderPreview
						onApprove={onClose}
						onEdit={handleEdit}
						onReject={onClose}
						order={order}
					/>
				) : (
					<OrderForm
						onCancel={handleCancel}
						onSave={handleCancel}
						order={order}
					/>
				)}
			</Box>
		</>
	) : null;

	return (
		<OrderDrawerMobile
			anchor="right"
			ModalProps={{ container: containerRef?.current }}
			onClose={onClose}
			open={open}
			SlideProps={{ container: containerRef?.current }}
			variant="temporary"
			{...other}
		>
			{content}
		</OrderDrawerMobile>
	);
};

OrderDrawer.propTypes = {
	containerRef: PropTypes.any,
	onClose: PropTypes.func,
	open: PropTypes.bool,
	order: PropTypes.object,
};
