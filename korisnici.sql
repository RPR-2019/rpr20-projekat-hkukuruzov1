BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "korisnici" (
	"username"	TEXT,
	"password"	TEXT,
	"admin"	INTEGER,
	"ime"	TEXT,
	"prezime"	TEXT,
	PRIMARY KEY("username")
);
CREATE TABLE IF NOT EXISTS "radnovrijeme" (
	"id"	TEXT,
	"pocetak"	TEXT,
	"kraj"	TEXT,
	"vrstarada"	TEXT,
	"dan"	INTEGER,
	"mjesec"	TEXT,
	"godina"	INTEGER,
	FOREIGN KEY("id") REFERENCES "korisnici"("username")
);
INSERT INTO "korisnici" VALUES ('admin','admin',1,'admin','admin');
INSERT INTO "korisnici" VALUES ('haris','0000',0,'Haris','Kukuruzović');
COMMIT;
