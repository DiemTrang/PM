SELECT 
	a.id, b.title, a.title, a.status, c."name" 
FROM task a 
JOIN project b 
	ON a.project = b.id 
JOIN "public"."user" c 
	ON a.asign = c."id"