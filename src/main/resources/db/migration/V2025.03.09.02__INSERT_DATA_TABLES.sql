BEGIN TRY
BEGIN TRANSACTION

    INSERT INTO role (name) values ('ADMIN');
    INSERT INTO role (name) values ('CUSTOMER');

    INSERT INTO user (first_name, last_name, email) values ('Super', 'Admin', 'admin@gmail.com');
    INSERT INTO user (first_name, last_name, email) values ('Mr.', 'Tom', 'tom@gmail.com');
    INSERT INTO user (first_name, last_name, email) values ('Mr.', 'Jerry', 'jerry@gmail.com');

    INSERT INTO user_role (user_id, role_id) values (1, 1);
    INSERT INTO user_role (user_id, role_id) values (2, 2);
    INSERT INTO user_role (user_id, role_id) values (3, 2);

COMMIT TRANSACTION
END TRY

BEGIN CATCH
IF @@TRANCOUNT > 0
        ROLLBACK TRANSACTION
    PRINT 'An error occurred: ' + ERROR_MESSAGE();
END CATCH