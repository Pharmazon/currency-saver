FROM postgres:10

ARG SSH_ROOT_USER
ARG SSH_ROOT_PASSWORD

USER root

COPY init/init_db.sh /docker-entrypoint-initdb.d/
COPY init/init_ssh.sh /tmp/

RUN chmod 777 -R /tmp/ &&\
    apt-get update -y &&\
    apt-get install -y openssh-server &&\
    mkdir /var/run/sshd &&\
    echo "${SSH_ROOT_USER}:${SSH_ROOT_PASSWORD}" | chpasswd &&\
    sed -i 's/PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config &&\
    sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd &&\
    echo "export VISIBLE=now" >> /etc/profile

ENTRYPOINT /tmp/init_ssh.sh ; service ssh restart; docker-entrypoint.sh postgres

EXPOSE 22