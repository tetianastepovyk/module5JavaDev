--працівник з найбільшою заробітною платою
SELECT worker.NAME,worker.SALARY
FROM worker
WHERE SALARY = (
  SELECT MAX(SALARY)
  FROM worker
);