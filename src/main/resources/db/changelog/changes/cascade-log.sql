CREATE OR REPLACE FUNCTION users_clear_likes_and_posts()
    RETURNS trigger AS '
    BEGIN
        DELETE FROM likes where username == old.username;
        RETURN OLD;
    END;
'
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION clear_posts_func()
    RETURNS trigger AS '
    BEGIN
        DELETE FROM likes WHERE post_id == old.id;
        RETURN OLD;
    END;
'
    LANGUAGE plpgsql;

CREATE TRIGGER users_clear_trigger
    AFTER DELETE ON users
    FOR EACH ROW EXECUTE FUNCTION users_clear_likes_and_posts();

CREATE TRIGGER posts_clear_trigger
    AFTER DELETE ON posts
    FOR EACH ROW EXECUTE FUNCTION clear_posts_func();