CREATE TABLE "users" (
                         "username" varchar PRIMARY KEY,
                         "pass_hash" varchar NOT NULL,
                         "created_at" timestamp DEFAULT now(),
                         "country" varchar NOT NULL
);

CREATE TABLE "authorities" (
                               "username" varchar NOT NULL,
                               "role" varchar NOT NULL,
                               UNIQUE ("username", "role")
);

CREATE TABLE "posts" (
                         "id" SERIAL PRIMARY KEY,
                         "owner" varchar NOT NULL,
                         "title" varchar NOT NULL,
                         "content" text NOT NULL,
                         "created_at" timestamp DEFAULT now(),
                         "updated_at" timestamp DEFAULT now()
);

CREATE TABLE "tags" (
                        "label" varchar PRIMARY KEY,
                        "color" varchar DEFAULT '#000'
);

CREATE TABLE "likes" (
                         "username" varchar,
                         "post_id" serial,
                         PRIMARY KEY ("username", "post_id")
);

CREATE TABLE "tags_posts" (
                              "tag_label" varchar,
                              "post_id" serial,
                              UNIQUE ("tag_label", "post_id")
);

ALTER TABLE "posts" ADD FOREIGN KEY ("owner") REFERENCES "users" ("username")
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE "likes" ADD FOREIGN KEY ("username") REFERENCES "users" ("username")
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE "likes" ADD FOREIGN KEY ("post_id") REFERENCES "posts" ("id")
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE "tags_posts" ADD FOREIGN KEY ("tag_label") REFERENCES "tags" ("label")
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "tags_posts" ADD FOREIGN KEY ("post_id") REFERENCES "posts" ("id")
ON DELETE CASCADE ON UPDATE CASCADE;
