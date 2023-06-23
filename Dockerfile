# My device OS.
FROM ubuntu:22.04

RUN apt-get update && apt-get install -y openssh-server

# Create the privilege separation directory
RUN mkdir /run/sshd

# Username + password + update them.
RUN echo 'root:root' | chpasswd

# To enable SSH root login in the Docker container, it is disabled in Linux.
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

# Start the SSH server
CMD ["/usr/sbin/sshd", "-D"]


#in terminal:
#docker build -t clustered-data-warehouse .
#docker run -d -p 2222:22 clustered-data-warehouse