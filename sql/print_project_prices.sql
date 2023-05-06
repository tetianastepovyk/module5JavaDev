--Завдання №7 - вивести вартість кожного проєкту
SELECT project.ID AS NAME, SUM(worker.SALARY * DATEDIFF(MONTH, START_DATE, FINISH_DATE)) AS PRICE
FROM project
JOIN project_worker ON project_worker.PROJECT_ID = project.ID
JOIN worker ON worker.ID = project_worker.WORKER_ID
GROUP BY project.ID
ORDER BY PRICE DESC;



