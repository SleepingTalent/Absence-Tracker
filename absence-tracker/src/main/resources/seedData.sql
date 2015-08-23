
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (1,'tboss','password','true');
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (4,'norole','password','true');
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (5,'unknownrole','password','true');
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (6,'bgaffer','password','true');
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (7,'dworker','password','true');
INSERT INTO USER (ID,USERNAME,PASSWORD,ENABLED) VALUES (8,'jworker','password','true');

INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(1, 'ADMIN',1);
INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(2, 'MANAGER',1);
INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(3, 'EMPLOYEE',1);

INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(7, 'UNKNOWN',5);

INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(8, 'MANAGER',6);
INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(9, 'EMPLOYEE',6);

INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(10, 'EMPLOYEE',7);
INSERT INTO USERROLE (ID,ROLE,USER_ID) VALUES(11, 'EMPLOYEE',8);

INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,USER_ID) VALUES (1, 'Trevor','Boss',1);
INSERT INTO DEPARTMENT (ID,DEPARTMENT_NAME,MANAGER_ID) VALUES (1, 'Senior Management',1);

INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,DEPARTMENT_ID,USER_ID) VALUES (2, 'Bob','Gaffer',1,6);

INSERT INTO DEPARTMENT (ID,DEPARTMENT_NAME,MANAGER_ID) VALUES (2, 'Software Development',2);
INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,DEPARTMENT_ID,USER_ID) VALUES (3, 'Dave','Worker',2,7);
INSERT INTO EMPLOYEE (ID,FIRST_NAME,LAST_NAME,DEPARTMENT_ID,USER_ID) VALUES (4, 'Jane','Worker',2,8);

INSERT INTO HOLIDAYALLOWANCE (ID,TOTAL,USED,EMPLOYEE_ID) VALUES (1,300,0,1);
INSERT INTO HOLIDAYALLOWANCE (ID,TOTAL,USED,EMPLOYEE_ID) VALUES (2,256,0,2);
INSERT INTO HOLIDAYALLOWANCE (ID,TOTAL,USED,EMPLOYEE_ID) VALUES (3,200,40,3);
INSERT INTO HOLIDAYALLOWANCE (ID,TOTAL,USED,EMPLOYEE_ID) VALUES (4,200,80,4);

INSERT INTO ABSENCE (ID,START,END,STATUS,REASON,COINCIDES_WITH_DECLINED_HOL,EMPLOYEE_ID) VALUES (1,{ts '2015-01-01'},{ts '2015-01-10'},'CONFIRMED','COLD_FLU','false',4);
INSERT INTO ABSENCE (ID,START,END,STATUS,REASON,COINCIDES_WITH_DECLINED_HOL,EMPLOYEE_ID) VALUES (2,{ts '2015-02-01'},{ts '2015-02-05'},'CONFIRMED','COLD_FLU','false',4);
INSERT INTO ABSENCE (ID,START,END,STATUS,REASON,COINCIDES_WITH_DECLINED_HOL,EMPLOYEE_ID) VALUES (3,{ts '2015-03-01'},{ts '2015-03-15'},'CONFIRMED','STRESS_DEPRESSION','false',4);
INSERT INTO ABSENCE (ID,START,END,STATUS,REASON,COINCIDES_WITH_DECLINED_HOL,EMPLOYEE_ID) VALUES (4,{ts '2015-04-01'},{ts '2015-06-01'},'CONFIRMED','MATERNITY','false',4);
INSERT INTO ABSENCE (ID,START,END,STATUS,REASON,COINCIDES_WITH_DECLINED_HOL,EMPLOYEE_ID) VALUES (5,{ts '2015-05-01'},{ts '2015-05-04'},'CONFIRMED','CARERS','false',4);

INSERT INTO ABSENCE (ID,START,END,STATUS,REASON,COINCIDES_WITH_DECLINED_HOL,EMPLOYEE_ID) VALUES (6,{ts '2015-01-01'},{ts '2015-01-03'},'CONFIRMED','COLD_FLU','false',3);
INSERT INTO ABSENCE (ID,START,END,STATUS,REASON,COINCIDES_WITH_DECLINED_HOL,EMPLOYEE_ID) VALUES (7,{ts '2015-06-01'},{ts '2015-05-10'},'CONFIRMED','BACK_PROBLEMS','false',3);
INSERT INTO ABSENCE (ID,START,END,STATUS,REASON,COINCIDES_WITH_DECLINED_HOL,EMPLOYEE_ID) VALUES (8,{ts '2015-07-01'},{ts '2015-07-13'},'CONFIRMED','BEREAVEMENT','false',3);

INSERT INTO ANNUALLEAVE (ID,START,END,STATUS,EMPLOYEE_ID) VALUES (1,{ts '2015-04-06'},{ts '2015-04-10'},'AUTHORISED',3);
INSERT INTO ANNUALLEAVE (ID,START,END,STATUS,EMPLOYEE_ID) VALUES (2,{ts '2015-04-27'},{ts '2015-05-08'},'AUTHORISED',4);