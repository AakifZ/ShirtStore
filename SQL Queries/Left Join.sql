SELECT inventory.Brand,shirts.shirtColor,inventory.ShirtID
FROM inventory
LEFT JOIN shirts
ON inventory.shirtID = shirts.shirtID;

