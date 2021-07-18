BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "korisnici" (
	"username"	TEXT,
	"password"	TEXT,
	"admin"	INTEGER,
	PRIMARY KEY("username")
);
INSERT INTO "korisnici" VALUES ('admin','admin',1);
INSERT INTO "korisnici" VALUES ('haris','0000',0);
COMMIT;
