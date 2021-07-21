BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "korisnici" (
	"username"	TEXT,
	"password"	TEXT,
	"admin"	INTEGER,
	PRIMARY KEY("username")
);
CREATE TABLE IF NOT EXISTS "radnovrijeme" (
	"id"	TEXT,
	"pocetak"	TEXT,
	"kraj"	TEXT,
	"vrstarada"	TEXT,
	FOREIGN KEY("id") REFERENCES "korisnici"("username"),
	PRIMARY KEY("id")
);
INSERT INTO "korisnici" VALUES ('admin','admin',1);
INSERT INTO "korisnici" VALUES ('haris','0000',0);
COMMIT;
