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
    VALUES(jb_no_seq.nextval,'ȫ?浿','JSP CURD ????','JSP ???尴ü request/response Ȱ??','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'ȫ?浿','JSP CURD ????','JSP ???尴ü request/response Ȱ??','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'ȫ?浿','JSP CURD ????','JSP ???尴ü request/response Ȱ??','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'ȫ?浿','JSP CURD ????','JSP ???尴ü request/response Ȱ??','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'ȫ?浿','JSP CURD ????','JSP ???尴ü request/response Ȱ??','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'ȫ?浿','JSP CURD ????','JSP ???尴ü request/response Ȱ??','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'ȫ?浿','JSP CURD ????','JSP ???尴ü request/response Ȱ??','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'ȫ?浿','JSP CURD ????','JSP ???尴ü request/response Ȱ??','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'ȫ?浿','JSP CURD ????','JSP ???尴ü request/response Ȱ??','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'ȫ?浿','JSP CURD ????','JSP ???尴ü request/response Ȱ??','1234');
INSERT INTO jsp_board(no,name,subject,content,pwd) 
    VALUES(jb_no_seq.nextval,'ȫ?浿','JSP CURD ????','JSP ???尴ü request/response Ȱ??','1234');
COMMIT;

CREATE TABLE jsp_member(
    id VARCHAR2(20),
    pwd VARCHAR2(10) CONSTRAINT jm_pwd_nn NOT NULL,
    name VARCHAR2(34) CONSTRAINT jm_name_nn NOT NULL,
    sex VARCHAR2(10),
    CONSTRAINT jm_id_pk PRIMARY KEY(id),
    CONSTRAINT jm_sex_ck CHECK(sex IN('????','????'))
);
INSERT INTO jsp_member VALUES('hong','1234','ȫ?浿','????');
INSERT INTO jsp_member VALUES('shim','1234','??û??','????');
INSERT INTO jsp_member VALUES('park','1234','?ڹ???','????');
INSERT INTO jsp_member VALUES('kang','1234','??????','????');
INSERT INTO jsp_member VALUES('kim','1234','??????','????');
COMMIT;

CREATE TABLE jsp_reply(
    no NUMBER,
    fno NUMBER,
    id VARCHAR2(20),
    name VARCHAR2(34) CONSTRAINT jr_name_nn NOT NULL,
    msg CLOB CONSTRAINT jr_msg_nn NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    star NUMBER DEFAULT 0,
    CONSTRAINT jr_no_pk PRIMARY KEY(no),
    CONSTRAINT jr_fno_fk FOREIGN KEY(fno)
        REFERENCES project_food(fno),
    CONSTRAINT jr_id_fk FOREIGN KEY(id)
        REFERENCES jsp_member(id)
);
CREATE SEQUENCE jr_no_seq
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;
    
CREATE TABLE jsp_databoard(
    no NUMBER, 
    name VARCHAR2(34) CONSTRAINT jd_name_nn NOT NULL,
    subject VARCHAR2(4000) CONSTRAINT jd_subject_nn NOT NULL,
    content CLOB CONSTRAINT jd_content_nn NOT NULL,
    pwd VARCHAR2(10) CONSTRAINT jd_pwd_nn NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    hit NUMBER DEFAULT 0,
    filename VARCHAR2(260),
    filesize NUMBER,
    CONSTRAINT jd_no_pk PRIMARY KEY(no)
);
CREATE SEQUENCE jd_no_seq
    START WITH 1
    INCREMENT BY 1
    NOCYCLE
    NOCACHE;
    
CREATE TABLE jsp_replyBoard(
    no NUMBER, 
    name VARCHAR2(34) CONSTRAINT jrb_name_nn NOT NULL,
    subject VARCHAR2(4000) CONSTRAINT jrb_subject_nn NOT NULL,
    content CLOB CONSTRAINT jrb_content_nn NOT NULL,
    pwd VARCHAR2(10) CONSTRAINT jrb_pwd_nn NOT NULL,
    regdate DATE DEFAULT SYSDATE,
    hit NUMBER DEFAULT 0,
    group_id NUMBER,
    group_step NUMBER DEFAULT 1,
    group_tab NUMBER DEFAULT 0,
    root NUMBER DEFAULT 0,
    depth NUMBER DEFAULT 0,
    CONSTRAINT jrb_no_pk PRIMARY KEY(no)
);
CREATE SEQUENCE jrb_no_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',1);
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',2);
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',3);
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',4);
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',5);
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',6);
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',7);
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',8);
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',9);
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',10);
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',10,2,1);
INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab) 
    VALUES(jrb_no_seq.nextval,'ȫ?浿','?亯???Խ???','?亯???Խ??? = Chapter 15','1234',10,3,2);
COMMIT;