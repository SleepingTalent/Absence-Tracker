
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (1,'admin','password','true');
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (2,'manager','password','true');
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (3,'employee','password','true');

INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(1, 'ADMIN',1);

INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(3, 'MANAGER',2);
INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(4, 'EMPLOYEE',2);

INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(5, 'EMPLOYEE',3);