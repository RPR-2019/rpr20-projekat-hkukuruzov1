BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "radnovrijeme" (
	"id"	TEXT,
	"pocetak"	TEXT,
	"kraj"	TEXT,
	"vrstarada"	TEXT,
	FOREIGN KEY("id") REFERENCES "korisnici"("username")
);
CREATE TABLE IF NOT EXISTS "korisnici" (
	"username"	TEXT,
	"password"	TEXT,
	"admin"	INTEGER,
	"ime"	TEXT,
	"prezime"	TEXT,
	PRIMARY KEY("username")
);
INSERT INTO "radnovrijeme" VALUES ('haris','04:04:46','04:04:47','Onsite');
INSERT INTO "radnovrijeme" VALUES ('haris','06:13:43','06:13:47','Remote');
INSERT INTO "radnovrijeme" VALUES ('haris','06:44:21','06:44:24','Remote');
INSERT INTO "korisnici" VALUES ('admin','admin',1,'admin','admin');
INSERT INTO "korisnici" VALUES ('haris','0000',0,'Haris','Kukuruzovic');
COMMIT;
