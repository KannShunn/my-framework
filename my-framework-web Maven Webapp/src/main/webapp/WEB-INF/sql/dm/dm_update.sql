DECLARE 
    ver NUMBER; 
    cnt NUMBER; 
BEGIN 
    EXECUTE IMMEDIATE 'SELECT COUNT(*) FROM user_tables WHERE TABLE_NAME=UPPER(''T_AUTH_VERSION'')' INTO cnt;
    IF cnt = 0 THEN 
        print('创建version表'); 
        EXECUTE IMMEDIATE ' 
        CREATE TABLE T_AUTH_VERSION
        (
            VERSION NUMBER(10) NOT NULL
        )'; 
        cnt:=0; 
        
        EXECUTE IMMEDIATE 'INSERT INTO T_AUTH_VERSION (VERSION) VALUES (1000)';
        
    END IF; 
    
    EXECUTE IMMEDIATE 'SELECT VERSION FROM T_AUTH_VERSION' INTO ver;
    
    IF ver < 1000 THEN
	    print('将version更新到1000');
	    EXECUTE IMMEDIATE 'UPDATE T_AUTH_VERSION SET VERSION = 1000';
    END IF; 
  
 END; 
