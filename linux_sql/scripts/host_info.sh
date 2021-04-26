# Assign command line arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

specs="$lscpu"

# helper function to fetch hardware information
fetch_info() {
  echo "$specs" | grep -E "$1" | awk -F ':' '{print $2}' | xargs
}

hostname=$(hostname -f)
cpu_number=$(fetch_info "^CPU\(s\):")
cpu_architecture=$(fetch_info "^Arch")
cpu_model=$(fetch_info "^Model name")
cpu_mhz=$(fetch_info "^CPU MHz")
l2_cache=$(fetch_info "^L2" | sed "s/K//")
total_mem=$(cat /proc/meminfo | grep -E "^MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(date "+%Y-%m-%d %T")

# Insert statement into psql
statement="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp)
          VALUES ($hostname, $cpu_number, $cpu_architecture, $cpu_model, $cpu_mhz, $l2_cache, $total_mem, $timestamp);"

psql -h "$psql_host" -U "$psql_user" -d "$db_name" -p "$psql_port" -c "$statement"

exit 0;