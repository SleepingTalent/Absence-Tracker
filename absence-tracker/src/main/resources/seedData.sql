
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (1,'admin','password','true');
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (2,'manager','password','true');
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (3,'employee','password','true');
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (4,'norole','password','true');
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (5,'unknownrole','password','true');

INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(1, 'ADMIN',1);
INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(3, 'MANAGER',2);
INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(4, 'EMPLOYEE',2);
INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(5, 'EMPLOYEE',3);
INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(6, 'UNKNOWN',5);

INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME) VALUES (1, 'Trevor','Boss');
INSERT INTO DEPARTMENT (ID,DEPARTMENT_NAME,MANAGER_ID) VALUES (1, 'Senior Management',1);


INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,DEPARTMENT_ID) VALUES (2, 'Bob','Gaffer',1);

INSERT INTO DEPARTMENT (ID,DEPARTMENT_NAME,MANAGER_ID) VALUES (2, 'Software Development',2);
INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,DEPARTMENT_ID) VALUES (3, 'Dave','Worker',2);
INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,DEPARTMENT_ID) VALUES (4, 'Jane','Worker',2);