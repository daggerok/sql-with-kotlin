# sql-with-kotlin [![tests](https://github.com/daggerok/sql-with-kotlin/actions/workflows/tests.yml/badge.svg)](https://github.com/daggerok/sql-with-kotlin/actions/workflows/tests.yml)
This repository demonstrates one of many options to work in kotlin with MySQL: Spring Boot, Testcontainers, MySQL, Exposed

```bash
./mvnw
```

<!--

```bash
./mvnw -f docker -P down ; ./mvnw -f docker -P up ; ./mvnw -f docker -P logs &   
```

or

```bash
if [[ "" != `docker ps -aq` ]] ; then docker rm -f -v `docker ps -aq` ; fi ;

docker run --rm --name mysql --platform linux/x86_64 \
           --health-cmd='mysqladmin ping -h 127.0.0.1 -u $MYSQL_USER --password=$MYSQL_PASSWORD || exit 1' \
           --health-start-period=1s --health-retries=1111 --health-interval=1s --health-timeout=5s \
           -e MYSQL_USER=user -e MYSQL_PASSWORD=password \
           -e MYSQL_DATABASE=database -e MYSQL_ROOT_PASSWORD=password \
           -p 3306:3306 \
           -d mysql:8.0.24 ;

while [[ $(docker ps -n 1 -q -f health=healthy -f status=running | wc -l) -lt 1 ]] ; do sleep 3 ; echo -n '.' ; done ; sleep 15; echo 'MySQL is ready.'
```
-->

### Reference Documentation
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.0/maven-plugin/reference/html/#build-image)
* [Testcontainers](https://www.testcontainers.org/)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#appendix.configuration-metadata.annotation-processor)
