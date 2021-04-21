#!/bin/bash
# script usage
# ./scripts/psql_docker.sh start|stop|create [db_username][db_password]

#script pseudocode
#hint: `systemctl status docker || ...`
systemctl status docker || systemctl start docker

case $1 in
  create)
    # check if container is created
    if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 2 ]; then
      echo "`jrvs-psql` container is already created"
      exit 1
    fi

    # check if `db_username` or `db_password` is pass through as argument
    if [ $# -ne 2 ]; then
      echo "Incorrect number of arguments"
      exit 1
    fi

    # create `pgdate` volume
    docker volume create pgdate

    # create a psql container
    docker run --name jrvs-psql -e POSTGRES_PASSWORD=${db_password} -e POSTGRES_USER=${db_username} -d -v pgdate:/var/lib/postgresql/data -p 5432:5432 postgres

    # check if container is created
    if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 2 ]; then
      echo "`jrvs-psql` container has been successfully created!"
      exit 0
    fi
    ;;
  start)
    if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 1 ]; then
      echo "`jrvs-psql` container is not running"
      exit 1
    fi
    docker container start jrvs-psql
    ;;
  stop)
    if [ $(docker container ls -a -f name=jrvs-psql | wc -l) -eq 1 ]; then
      echo "`jrvs-psql` container is already stopped"
      exit 1
    fi
    docker container stop jrvs-psql
    ;;
esac

exit 0