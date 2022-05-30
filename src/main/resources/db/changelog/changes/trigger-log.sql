--liquibase formatted sql

CREATE TABLE audit (
  "operation" varchar NOT NULL ,
  "date" timestamp NOT NULL DEFAULT now(),
  "table" varchar NOT NULL,
  "record_id" varchar NOT NULL
);

CREATE OR REPLACE FUNCTION audit_func()
RETURNS trigger AS '
    BEGIN
        if (tg_op IN (''INSERT''::text)) then
            INSERT INTO audit(
                              "operation", "table", "record_id"
            ) VALUES (
                      ''INSERT'', tg_table_name, new.id::varchar
            );
            RETURN NEW;
        elseif (tg_op IN (''UPDATE''::text)) then
            INSERT INTO audit(
                "operation", "table", "record_id"
            ) VALUES (
                         ''UPDATE'', tg_table_name, old.id::varchar
            );
            RETURN OLD;
        elseif (tg_op IN (''DELETE''::text)) then
            INSERT INTO audit(
                "operation", "table", "record_id"
            ) VALUES (
                         ''DELETE'', tg_table_name, old.id::varchar
            );
            RETURN OLD;
        end if;
    END;
'
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION users_audit_func()
    RETURNS trigger AS '
    BEGIN
        if (tg_op IN (''INSERT''::text)) then
            INSERT INTO audit(
                "operation", "table", "record_id"
            ) VALUES (
                         ''INSERT'', tg_table_name, new.username::varchar
                     );
            RETURN NEW;
        elseif (tg_op IN (''UPDATE''::text)) then
            INSERT INTO audit(
                "operation", "table", "record_id"
            ) VALUES (
                         ''UPDATE'', tg_table_name, old.username::varchar
                     );
            RETURN OLD;
        elseif (tg_op IN (''DELETE''::text)) then
            INSERT INTO audit(
                "operation", "table", "record_id"
            ) VALUES (
                         ''DELETE'', tg_table_name, old.username::varchar
                     );
            RETURN OLD;
        end if;
    END;
'
    LANGUAGE plpgsql;

CREATE TRIGGER users_audit_trigger
    AFTER INSERT OR UPDATE OR DELETE ON users
    FOR EACH ROW EXECUTE FUNCTION users_audit_func();

CREATE TRIGGER posts_audit_trigger
    AFTER INSERT OR UPDATE OR DELETE ON posts
    FOR EACH ROW EXECUTE FUNCTION audit_func();

CREATE TRIGGER tags_audit_trigger
    AFTER INSERT OR UPDATE OR DELETE ON tags
    FOR EACH ROW EXECUTE FUNCTION audit_func();

