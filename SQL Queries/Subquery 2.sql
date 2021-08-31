select customer.FirstName,customer.LastName,loyaltyprogram.customerID,loyaltyprogram.Have500points
from customer
right join loyaltyprogram on customer.customerID = loyaltyprogram.customerID where Have500points=true
order by loyaltyprogram.Have500points 


