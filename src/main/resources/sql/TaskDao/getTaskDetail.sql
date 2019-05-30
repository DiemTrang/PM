SELECT 
	a.id, b.title project, a.title nameTask, a.status, c."name" asign, a.priority, a.due_date, 
	a.original_estimate, a.decription, a.start_date, a.end_date, d.createBy, a.create_on, e.modifyBy, a.modify_on 
FROM task a 
JOIN project b 
	ON a.project = b.id 
JOIN "public".user c 
	ON a.asign = c.id 
JOIN(SELECT 
		a.id, a.title, c.name createBy 
	FROM task a 
	JOIN "public".user c 
		ON a.create_by = c.id 
	)d 
ON a.id = d.id 
JOIN(SELECT
		a.id, a.title, c.name modifyBy
	FROM task a
	JOIN "public".user c
		ON a.modify_by = c.id
	)e
ON a.id = e.id