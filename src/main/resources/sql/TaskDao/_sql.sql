SELECT 
	a.id, b.title project, a.title tt_name, a.status, c.name assign_name
FROM task a 
INNER JOIN project b 
	ON a.project = b.id 
INNER JOIN "public".user c 
	ON a.asign = c."id" 
