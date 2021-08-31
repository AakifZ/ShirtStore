SELECT online_order.online_OrderID AS OnlineOrder, shirts.shirtName AS ShirtName, online_order.shirt_color AS ShirtColor
FROM online_order
INNER JOIN shirts ON online_order.shirtID=shirts.ShirtID;





