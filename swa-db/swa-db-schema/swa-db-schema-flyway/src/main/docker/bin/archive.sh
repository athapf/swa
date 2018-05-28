#!/bin/sh

cd /var/lib/postgresql/data

echo "archive data ..."
tar czf /opt/data/data.tgz *

chmod -R 777 /opt/data
echo "ready"