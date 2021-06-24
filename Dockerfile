FROM alpine:3.5

#Create Ant Dir in Docker container
RUN mkdir -p /opt/ant/
#Download Ant 1.9.8
RUN wget http://archive.apache.org/dist/ant/binaries/apache-ant-1.9.8-bin.tar.gz -P /opt/ant
#Unpack Apache Ant for Java builds
RUN tar -xvzf /opt/ant/apache-ant-1.9.8-bin.tar.gz -C /opt/ant/
# Remove tar file
RUN rm -f /opt/ant/apache-ant-1.9.8-bin.tar.gz
#Add Sonarqube lib
RUN wget http://downloads.sonarsource.com/plugins/org/codehaus/sonar-plugins/sonar-ant-task/2.3/sonar-ant-task-2.3.jar -P /opt/ant/apache-ant-1.9.8/lib/
#Install JDK 1.8
RUN apk --update add openjdk8
#Install GIT for GitHub pulls
RUN apk --update add git
#Install Curl
RUN apk --update add curl
#Set Ant Home in Docker container for Jenkins
ENV ANT_HOME=/opt/ant/apache-ant-1.9.8
#Set Ant Params in Docker for Jenkins
ENV ANT_OPTS="-Xms256M -Xmx512M"
#Update Path
ENV PATH="${PATH}:${HOME}/bin:${ANT_HOME}/bin"