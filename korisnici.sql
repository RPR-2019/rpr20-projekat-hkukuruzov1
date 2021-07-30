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
INSERT INTO "korisnici" VALUES ('adnan','0000',0,'Adnan','Mahmić');
INSERT INTO "radnovrijeme" VALUES ('haris','15:14:33','15:14:39','Remote',28,'JULY',2021);
INSERT INTO "radnovrijeme" VALUES ('haris','16:03:45','16:03:51','Remote',28,'JULY',2021);
INSERT INTO "radnovrijeme" VALUES ('haris','10:15:25','10:15:36','Onsite',30,'JULY',2021);
COMMIT;
