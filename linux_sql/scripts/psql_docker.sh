#!/bin/bash
# script usage
# ./scripts/psql_docker.sh start|stop|create [db_username][db_password]

# set up arguments
command=$1
username=$2
password=$3

# validate arguments
if [ $# -ge 4 ] || [ $# -lt 1 ]; then
  echo "Illegal number of parameters"
  exit 1
fi

# check if docker is running
systemctl status docker || systemctl start docker

# get latest postgres image
docker pull postgres

case $command in
  create)
    # check if container is created
    if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 2 ]; then
      echo jrvs-psql container is already created
      exit 1
    fi

    # check if `db_username` or `db_password` is pass through as argument
    if [ $# -ne 3 ]; then
      echo Incorrect number of arguments. Please provide username and password
      exit 1
    fi

    # check if volume exist
    # create `pgdata` volume
    if [ $(docker volume ls | awk '{print $2}' | egrep 'pgdata') == '' ]; then
      docker volume create pgdata
    fi

    # set password as env variable
    export PGPASSWORD=$password

    # create a psql container
    docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -e POSTGRES_USER="$username" -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres

    # check if container is created
    if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 2 ]; then
      echo jrvs-psql container is successfully created!
      exit 0
    fi
    exit $?
    ;;
  start)
    if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 1 ]; then
      echo jrvs-psql container is not created
      exit 1
    fi
    docker container start jrvs-psql
    ;;
  stop)
    if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 1 ]; then
      echo jrvs-psql container is not created
      exit 1
    fi
    docker container stop jrvs-psql
    ;;
  *)
    echo Please enter a valid command
    exit 1
esac

exit 0