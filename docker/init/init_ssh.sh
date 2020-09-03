#! /bin/bash
set -e

printf "\n\033[;44m---> Creating SSH User.\033[0m\n"

if grep -q "^ssh:" /etc/group
then
  echo "group ssh exists"
else
  echo "create new group"
  groupadd ssh
fi

echo "************************************"
echo "SSH User: " ${SSH_USER}
echo "************************************"

if ! [ -d /home/${SSH_USER} ]; then
  mkdir /home/${SSH_USER}
fi

echo "Проверка пользователя"

if [[ `grep "^${SSH_USER}:" /etc/passwd >/dev/null; echo $?` -ne 0 ]]; then
  useradd -d /home/${SSH_USER} -G ssh,${POSTGRES_USER} -s /bin/bash ${SSH_USER}
fi

echo "${SSH_USER}:${SSH_PASS}" | chpasswd
echo 'PATH=/opt/mqm/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin' >> /etc/environment

echo "${SSH_USER} ALL=NOPASSWD:/bin/rm" >> /etc/sudoers
echo "${SSH_USER} ALL=NOPASSWD:/bin/mkdir" >> /etc/sudoers
echo "${SSH_USER} ALL=NOPASSWD:/bin/chown" >> /etc/sudoers
echo "${SSH_USER} ALL=NOPASSWD:/bin/sbin/useradd" >> /etc/sudoers
echo "${SSH_USER} ALL=NOPASSWD:/bin/sbin/deluser" >> /etc/sudoers
echo "${SSH_USER} ALL=NOPASSWD:/bin/sbin/chpasswd" >> /etc/sudoers

echo "SSH user (${SSH_USER}) created successfully!"