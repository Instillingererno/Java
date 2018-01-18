USE ovelse5;

DROP TABLE IF EXISTS Medlemmer;
DROP TABLE IF EXISTS Leilighet;
DROP TABLE IF EXISTS Boenhet;
DROP TABLE IF EXISTS Borettslag;

CREATE TABLE Borettslag(
  idBorettslag INT AUTO_INCREMENT NOT NULL,
  navn VARCHAR(45) NOT NULL,
  adresse VARCHAR(45) NOT NULL,
  etableringsAar INT NOT NULL,
  PRIMARY KEY (idBorettslag)
);

CREATE TABLE Boenhet(
  idBoenhet INT NOT NULL AUTO_INCREMENT,
  adresse VARCHAR(45) NOT NULL,
  idBorettslag INT NOT NULL,
  PRIMARY KEY (idBoenhet),
  FOREIGN KEY (idBorettslag) REFERENCES Borettslag(idBorettslag)
);

CREATE TABLE Leilighet(
  leilighetsNr INT NOT NULL,
  antallRom INT NOT NULL,
  areal INT NOT NULL,
  etasje INT NOT NULL,
  idBoenhet INT NOT NULL,
  PRIMARY KEY (leilighetsNr),
  FOREIGN KEY (idBoenhet) REFERENCES Boenhet(idBoenhet)
);

CREATE TABLE Medlemmer(
  idMedlemmer INT NOT NULL AUTO_INCREMENT,
  navn VARCHAR(45) NOT NULL,
  tlf VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  leilighetsNr INT UNIQUE,
  PRIMARY KEY (idMedlemmer),
  FOREIGN KEY (leilighetsNr) REFERENCES Leilighet(leilighetsNr)
);