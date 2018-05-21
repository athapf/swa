#!/bin/sh

echo "archive data ..."
sleep 5
cd /
tar czf /opt/data/oradata.tgz u01/app/oracle/oradata
#chmod -R 777 /opt/data
echo "... archive ready"