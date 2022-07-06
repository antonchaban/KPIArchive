FROM tomcat:8.0-alpine
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
#RUN ["javac", "-cp", ".:/usr/local/tomcat/lib/servlet-api.jar", "-d", "/usr/local/tomcat/webapps/myApp/WEB-INF/classes/", "/usr/local/tomcat/webapps/myApp/src/TestingServlet.java"]
COPY . /usr/src/app
#RUN ["javac", "-cp", ".:/usr/local/tomcat/lib/servlet-api.jar", "-d", "/usr/local/tomcat/webapps/myApp/WEB-INF/classes/", "/usr/local/tomcat/webapps/myApp/src/ContactFormServlet.java"]

EXPOSE 8080
CMD ["catalina.sh", "run"]