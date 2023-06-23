# My device OS.
FROM ubuntu:22.04

RUN apt-get update && apt-get install -y openssh-server openjdk-8-jdk

# ssh directory
RUN mkdir /run/sshd

# Username + password + update them.
RUN echo 'root:root' | chpasswd

# To enable SSH root login in the Docker container, it is disabled in Linux.
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

COPY app.jar /app/app.jar

WORKDIR /app


# Start the application
CMD ["java", "-jar", "app.jar"]



#in terminal:
#docker build -t clustered-data-warehouse .
#docker run -d -p 2222:22 clustered-data-warehouse
