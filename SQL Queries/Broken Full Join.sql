SELECT * from employees
LEFT JOIN manager ON employees.employeesID = manager.employeesID
UNION
SELECT * from employees
RIGHT JOIN Manager ON employees.employeesID = manager.employeesID 
