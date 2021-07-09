-- Show table schema 
\d+ retail;

-- Show first 10 rows
SELECT * FROM retail limit 10;

-- Check # of records
SELECT count(*) FROM retail;

-- number of clients (e.g. unique client ID)
select count(distinct customer_id) from retail;

--invoice date range
select max(invoice_date), min(invoice_date) from retail;

--number of SKU/merchants
select count(distinct stock_code) from retail;

--calculate average invoice amount
select avg(s.total)
from (
	select invoice_no , sum(unit_price * quantity) as total
	from retail 
	group by invoice_no
) as s
where s.total > 0;

--calculate total revenue
select sum(unit_price * quantity) from retail;

--calculate total revenue by YYYYMM
select cast(extract(year from invoice_date) as Integer) * 100 
		+ cast(extract(month from invoice_date) as Integer) as yyyymm,
		sum(unit_price * quantity)
from retail
group by yyyymm
order by yyyymm ASC;  
