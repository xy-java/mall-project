#!/bin/bash

sqoop=/usr/local/sqoop/sqoop-1.4.7.bin__hadoop-2.6.0/bin/sqoop
JDBCURL="jdbc:mysql://192.168.0.103:3306/test"
USERNAME="root"
PASSWORD="123456"
hive=/usr/local/hive/apache-hive-3.1.0-bin/bin/hive


#删除对应hive表
$hive -e "drop table if exists test.user_info"
$hive -e "drop table if exists test.order_info"
$hive -e "drop table if exists test.type_num"
$hive -e "drop table if exists test.type_price"
$hive -e "drop table if exists test.order_detail"

$sqoop import --connect $JDBCURL --username $USERNAME --password $PASSWORD --table user_info --hive-import --input-fields-terminated-by '\t' --hive-table test.user_info --target-dir /test/user_info --delete-target-dir --create-hive-table -m 1
$sqoop import --connect $JDBCURL --username $USERNAME --password $PASSWORD --table order_info --hive-import --input-fields-terminated-by '\t' --hive-table test.order_info --target-dir /test/order_info --delete-target-dir --create-hive-table -m 1
$sqoop import --connect $JDBCURL --username $USERNAME --password $PASSWORD --query "select * from(select b.sku_type,a.order_num,'num' as type from order_detail a,sku_info b where a.sku_id = b.sku_id and a.order_id in (select order_id from order_info where order_status = 1))c where  \$CONDITIONS" --hive-import --input-fields-terminated-by '\t' --hive-table test.type_num --target-dir /test/type_num --delete-target-dir --create-hive-table -m 1
$sqoop import --connect $JDBCURL --username $USERNAME --password $PASSWORD --query "select * from(select b.sku_type,a.order_price*a.order_num as price from order_detail a,sku_info b where a.sku_id = b.sku_id and a.order_id in (select order_id from order_info where order_status = 1))c where \$CONDITIONS" --hive-import --input-fields-terminated-by '\t' --hive-table test.type_price --target-dir /test/type_price --delete-target-dir --create-hive-table -m 1
$sqoop import --connect $JDBCURL --username $USERNAME --password $PASSWORD --query "select * from(select * from order_detail where order_id in (select order_id from order_info where order_status = 1))c where \$CONDITIONS" --hive-import --input-fields-terminated-by '\t' --hive-table test.order_detail --target-dir /test/order_detail --delete-target-dir --create-hive-table -m 1


$hive -e "insert overwrite directory '/result/count' ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' select * from (select count(*) from test.user_info where user_power='普通用户')a ,( select sum(total_amount) from test.order_info where order_status=1 )b"
$hive -e "insert overwrite directory '/result/date' ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' select a.x_info,a.y_info,'date' as type from (select SUBSTR(create_time,1,10) as x_info,sum(order_num) as y_info from test.order_detail  GROUP BY SUBSTR(create_time,1,10))a where a.x_info between date_add(current_date,-6) and current_date;"
$hive -e "insert overwrite directory '/result/type_num' ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' select sku_type as x_info,sum(order_num) as y_info,'num' as type from test.type_num group by sku_type"
$hive -e "insert overwrite directory '/result/type_price' ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' select sku_type as x_info,sum(price) as y_info,'price' as type from test.type_price group by sku_type"

$sqoop eval --connect $JDBCURL --username $USERNAME --password $PASSWORD --e "delete from hive_count"
$sqoop eval --connect $JDBCURL --username $USERNAME --password $PASSWORD --e "delete from hive_analys"

$sqoop export --connect $JDBCURL --username $USERNAME --password $PASSWORD --table hive_count --columns count,sumPrice --input-fields-terminated-by '\t' --export-dir /result/count -m 1  
$sqoop export --connect $JDBCURL --username $USERNAME --password $PASSWORD --table hive_analys --columns x_info,y_info,type --input-fields-terminated-by '\t' --export-dir /result/date -m 1 
$sqoop export --connect $JDBCURL --username $USERNAME --password $PASSWORD --table hive_analys --columns x_info,y_info,type --input-fields-terminated-by '\t' --export-dir /result/type_num -m 1 
$sqoop export --connect $JDBCURL --username $USERNAME --password $PASSWORD --table hive_analys --columns x_info,y_info,type --input-fields-terminated-by '\t' --export-dir /result/type_price -m 1 
 