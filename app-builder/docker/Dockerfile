FROM ubuntu:14.04
RUN locale-gen ru_RU.UTF-8

RUN apt-get update
RUN apt-get install -y software-properties-common
RUN add-apt-repository -y ppa:webupd8team/java 
RUN apt-get update
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
RUN apt-get install -y oracle-java8-installer

RUN mkdir -p /app
WORKDIR /app

CMD java -jar appbuilder.jar

