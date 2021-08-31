SELECT register.TransactionsID,shirts.shirtID, shirts.shirtcost, register.customerID
FROM shirts
RIGHT JOIN register ON shirts.shirtID = register.shirtID
ORDER BY shirts.shirtID;



