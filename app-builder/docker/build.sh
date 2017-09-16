#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
NAME=appbuilder-builder

while test $# -gt 0; do
    case "$1" in
        -d | --detach)
            detach=-d
            ;;
    esac
    shift
done

${DIR}/../../jms-integration-adapter/docker/build.sh ${detach} ;
${DIR}/../../templatemanager/docker/build.sh ${detach} ;

sleep 15

docker rm -f ${NAME}

docker run \
       ${detach} \
       -e DNSDOCK_ALIAS=${NAME}.docker \
       -h ${NAME}.docker \
       --name ${NAME} \
       -v ${DIR}/../.:/app \
       -v ~/.m2:/root/.m2 \
       builder mvn clean package
