# Usando Tomcat 9 com JDK 17
FROM tomcat:9-jdk17

# Removendo aplicações padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiando o WAR compilado para o Tomcat
COPY dist/TgBurnout.war /usr/local/tomcat/webapps/ROOT.war

# Expondo a porta padrão do Tomcat
EXPOSE 8080

# Iniciando o Tomcat
CMD ["catalina.sh", "run"]
