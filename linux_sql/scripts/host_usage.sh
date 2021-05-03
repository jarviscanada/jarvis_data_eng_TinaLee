#!/bin/bash

# Assign arguments to variables
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# validate arguments
if [ $# -ne 5 ]; then
  echo "Invalid number of arguments. Please enter host, port number, database name, username and password."
  exit 1
fi

# Helper for fetching usage data
fetch_data() {
  vmstat -t | tail -1 | awk "$1" | xargs
}

# Get resource usage data
timestamp=$(date "+%Y-%m-%d %T")
memory_free=$(fetch_data "{print \$4}")
cpu_idle=$(fetch_data "{print \$15}")
cpu_kernel=$(fetch_data "{print \$14}")
disk_io=$(vmstat -d | tail -1 | awk '{print $10}' | xargs)
disk_available=$(df -BM / | tail -1 | awk '{print $4}' | sed "s/M//" | xargs)

# Insert statement into psql
hostname=$(hostname -f)
inst_statement="INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
                VALUES ('${timestamp}', (SELECT id FROM host_info WHERE hostname='${hostname}'), '${memory_free}', '${cpu_idle}', '${cpu_kernel}', '${disk_io}', '${disk_available}');"

export PGPASSWORD=$psql_password
psql -h $psql_host -U $psql_user -d $db_name -p $psql_port -c "$inst_statement"

exit $?