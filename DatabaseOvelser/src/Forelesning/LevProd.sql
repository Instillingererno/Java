-- I tilfelle vi må kjøre scriptet på nytt

DROP TABLE IF EXISTS leveranse;
DROP TABLE IF EXISTS leverandor;
DROP TABLE IF EXISTS produkt;

-- To tabeller 

CREATE TABLE leverandor(
  lev_nr INTEGER PRIMARY KEY AUTO_INCREMENT,
  lev_navn VARCHAR(15) NOT NULL,
  status INTEGER,
  lev_by  VARCHAR(15));

CREATE TABLE produkt(
  prod_nr INTEGER PRIMARY KEY AUTO_INCREMENT,
  prod_navn VARCHAR(10) NOT NULL,
  kode  VARCHAR(10),  
  vekt REAL,
  prod_by VARCHAR(15));

-- Denne tabellen uttrykker en mange-til-mange-kopling mellom de to forrige tabellene

CREATE TABLE leveranse(
  lev_nr INTEGER,
  prod_nr INTEGER,
  antall INTEGER,
  PRIMARY KEY (lev_nr, prod_nr));

-- Fremmednøkler

ALTER TABLE leveranse
  ADD CONSTRAINT leveranse_fk1 FOREIGN KEY(lev_nr) 
  REFERENCES leverandor(lev_nr);
  
ALTER TABLE leveranse
  ADD CONSTRAINT leveranse_fk2 FOREIGN KEY(prod_nr) 
  REFERENCES produkt(prod_nr);


-- Legger inn eksempeldata
INSERT INTO leverandor(lev_navn, status, lev_by)  VALUES('Svendsen', 20, 'Lillehammer');
INSERT INTO leverandor(lev_navn, status, lev_by)  VALUES('Jensen', 10, 'Porsgrunn');
INSERT INTO leverandor(lev_navn, status, lev_by)  VALUES('Bø', 30, 'Porsgrunn');
INSERT INTO leverandor(lev_navn, status, lev_by)  VALUES('Christiansen', 20, 'Lillehammer');
INSERT INTO leverandor(lev_navn, status, lev_by)  VALUES('Andersen', 30, 'Arendal');

INSERT INTO produkt(prod_navn, kode, vekt, prod_by) VALUES('synåler', 'rød', 12, 'Lillehammer');
INSERT INTO produkt(prod_navn, kode, vekt, prod_by) VALUES('binders', 'grønn', 17, 'Porsgrunn');
INSERT INTO produkt(prod_navn, kode, vekt, prod_by) VALUES('skruer', 'blå', 17, 'Risør');
INSERT INTO produkt(prod_navn, kode, vekt, prod_by) VALUES('skruer', 'rød', 14, 'Lillehammer');
INSERT INTO produkt(prod_navn, kode, vekt, prod_by) VALUES('knapper', 'blå', 12, 'Porsgrunn');
INSERT INTO produkt(prod_navn, kode, vekt, prod_by) VALUES('spiker', 'rød', 19, 'Lillehammer');

INSERT INTO leveranse(lev_nr, prod_nr, antall) VALUES(1, 1, 300);
INSERT INTO leveranse(lev_nr, prod_nr, antall) VALUES(1, 2, 200);
INSERT INTO leveranse(lev_nr, prod_nr, antall) VALUES(1, 3, 400);
INSERT INTO leveranse(lev_nr, prod_nr, antall) VALUES(1, 4, 200);
INSERT INTO leveranse(lev_nr, prod_nr, antall) VALUES(1, 5, 100);
INSERT INTO leveranse(lev_nr, prod_nr, antall) VALUES(2, 1, 300);
INSERT INTO leveranse(lev_nr, prod_nr, antall) VALUES(2, 2, 400);
INSERT INTO leveranse(lev_nr, prod_nr, antall) VALUES(3, 2, 200);
INSERT INTO leveranse(lev_nr, prod_nr, antall) VALUES(4, 2, 200);
INSERT INTO leveranse(lev_nr, prod_nr, antall) VALUES(4, 4, 300);
INSERT INTO leveranse(lev_nr, prod_nr, antall) VALUES(4, 5, 400);



