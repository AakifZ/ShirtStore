select transaction.FirstName, transaction.LastName, max(transactionID) AS Latest_Transaction from transaction 