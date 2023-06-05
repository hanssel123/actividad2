import React, { useCallback, useEffect, useRef, useState } from "react";
import {
	Box,
	Card,
	Container,
	Divider,
	InputAdornment,
	Tab,
	Tabs,
	TextField,
} from "@mui/material";
import { customerApi } from "../../constants/customers";
import { tabs, sortOptions } from "../../constants/types";
import { CustomerListTable } from "./List";
import { useMounted } from "../../hooks/useMounted";
import SearchIcon from "@mui/icons-material/Search";

const applyFilters = (customers, filters) =>
	customers.filter((customer) => {
		if (filters.query) {
			let queryMatched = false;
			const properties = ["email", "name"];

			properties.forEach((property) => {
				if (
					customer[property].toLowerCase().includes(filters.query.toLowerCase())
				) {
					queryMatched = true;
				}
			});

			if (!queryMatched) {
				return false;
			}
		}

		if (filters.hasAcceptedMarketing && !customer.hasAcceptedMarketing) {
			return false;
		}

		if (filters.isProspect && !customer.isProspect) {
			return false;
		}

		if (filters.isReturning && !customer.isReturning) {
			return false;
		}

		return true;
	});

const descendingComparator = (a, b, sortBy) => {
	// When compared to something undefined, always returns false.
	// This means that if a field does not exist from either element ('a' or 'b') the return will be 0.

	if (b[sortBy] < a[sortBy]) {
		return -1;
	}

	if (b[sortBy] > a[sortBy]) {
		return 1;
	}

	return 0;
};

const getComparator = (sortDir, sortBy) =>
	sortDir === "desc"
		? (a, b) => descendingComparator(a, b, sortBy)
		: (a, b) => -descendingComparator(a, b, sortBy);

const applySort = (customers, sort) => {
	const [sortBy, sortDir] = sort.split("|");
	const comparator = getComparator(sortDir, sortBy);
	const stabilizedThis = customers.map((el, index) => [el, index]);

	stabilizedThis.sort((a, b) => {
		const newOrder = comparator(a[0], b[0]);

		if (newOrder !== 0) {
			return newOrder;
		}

		return a[1] - b[1];
	});

	return stabilizedThis.map((el) => el[0]);
};

const applyPagination = (customers, page, rowsPerPage) =>
	customers.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage);

export const CustomerList = () => {
	const isMounted = useMounted();
	const queryRef = useRef(null);
	const [customers, setCustomers] = useState([]);
	const [currentTab, setCurrentTab] = useState("all");
	const [page, setPage] = useState(0);
	const [rowsPerPage, setRowsPerPage] = useState(10);
	const [sort, setSort] = useState(sortOptions[0].value);
	const [filters, setFilters] = useState({
		query: "",
		hasAcceptedMarketing: undefined,
		isProspect: undefined,
		isReturning: undefined,
	});

	const getCustomers = useCallback(async () => {
		try {
			const data = await customerApi.getCustomers();

			if (isMounted()) {
				setCustomers(data);
			}
		} catch (err) {
			console.error(err);
		}
	}, [isMounted]);

	useEffect(
		() => {
			getCustomers();
		},
		// eslint-disable-next-line react-hooks/exhaustive-deps
		[]
	);

	const handleTabsChange = (event, value) => {
		const updatedFilters = {
			...filters,
			hasAcceptedMarketing: undefined,
			isProspect: undefined,
			isReturning: undefined,
		};

		if (value !== "all") {
			updatedFilters[value] = true;
		}

		setFilters(updatedFilters);
		setCurrentTab(value);
	};

	const handleQueryChange = (event) => {
		event.preventDefault();
		setFilters((prevState) => ({
			...prevState,
			query: queryRef.current?.value,
		}));
	};

	const handleSortChange = (event) => {
		setSort(event.target.value);
	};

	const handlePageChange = (event, newPage) => {
		setPage(newPage);
	};

	const handleRowsPerPageChange = (event) => {
		setRowsPerPage(parseInt(event.target.value, 10));
	};

	// Usually query is done on backend with indexing solutions
	const filteredCustomers = applyFilters(customers, filters);
	const sortedCustomers = applySort(filteredCustomers, sort);
	const paginatedCustomers = applyPagination(
		sortedCustomers,
		page,
		rowsPerPage
	);

	return (
		<>
			<Box
				component="main"
				sx={{
					flexGrow: 1,
					py: 8,
				}}
			>
				<Container maxWidth="xl">
					<Card>
						<Tabs
							indicatorColor="primary"
							onChange={handleTabsChange}
							scrollButtons="auto"
							sx={{ px: 3 }}
							textColor="primary"
							value={currentTab}
							variant="scrollable"
						>
							{tabs.map((tab) => (
								<Tab key={tab.value} label={tab.label} value={tab.value} />
							))}
						</Tabs>
						<Divider />
						<Box
							sx={{
								alignItems: "center",
								display: "flex",
								flexWrap: "wrap",
								m: -1.5,
								p: 3,
							}}
						>
							<Box
								component="form"
								onSubmit={handleQueryChange}
								sx={{
									flexGrow: 1,
									m: 1.5,
								}}
							>
								<TextField
									defaultValue=""
									fullWidth
									inputProps={{ ref: queryRef }}
									InputProps={{
										startAdornment: (
											<InputAdornment position="start">
												<SearchIcon fontSize="small" />
											</InputAdornment>
										),
									}}
									placeholder="Buscar clientes"
								/>
							</Box>
							<TextField
								label="Sort By"
								name="sort"
								onChange={handleSortChange}
								select
								SelectProps={{ native: true }}
								sx={{ m: 1.5 }}
								value={sort}
							>
								{sortOptions.map((option) => (
									<option key={option.value} value={option.value}>
										{option.label}
									</option>
								))}
							</TextField>
						</Box>
						<CustomerListTable
							customers={paginatedCustomers}
							customersCount={filteredCustomers.length}
							onPageChange={handlePageChange}
							onRowsPerPageChange={handleRowsPerPageChange}
							rowsPerPage={rowsPerPage}
							page={page}
						/>
					</Card>
				</Container>
			</Box>
		</>
	);
};

// export default CustomerList;
