DROP TABLE users CASCADE CONSTRAINTS;
DROP TABLE broker_shareholder CASCADE CONSTRAINTS;
DROP TABLE permissions CASCADE CONSTRAINTS;
DROP TABLE trade CASCADE CONSTRAINTS;
DROP TABLE trade_request CASCADE CONSTRAINTS;
DROP TABLE user_request CASCADE CONSTRAINTS;
DROP TABLE matched_trades CASCADE CONSTRAINTS;
DROP TABLE trade_requests CASCADE CONSTRAINTS;
DROP TABLE shareholder_broker CASCADE CONSTRAINTS;
DROP SEQUENCE user_id_seq;
DROP SEQUENCE user_req_id_seq;
DROP SEQUENCE trade_id_seq; 

CREATE TABLE permissions
(
  type VARCHAR2(20) NOT NULL,
  CONSTRAINT type_pk PRIMARY KEY (type)
);

INSERT INTO Permissions (type) VALUES ('BROKER');
INSERT INTO Permissions (type) VALUES ('SHAREHOLDER');
INSERT INTO Permissions (type) VALUES ('SYSADMIN');
INSERT INTO Permissions (type) VALUES ('BROKER_SHAREHOLDER');

CREATE TABLE users
(
  user_id     NUMBER(6) NOT NULL,
  username    VARCHAR2(20) UNIQUE NOT NULL,
  password    VARCHAR2(20) NOT NULL,
  banned      NUMBER(1,0),
  permission  VARCHAR(20),
  CONSTRAINT user_id_pk PRIMARY KEY(user_id),
  CONSTRAINT banned CHECK (banned = 1 OR banned = 0),
  CONSTRAINT permission_fk FOREIGN KEY (permission) REFERENCES permissions(type)
);

CREATE TABLE trade
(
  trade_id NUMBER(6) NOT NULL,
  shareholder_username VARCHAR(20) NOT NULL,
  broker_username VARCHAR(20) NOT NULL,
  type VARCHAR2(5) NOT NULL,
  ticker VARCHAR2(10) NOT NULL,
  quantity NUMBER(6) NOT NULL,
  price NUMBER(6,2) NOT NULL,
  matched NUMBER(1,0) NOT NULL,
  matched_trade_id NUMBER(6),
  date_matched DATE,
  date_requested DATE NOT NULL,
  CONSTRAINT trade_id PRIMARY KEY (trade_id),
  CONSTRAINT shareholder_username_fk FOREIGN KEY(shareholder_username) REFERENCES users(username),
  CONSTRAINT broker_username_fk FOREIGN KEY(shareholder_username) REFERENCES users(username),
  CONSTRAINT type CHECK (type = 'BUY' OR type = 'SELL')
);

CREATE TABLE user_request
(
    request_id NUMBER(6) NOT NULL,
    username VARCHAR(20) NOT NULL,
    admin VARCHAR(20) NOT NULL,
    requestType VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT request_id PRIMARY KEY(request_id)
);

CREATE TABLE shareholder_broker
(
    shareholder VARCHAR(20) NOT NULL,
    broker VARCHAR(20) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY(shareholder,broker),
    CONSTRAINT shareholder_fk FOREIGN KEY (shareholder) REFERENCES users(username),
    CONSTRAINT broker_fk FOREIGN KEY (broker) REFERENCES users(username)
);


CREATE SEQUENCE user_id_seq 
INCREMENT BY 1 
START WITH 1
;

CREATE SEQUENCE user_req_id_seq 
INCREMENT BY 1 
START WITH 1
;

CREATE SEQUENCE trade_id_seq 
INCREMENT BY 1 
START WITH 1
;

CREATE OR REPLACE VIEW portfolio
AS
SELECT;

CREATE OR REPLACE VIEW matched_trades
AS
SELECT
  t.trade_id,
  t.shareholder_username,
  t.broker_username,
  t.type,
  t.ticker,
  t.quantity,
  t.price,
  t.matched,
  t.matched_trade_id,
  t.date_matched,
  t.date_requested
FROM trade t
WHERE t.matched = 1
 ; 
 
CREATE OR REPLACE VIEW trade_requests
AS
SELECT
  t.trade_id,
  t.shareholder_username,
  t.broker_username,
  t.type,
  t.ticker,
  t.quantity,
  t.price,
  t.matched,
  t.matched_trade_id,
  t.date_matched,
  t.date_requested
FROM trade t
WHERE t.matched = 0
 ;
 

--INSERT INTO users (user_id,username,password,banned,permission) VALUES (user_id_seq.NEXTVAL,'bob','432',0,1);
--INSERT INTO users (user_id,username,password,banned,permission) VALUES (user_id_seq.NEXTVAL,'brokerbill','432',0,0);
INSERT INTO trade (trade_id, shareholder_username, broker_username, type, ticker, quantity, price,matched,matched_trade_id,date_matched,date_requested) VALUES (1,'user2','broker','SELL','GOOG',10,50.0,1,3,SYSDATE,SYSDATE);
INSERT INTO trade (trade_id, shareholder_username, broker_username, type, ticker, quantity, price,matched,matched_trade_id,date_matched,date_requested) VALUES (2,'user2','broker','BUY','MSFT',20,100.0,0,NULL,NULL,SYSDATE);
INSERT INTO trade (trade_id, shareholder_username, broker_username, type, ticker, quantity, price,matched,matched_trade_id,date_matched,date_requested) VALUES (3,'user2','broker','BUY','GOOG',30,50.0,1,1,SYSDATE,SYSDATE);
INSERT INTO trade (trade_id, shareholder_username, broker_username, type, ticker, quantity, price,matched,matched_trade_id,date_matched,date_requested) VALUES (4,'user2','broker','SELL','ORCL',100,150.0,0,NULL,NULL,SYSDATE);
INSERT INTO trade (trade_id, shareholder_username, broker_username, type, ticker, quantity, price,matched,matched_trade_id,date_matched,date_requested) VALUES (5,'brokersh','broker','SELL','TICK',65,134.0,0,NULL,NULL,SYSDATE);
INSERT INTO trade (trade_id, shareholder_username, broker_username, type, ticker, quantity, price,matched,matched_trade_id,date_matched,date_requested) VALUES (6,'brokersh','registerMe','BUY','GOOG',500,150.0,0,NULL,NULL,SYSDATE);
commit;
SELECT request_id, username, admin, requestType, status FROM user_request where username='user2';
SELECT request_id, username, admin, requestType, status FROM user_request;
INSERT INTO user_request(request_id, username, admin, requestType, status) VALUES (1,'user2','admin','RESET_PASSWORD','OUTSTANDING');

SELECT trade_id, shareholder_username, broker_username, type, ticker, quantity, price FROM trade_requests WHERE broker_username='broker';

UPDATE users SET username='udpate', password='new', banned=1, permission='BROKER' WHERE username='userToUpdate';

DELETE FROM user_request WHERE username='DeleteMe' AND admin='admin' AND requestType='CHANGE_USERNAME' AND status='COMPLETED';