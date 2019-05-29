SELECT 
	a.id, b.title project, a.title taskName, a.status, c.name asign
FROM task a 
INNER JOIN project b 
	ON a.project = b.id 
INNER JOIN "public".user c 
	ON a.asign = c."id" 
