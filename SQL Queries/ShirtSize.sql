select shirtSize, Count(*) AS Total 
FROM shirts
group by shirtSize
