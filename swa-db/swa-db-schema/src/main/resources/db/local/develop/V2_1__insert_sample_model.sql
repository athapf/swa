--
-- insert sample model
--

INSERT INTO T_MODEL_STATE (ID, STATE) VALUES (nextval('SEQ_ID_MODEL_STATE'), 'START');
INSERT INTO T_MODEL_STATE (ID, STATE) VALUES (nextval('SEQ_ID_MODEL_STATE'), 'EMPTY');
INSERT INTO T_MODEL_STATE (ID, STATE) VALUES (nextval('SEQ_ID_MODEL_STATE'), 'SELECT');
INSERT INTO T_MODEL_STATE (ID, STATE) VALUES (nextval('SEQ_ID_MODEL_STATE'), 'CLOSED');
INSERT INTO T_MODEL_STATE (ID, STATE) VALUES (nextval('SEQ_ID_MODEL_STATE'), 'END');

INSERT INTO T_MODEL_EDGE (ID, FROM_ID, TO_ID) VALUES (nextval('SEQ_ID_MODEL_EDGE'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'START'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'EMPTY'));
INSERT INTO T_MODEL_ACTION (ID, ACTION, TYPE, EDGE_ID) VALUES (nextval('SEQ_ID_MODEL_ACTION'), 'init', 'INTERNAL', currval('SEQ_ID_MODEL_EDGE'));

INSERT INTO T_MODEL_EDGE (ID, FROM_ID, TO_ID) VALUES (nextval('SEQ_ID_MODEL_EDGE'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'EMPTY'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'SELECT'));
INSERT INTO T_MODEL_ACTION (ID, ACTION, TYPE, EDGE_ID) VALUES (nextval('SEQ_ID_MODEL_ACTION'), 'open', 'PUBLIC', currval('SEQ_ID_MODEL_EDGE'));

INSERT INTO T_MODEL_EDGE (ID, FROM_ID, TO_ID) VALUES (nextval('SEQ_ID_MODEL_EDGE'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'SELECT'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'CLOSED'));
INSERT INTO T_MODEL_ACTION (ID, ACTION, TYPE, EDGE_ID) VALUES (nextval('SEQ_ID_MODEL_ACTION'), 'complete', 'PUBLIC', currval('SEQ_ID_MODEL_EDGE'));
INSERT INTO T_MODEL_ACTION (ID, ACTION, TYPE, EDGE_ID) VALUES (nextval('SEQ_ID_MODEL_ACTION'), 'finish', 'PUBLIC', currval('SEQ_ID_MODEL_EDGE'));

INSERT INTO T_MODEL_EDGE (ID, FROM_ID, TO_ID) VALUES (nextval('SEQ_ID_MODEL_EDGE'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'SELECT'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'EMPTY'));
INSERT INTO T_MODEL_ACTION (ID, ACTION, TYPE, EDGE_ID) VALUES (nextval('SEQ_ID_MODEL_ACTION'), 'clear', 'PUBLIC', currval('SEQ_ID_MODEL_EDGE'));

INSERT INTO T_MODEL_EDGE (ID, FROM_ID, TO_ID) VALUES (nextval('SEQ_ID_MODEL_EDGE'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'CLOSED'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'END'));
INSERT INTO T_MODEL_ACTION (ID, ACTION, TYPE, EDGE_ID) VALUES (nextval('SEQ_ID_MODEL_ACTION'), 'save', 'PUBLIC', currval('SEQ_ID_MODEL_EDGE'));
INSERT INTO T_MODEL_ACTION (ID, ACTION, TYPE, EDGE_ID) VALUES (nextval('SEQ_ID_MODEL_ACTION'), 'stop', 'INTERNAL', currval('SEQ_ID_MODEL_EDGE'));

INSERT INTO T_MODEL_EDGE (ID, FROM_ID, TO_ID) VALUES (nextval('SEQ_ID_MODEL_EDGE'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'CLOSED'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'SELECT'));
INSERT INTO T_MODEL_ACTION (ID, ACTION, TYPE, EDGE_ID) VALUES (nextval('SEQ_ID_MODEL_ACTION'), 'reopen', 'PUBLIC', currval('SEQ_ID_MODEL_EDGE'));

INSERT INTO T_MODEL_EDGE (ID, FROM_ID, TO_ID) VALUES (nextval('SEQ_ID_MODEL_EDGE'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'CLOSED'), (SELECT ID FROM T_MODEL_STATE WHERE STATE = 'EMPTY'));
INSERT INTO T_MODEL_ACTION (ID, ACTION, TYPE, EDGE_ID) VALUES (nextval('SEQ_ID_MODEL_ACTION'), 'drop', 'PUBLIC', currval('SEQ_ID_MODEL_EDGE'));