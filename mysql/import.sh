#!/usr/bin/env bash
# 使用MySQL的source导入SQL表

mysql -uroot -p$MYSQL_ROOT_PASSWORD <<EOF
source $WORK_PATH/$FILE_0;
source $WORK_PATH/$FILE_1;