#!/bin/bash
# script usage
# ./scripts/psql_docker.sh start|stop|create [db_username][db_password]

# check if docker is running
systemctl status docker || systemctl start docker

# get latest postgres image
docker pull postgres

case $1 in
  create)
    # check if container is created
    if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 2 ]; then
      echo jrvs-psql container is already created
      exit 1
    fi

    # check if `db_username` or `db_password` is pass through as argument
    if [ $# -lt 2 ]; then
      echo Incorrect number of arguments. Please provide username and password
      exit 1
    fi

    # create `pgdata` volume
    docker volume create pgdate

    # create a psql container
    docker run --name jrvs-psql -e POSTGRES_PASSWORD="$2" -e POSTGRES_USER="$1" -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres

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
esac

exit 0