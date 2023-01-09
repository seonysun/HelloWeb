CREATE TABLE jsp_board(
    no NUMBER, 
    name VARCHAR2(34) CONSTRAINT jb_name_nn NOT NULL,
    subject VARCHAR2(4000) CONSTRAINT jb_subject_nn NOT NULL,
    content CLOB CONSTRAINT jb_content_nn NOT NULL,
    pwd VARCHAR2(10) CONSTRAINT jb_pwd_nn NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    hit NUMBER DEFAULT 0,
    CONSTRAINT jb_no_pk PRIMARY KEY(no)
);
CREATE SEQUENCE jb_no_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'È«±æµ¿','JSP CURD °³¹ß','JSP ³»Àå°´Ã¼ request/response È°¿ë','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'È«±æµ¿','JSP CURD °³¹ß','JSP ³»Àå°´Ã¼ request/response È°¿ë','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'È«±æµ¿','JSP CURD °³¹ß','JSP ³»Àå°´Ã¼ request/response È°¿ë','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'È«±æµ¿','JSP CURD °³¹ß','JSP ³»Àå°´Ã¼ request/response È°¿ë','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'È«±æµ¿','JSP CURD °³¹ß','JSP ³»Àå°´Ã¼ request/response È°¿ë','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'È«±æµ¿','JSP CURD °³¹ß','JSP ³»Àå°´Ã¼ request/response È°¿ë','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'È«±æµ¿','JSP CURD °³¹ß','JSP ³»Àå°´Ã¼ request/response È°¿ë','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'È«±æµ¿','JSP CURD °³¹ß','JSP ³»Àå°´Ã¼ request/response È°¿ë','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'È«±æµ¿','JSP CURD °³¹ß','JSP ³»Àå°´Ã¼ request/response È°¿ë','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'È«±æµ¿','JSP CURD °³¹ß','JSP ³»Àå°´Ã¼ request/response È°¿ë','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'È«±æµ¿','JSP CURD °³¹ß','JSP ³»Àå°´Ã¼ request/response È°¿ë','1234');
COMMIT;