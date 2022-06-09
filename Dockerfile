FROM openjdk:11
EXPOSE 8080 8000
ADD target/payroll-service.jar payroll-service.jar
ADD entrypoint.sh entrypoint.sh
ENTRYPOINT ["sh","/entrypoint.sh"]
