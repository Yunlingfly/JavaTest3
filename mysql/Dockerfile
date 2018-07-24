#  这个是构建MySQL的dockerfile

FROM registry.saas.hand-china.com/tools/mysql:5.6

# mysql的工作位置
ENV WORK_PATH /usr/local/hand_mysql

# 该文件用来初始化数据库
ENV AUTO_RUN_DIR /docker-entrypoint-initdb.d

# 初始化数据库的SQL
ENV FILE_0 sakila-schema.sql
ENV FILE_1 sakila-data.sql

# 执行SQL
ENV INSTALL_DATA_SHELL import.sh

COPY ./$FILE_0 $WORK_PATH/
COPY ./$FILE_1 $WORK_PATH/
#把要执行的shell文件放到/docker-entrypoint-initdb.d/目录下，容器会自动执行这个shell
COPY ./$INSTALL_DATA_SHELL $AUTO_RUN_DIR/

RUN chmod a+x $AUTO_RUN_DIR/$INSTALL_DATA_SHELL