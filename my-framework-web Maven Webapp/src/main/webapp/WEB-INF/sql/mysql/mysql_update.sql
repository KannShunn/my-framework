
CREATE PROCEDURE p_update()
BEGIN
DECLARE ver INT;
DECLARE cnt INT;

    SELECT COUNT(*) into cnt FROM information_schema.TABLES WHERE TABLE_NAME='T_AUTH_VERSION' and TABLE_SCHEMA = 'my_framework';
    IF cnt = 0 THEN 
        SELECT '创建version表';  
        CREATE TABLE T_AUTH_VERSION
        (
            VERSION INT NOT NULL
        ); 
    	INSERT INTO T_AUTH_VERSION (VERSION) VALUES (50000);
    END IF; 
        
        
    
    
    SELECT version INTO ver FROM T_AUTH_VERSION;
    
    IF ver < 1000 THEN
	    SELECT '将version更新到1000';
	    UPDATE T_AUTH_VERSION SET VERSION = 1000;
    END IF; 
    

 END; 
