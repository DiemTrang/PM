SELECT 
	a.id, b.title project, a.title nameTask, a.status, c."name" asign, a.priority, a.due_date, 
	a.original_estimate, a.decription, a.start_date, a.end_date, d.name createdBy, a.create_on, 
	e.name modifyBy, a.modify_on 
FROM task a 
JOIN project b 
	ON a.project = b.id 
JOIN "public".user c 
	ON a.asign = c.id 
JOIN "public".user d 
	ON a.create_by = d.id 
	JOIN "public".user e 
	ON a.modify_by = e.id 
