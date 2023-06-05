import React from "react";
import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import {
	Avatar,
	Box,
	Button,
	Chip,
	Container,
	Divider,
	Grid,
	Tab,
	Tabs,
	Typography,
} from "@mui/material";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { customerApi } from "../../constants/customers";
import CustomerBasicDetails from "./CustomerBasicDetails";
import { CustomerDataManagement } from "./CustomerDataManagement";
import { CustomerEmailsSummary } from "./CustomerEmailsSumary";
import { CustomerInvoices } from "./CustomerInvoices";
import { CustomerPayment } from "./CustomerPayment";
import { CustomerLogs } from "./CustomerLogs";
import { useMounted } from "../../hooks/useMounted";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import { getInitials } from "../../utils/get-initials";

const tabs = [
	{ label: "Detalles", value: "details" },
	{ label: "Facturas", value: "invoices" },
	{ label: "Registros", value: "logs" },
];

export const CustomerDetails = () => {
	const isMounted = useMounted();
	const [customer, setCustomer] = useState(null);
	const [currentTab, setCurrentTab] = useState("details");

	const getCustomer = useCallback(async () => {
		try {
			const data = await customerApi.getCustomer();

			if (isMounted()) {
				setCustomer(data);
			}
		} catch (err) {
			console.error(err);
		}
	}, [isMounted]);

	useEffect(
		() => {
			getCustomer();
		},
		// eslint-disable-next-line react-hooks/exhaustive-deps
		[]
	);

	const handleTabsChange = (event, value) => {
		setCurrentTab(value);
	};

	if (!customer) {
		return null;
	}

	return (
		<>
			<Box component="main" className="box">
				<Container maxWidth="md">
					<div>
						<Box className="box">
							<Link href="/dashboard/customers" passHref>
								<ArrowBackIcon fontSize="small" />
								<Typography variant="subtitle2">Clientes</Typography>
							</Link>
						</Box>
						<Grid container justifyContent="space-between" spacing={3}>
							<Grid
								item
								sx={{
									alignItems: "center",
									display: "flex",
									overflow: "hidden",
								}}
							>
								<Avatar
									src={customer.avatar}
									sx={{
										height: 64,
										mr: 2,
										width: 64,
									}}
								>
									{getInitials(customer.name)}
								</Avatar>
								<div>
									<Typography variant="h4">{customer.email}</Typography>
									<Box
										sx={{
											display: "flex",
											alignItems: "center",
										}}
									>
										<Typography variant="subtitle2">ID:</Typography>
										<Chip label={customer.id} size="small" sx={{ ml: 1 }} />
									</Box>
								</div>
							</Grid>
							<Grid item sx={{ m: -1 }}>
								<Button
									endIcon={<ExpandMoreIcon fontSize="small" />}
									sx={{ m: 1 }}
									variant="contained"
								>
									Acciones
								</Button>
							</Grid>
						</Grid>
						<Tabs
							indicatorColor="primary"
							onChange={handleTabsChange}
							scrollButtons="auto"
							sx={{ mt: 3 }}
							textColor="primary"
							value={currentTab}
							variant="scrollable"
						>
							{tabs.map((tab) => (
								<Tab key={tab.value} label={tab.label} value={tab.value} />
							))}
						</Tabs>
					</div>
					<Divider />
					<Box sx={{ mt: 3 }}>
						{currentTab === "details" && (
							<Grid container spacing={3}>
								<Grid item xs={12}>
									<CustomerBasicDetails
										address1={customer.address1}
										address2={customer.address2}
										country={customer.country}
										email={customer.email}
										isVerified={!!customer.isVerified}
										phone={customer.phone}
										state={customer.state}
									/>
								</Grid>
								<Grid item xs={12}>
									<CustomerPayment />
								</Grid>
								<Grid item xs={12}>
									<CustomerEmailsSummary />
								</Grid>
								<Grid item xs={12}>
									<CustomerDataManagement />
								</Grid>
							</Grid>
						)}
						{currentTab === "invoices" && <CustomerInvoices />}
						{currentTab === "logs" && <CustomerLogs />}
					</Box>
				</Container>
			</Box>
		</>
	);
};

// export default CustomerDetails;
