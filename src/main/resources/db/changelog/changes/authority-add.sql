CREATE OR REPLACE FUNCTION add_default_authority()
    RETURNS trigger AS '
    BEGIN
        INSERT INTO authorities (username, role) VALUES (NEW.username, ''ROLE_USER'');
        RETURN NEW;
    END;
'
    LANGUAGE plpgsql;

CREATE TRIGGER add_default_authority_trigger
    AFTER INSERT ON users
    FOR EACH ROW EXECUTE FUNCTION add_default_authority();