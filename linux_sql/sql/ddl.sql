-- connect to host_agent database
\c host_agent

-- make sure there is no previously created table
DROP TABLE IF EXISTS PUBLIC.host_usage;
DROP TABLE IF EXISTS PUBLIC.host_info;

-- create table to store hardware specs
CREATE TABLE PUBLIC.host_info
  (
     id               SERIAL NOT NULL,
     hostname         VARCHAR NOT NULL,
     cpu_number       INT NOT NULL,
     cpu_architecture VARCHAR NOT NULL,
     cpu_model        VARCHAR NOT NULL,
     cpu_mhz          FLOAT NOT NULL,
     L2_cache         INT NOT NULL,
     total_mem        INT NOT NULL,
     "timestamp"        TIMESTAMP NOT NULL,
     CONSTRAINT host_id PRIMARY KEY (id),
     CONSTRAINT hostname_uniq UNIQUE (hostname)
  );

-- create table to store resource usage data
CREATE TABLE PUBLIC.host_usage
  (
     "timestamp"    TIMESTAMP NOT NULL,
     host_id        SERIAL NOT NULL,
     memory_free    INT NOT NULL,
     cpu_idle       INT NOT NULL,
     cpu_kernel     INT NOT NULL,
     disk_io        INT NOT NULL,
     disk_available INT NOT NULL,
     CONSTRAINT host_usage_id FOREIGN KEY (host_id)
     REFERENCES host_info (id)
  );