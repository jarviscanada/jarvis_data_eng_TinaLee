-- Group hosts by CPU number and sort by their memory size in descending order
SELECT
            AVG(cpu_number) OVER (PARTITION BY cpu_number) as cpu_number,
            id AS host_id,
            total_mem
FROM
    host_info AS info
ORDER BY total_mem DESC;

-- Helper function
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN data_trunc('hour', ts) + data_part('minute', ts):: int / 5 * interval '5 min';
END
$$
    LANGUAGE PLPGSQL;

--
SELECT
    use.host_id,
    hostname AS host_name,
    round5(use.timestamp) as timestamp,
    AVG((total_mem - memory_free)/total_mem * 100) AS avg_used_mem_percentage
FROM
    host_info AS info
        INNER JOIN host_usage AS use
                   ON info.id = use.host_id
GROUP BY
    use.host_id,
    hostname,
    round5(use.timestamp)
ORDER BY
    host_id ASC,
    timestamp ASC;

-- Detect Server Failure
SELECT
    host_id,
    round5(timestamp),
    count(*) AS num_data_points
FROM
    host_usage
GROUP BY
    host_id,
    round5(timestamp)
HAVING
        count(*) < 3;
