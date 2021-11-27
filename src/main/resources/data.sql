INSERT INTO USER(NAME, BIRTHDAY, GENDER, ID, PASSWORD, EMAIL)
VALUES ('test', NOW(), '남자', 'testSitter', '$2a$10$OHTekfxzNrvtK77AIAWDuOJ1NNlbHMAHduykKWGCPRkt8YAJH28sO', 'testSitter@test.com');
INSERT INTO SITTER(USER_ID, INTRO_MSG, CARE_FROM, CARE_TO)
VALUES ('testSitter', '안녕하세요', 1, 3);


INSERT INTO USER(NAME, BIRTHDAY, GENDER, ID, PASSWORD, EMAIL)
VALUES ('test', NOW(), '남자', 'testParent', '$2a$10$OHTekfxzNrvtK77AIAWDuOJ1NNlbHMAHduykKWGCPRkt8YAJH28sO', 'testParent@test.com');
INSERT INTO PARENT(USER_ID, REQUEST_MSG)
VALUES ('testParent', '우리아이 맡아주세요');
INSERT INTO CHILDREN(PARENT_NO, BIRTHDAY, GENDER)
VALUES (1, NOW(), '남자');


INSERT INTO USER(NAME, BIRTHDAY, GENDER, ID, PASSWORD, EMAIL)
VALUES ('test', NOW(), '남자', 'testDual', '$2a$10$OHTekfxzNrvtK77AIAWDuOJ1NNlbHMAHduykKWGCPRkt8YAJH28sO', 'testDual@test.com');
INSERT INTO SITTER(USER_ID, INTRO_MSG, CARE_FROM, CARE_TO)
VALUES ('testDual', '안녕하세요', 1, 3);
INSERT INTO PARENT(USER_ID, REQUEST_MSG)
VALUES ('testDual', '우리아이 맡아주세요');
INSERT INTO CHILDREN(PARENT_NO, BIRTHDAY, GENDER)
VALUES (2, NOW(), '남자');
