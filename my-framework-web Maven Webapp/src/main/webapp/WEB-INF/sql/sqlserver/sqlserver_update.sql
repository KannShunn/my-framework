 DECLARE
    @ver INT, @cnt INT
BEGIN
    select @cnt=COUNT(*) from sysobjects t where t.name=UPPER('T_AUTH_VERSION');
    IF @cnt = 0
    BEGIN
        print('创建version表');

        CREATE TABLE T_AUTH_VERSION
        (
            VERSION NUMERIC(10,0) NOT NULL
        );
        INSERT INTO T_AUTH_VERSION (VERSION) VALUES (1000);

    END;

    SELECT @ver=VERSION FROM T_AUTH_VERSION;

    IF @ver < 1000
    BEGIN
	    print('将version更新到1000');
	    UPDATE T_AUTH_VERSION SET VERSION = 1000;
    END;


 END;
