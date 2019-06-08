INSERT INTO PUBLIC."user" ("email", "password", "avatar", "name", "role", "status") VALUES
	('diemtrang596@gmail.com', '123456789', NULL, 'Nguyen Thi Diem Trang', 'Admin', 'Active'),
	('nguyenthidiemtrang1996@gmail.com', '123456789', NULL, 'Diem Trang', 'Manager', 'Active'),
	('diemtrang.nguyen@tanvieta.co', '123456789', NULL, 'TrangNguyen', 'User', 'In Active');
	
INSERT INTO PUBLIC."project" ("title") VALUES
	('eProtal'),
	('IFS');
	
INSERT INTO PUBLIC."task" ("title", "project", "asign", "attach", "board", "priority", "status", "due_date", "original_estimate") VALUES
	('To access Schedule Details - Schedule Tab', 1, 1, NULL, NULL, 'Medium', 'To Do', NULL, 10),
	('Updated SO value is not shown in the SO listing', 1, 1, NULL, NULL, 'Medium', 'Word In Progress', NULL, NULL),
	('Loan FR - To remove helptext shown when mouse over', 1, 2, NULL, NULL, 'Medium', 'In Review', NULL, 10),
	('Consolidated Report - Outstanding / FIU', 1, 2, NULL, NULL, 'Medium', 'Verify', NULL, 10),
	('View the details of ACL', 1, 3, NULL, NULL, 'Medium', 'To Do', NULL, 10),
	('Invoice: Action - PUG', 2, 1, NULL, NULL, 'Medium', 'Word In Progress', NULL, 10),
	('Fields and Layout for LCTR CA', 2, 1, NULL, NULL, 'Medium', 'Word In Progress', NULL, 10),
	('Redemption : Settlement Date can only be back dated to current month', 1, 1, NULL, NULL, 'Medium', 'To Do', NULL, 10),
	('Redemption : Notice Date cant be future Dated', 2, 1, NULL, NULL, 'Medium', 'In Review', NULL, 10),
	('Invoice and Client Account: New Fields', 2, 1, NULL, NULL, 'Medium', 'Done', NULL, 10);