# Sample Web Application
a sample project to show feasibility of architecture

## Database-Layer

Zu verwendende Umgebung
- DB2 (LUW)
- openjpa 2.2.1

Zielumgebung ist WebSphere 8.5 (?). Hier wird eine gepatchte Version von openjpa 2.1.1 verwendet.
In wieweit diese Version den Funktionsumfang von 2.2.1 entspricht ist nicht klar.
Version 2.2.1 muss aber für die Integrationstests verwendet werden, weil diese eine Standalone-Umgebung bereit stellt.

Datenbank lässt sich einfach über Docker einrichten: https://hub.docker.com/r/ibmcom/db2express-c/

JDBC-Driver für DB2 jeweils in die Verzeichnisse
- swa-db/swa-db-it/lib
- swa-db/swa-db-schema/lib

kopieren
