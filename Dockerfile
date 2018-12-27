FROM 192.168.14.171:5000/java:8-jre
MAINTAINER wanggx <g.wanggx@ffcs.cn>
ADD ./target/salt-api-server.jar /app/
ENV JAVA_OPTS ""
ENTRYPOINT ["java","-jar","/app/salt-api-server.jar"]
