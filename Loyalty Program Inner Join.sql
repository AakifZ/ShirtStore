SELECT customer.FirstName, Customer.LastName,loyaltyprogram.customerID,loyaltyprogram.loyaltyID
FROM Customer
INNER JOIN loyaltyprogram ON loyaltyprogram.customerID=customer.customerID