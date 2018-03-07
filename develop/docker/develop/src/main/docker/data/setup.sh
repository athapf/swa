#!/usr/bin/env bash

/opt/jboss/wildfly/bin/standalone.sh &
sleep 5
/opt/jboss/wildfly/bin/jboss-cli.sh -c --file=/tmp/initdb.cli
kill %1
