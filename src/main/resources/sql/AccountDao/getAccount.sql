SELECT 
	a.id, a.name, b.id idTask, b.title, b.status, b.create_on
FROM "public"."user" a
JOIN task b
	ON a.id = b.asign