INSERT INTO PUBLIC."user" ("email", "password", "avatar", "name", "role", "status") VALUES
	('diemtrang596@gmail.com', '123456789', NULL, 'Nguyen Thi Diem Trang', 'Admin', 'Active'),
	('nguyenthidiemtrang1996@gmail.com', '123456789', NULL, 'Diem Trang', 'Manager', 'Active'),
	('diemtrang.nguyen@tanvieta.co', '123456789', NULL, 'TrangNguyen', 'User', 'In Active');
	
INSERT INTO PUBLIC."project" ("title") VALUES
	('eprotal'),
	('ifs');
	
INSERT INTO PUBLIC."task" ("title", "project", "asign", "attach", "board", "priority", "status", "due_date", "original_estimate") VALUES
	('To access Schedule Details - Schedule Tab', 1, 1, NULL, NULL, 'Medium', 'TO DO', NULL, 10);