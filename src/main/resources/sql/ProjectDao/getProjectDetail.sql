SELECT 
	a.id, a.title project, b.title, b.status, c."name" 
FROM "public".project a 
JOIN task b 
	ON b.project = a.id 
JOIN "public"."user" c 
	ON b.asign = c."id"