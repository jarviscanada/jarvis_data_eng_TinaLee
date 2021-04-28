# Introduction
The Cluster monitoring Agent is designed to track and parse hardware information and resource usage of clustered nodes/servers 
managed by the Jarvis Cluster Administration (LCA). Each server is running CentOS 7 and the servers communicate 
internally through IPv4 addresses. Bash scripts are built to automatically parse resource usage data every minute and 
the data parsed will be safely transferred and stored to a PostgresSQL database, which is a database instance created 
by Docker Postgres image. Later, the LCA team will use the data to perform data analysis and future resource planning.

# Quick Start
This guide walks you through the cluster monitoring solution. \
(Note: Docker and psql CLI client must be downloaded before executing the following code) 
1. Create database and tables
```
# Start a psql instance using psql_docker.sh
./scripts/psql_docker.sh create [db_username] [db_password]

# Navigate into psql instance using psql cli
psql -h localhost -U postgres

# Now you should be in psql REPL 
# Create database (type \q to quit psql REPL)
CREATE DATABASE host_agent;

# You should now be exited from psql REPL
# Create tables to store hardware specification data and resource usage data
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
```
2. Insert hardware specs into psql 
* We can now collect hardware information from a server and store it into `host_info` table inside `host_agent` database.
    * `psql_host`: host name of the machine which your server is running.
    * `psql_port`: port server is listening, default is 5432
    * `db_name`: host_agent
    * `psql_user`: user username
    * `psql_password`: user password
```
./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
```
3. Insert resource usage data into psql
    Stores resource usage data of server and store it into `host_usage` table inside `host_agent`.
```
./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
```
4. Crontab setup
    Set up crontab to parse resource usage evey 1 minute.
```
# edit crontab tasks 
crontab -e

# add this to crontab
* * * * * bash path/host_usage.sh  psql_host psql_port db_name psql_user psql_password > /tmp/host_usage.log

# list crontab jobs
crontab -l

# you can verify that crontab is running 
cat /tmp/host_usage.log
```

# Implementation
1. Created a bash script `psql_docker.sh` to provision a PSQL instance using docker.
1. Then `ddl.sql` is created to initialize database `host_agent` and tables `host_info` and `host_usage`
1. After the database is successfully created, `host_info.sh` and `host_usage` are implemented to insert hardware 
   specification data and usage information of the server.
1. Used `crontab` command to parse resource usage and send data to database every one minute .
1. Created a `queries.sql` to query data from database for usage information and failure detection.
## Architecture
This diagram shows communication between clustered servers through a switch and the process of data storage into a 
PostgresSQL database. Each server will have its copy of bash shell scripts that automatically parse resource usage 
and store data into a database.

![Linux_sql_architecture](./assets/Linux_SQL_Architecture.png)

## Scripts
* `psql_docker.sh` is for setting up a psql instance using docker. This script allows the user to create, start, or stop
  a psql instance.
```
# Create a psql instance
./script/psql_docker.sh create [db_username] [db_password]

# Start the psql container
./script/psql_docker.sh start

# Stop the psql container
./script/psql_docker.sh stop
```
* `ddl.sql` is for initialization of database and tables. The script used to create `host_agent` database in postgres
  sql. Then `host_info`  and `host_usage` will be created in `host_agent` database.
```
# Create host_info table and host usage table
psql -h localhost -U postgres -d host_agent -f ./sql/ddl.sql
```  
* `host_info.sh` is a bash script that will be installed on each server to parse hardware specification data.
```
./script/host_info.sh psql_host psql_port db_name psql_user psql_user psql_password
```
* `host_usage.sh` is a bash script that will be installed on each server to parse resource usage data.
```
./script/host_usage.sh psql_host psql_port db_name psql_user psql_user psql_password
```
* `queries.sql` contains three queries. First query groups the hosts by CPU numbers and sort them by total memory size 
in descending order. Second query returns average used memory over 5 minute interval for every host. Lastly, third query
  detects host failure. These queries would assist the LCA team in analyzing and managing the cluster of servers.
```
psql -h localhost -U postgres -d host_agent -f ./sql/queries.sql
```  
## Database Modeling
* `host_info` table

Column Name | Data Type | Description
------------ | ------------- | -----------
id | SERIAL | Primary key in the table
hostname | VARCHAR | Name of the server. Each server must have a unique name
cpu_number | INT | Number of CPUs in the server
cpu_architecture | VARCHAR | Architecture information of the server
cpu_model | VARCHAR | CPU Model of the server
cpu_mhz | FLOAT | CPU mhz
L2_cache | INT | Indicate L2 cache information of the server
total_mem | INT | Indicate total memory information of the server 
timestamp | TIMESTAMP | Date and time when host_info.sh is executed
  
* `host_usage` table

Column Name | Data Type | Description
------------|-----------|------------
timestamp | TIMESTAMP | Date and time
host_id | INT | id of the server, references id in `host_info`
memory_free | INT | Free memory on the server in MB
cpu_idle | INT | Time spent in idle (percentage)
cpu_kernel | INT | Time spent running kernel code (percentage)
disk_io | INT | Number of Input/Output of the server
disk_available | INT | Available disk of root directory in MB 

# Test
* For bash scripts, they're tested manually by executing them in terminal and all the testing results are as expected.
* `ddl.sql` script is manually tested by executing it in the terminal and checking if database and tables were 
  successfully created.
* `queries.ddl` is tested by inserting sample data into the tables and calling each query independently in psql REPL.


# Improvements
* Send out alert when certain resource is overused
* Generate resource usage report every hour 
* Monitor internal connections between the servers