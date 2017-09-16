#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
NAME=backend

while test $# -gt 0; do
    case "$1" in
        -d | --detach)
            detach=-d
            ;;
    esac
    shift
done

if [[ "$(docker images -q ${NAME} 2> /dev/null)" == "" ]]; then
    ${DIR}/image-build.sh ;
fi

if [ ! -f ${DIR}/../target/${NAME}.jar ]; then
    ${DIR}/build.sh ;
fi

docker rm -f ${NAME}

docker run \
  ${detach} \
  -e DNSDOCK_ALIAS=${NAME}.docker \
  -h ${NAME}.docker \
  --name ${NAME} \
  -v ${DIR}/../target:/app \
  ${NAME}
