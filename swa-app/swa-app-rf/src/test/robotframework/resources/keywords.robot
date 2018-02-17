
*** Keywords ***

Open test browser
    Open browser  about:

Close test browser
    Close all browsers

OverviewPage should be shown
    Page should contain element  css=*[id$='maskName']
    Page should contain element  css=*[id$='version']
    Element should contain  css=*[id$='maskName']  Overview

Remove all notes
    Connect To Database    org.postgresql.Driver    jdbc:postgresql://localhost:5432/swa    swa    swa
    Execute SQL    DELETE FROM T_WORKSHOP;
    Execute SQL    COMMIT;
    Disconnect From Database

Insert 3 notes
    Connect To Database    org.postgresql.Driver    jdbc:postgresql://localhost:5432/swa    swa    swa
    Execute SQL    INSERT INTO t_workshop (id, title, number, duration, day_of_event, attendance) VALUES (100201, 'JEE by Example', 8080, 2, '2018-09-08 14:49:37.835000', 15);
    Execute SQL    INSERT INTO t_workshop (id, title, number, duration, day_of_event, attendance) VALUES (100202, 'Working with Squid', 3128, 1, '2018-05-17 14:41:42.134000', 24);
    Execute SQL    INSERT INTO t_workshop (id, title, number, duration, day_of_event, attendance) VALUES (100203, 'Mysticism of Data', 5432, 1, '2018-07-28 14:34:24.612000', 140);
    Execute SQL    COMMIT;
    Disconnect From Database