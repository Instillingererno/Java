USE ovelse7;

DROP TABLE IF EXISTS historikk;
DROP TABLE IF EXISTS oppdrag;
DROP TABLE IF EXISTS kandidatKvalifikasjon;
DROP TABLE IF EXISTS kvalifikasjon;
DROP TABLE IF EXISTS kandidat;
DROP TABLE IF EXISTS bedrift;


CREATE TABLE bedrift (
    orgNr INT AUTO_INCREMENT,
    navn VARCHAR(30) NOT NULL,
    tlf VARCHAR(12) NOT NULL,
    email VARCHAR(30) NOT NULL,
    PRIMARY KEY (orgNr)
);

CREATE TABLE kvalifikasjon (
    idKvalifikasjon INT AUTO_INCREMENT,
    navn VARCHAR(30) NOT NULL,
    beskrivelse VARCHAR(120) NOT NULL,
    PRIMARY KEY (idKvalifikasjon)
);

CREATE TABLE kandidat (
    idKandidat INT AUTO_INCREMENT,
    fornavn VARCHAR(30) NOT NULL,
    etternavn VARCHAR(30) NOT NULL,
    tlf VARCHAR(12) NOT NULL,
    email VARCHAR(30) NOT NULL,
    PRIMARY KEY (idKandidat)
);

CREATE TABLE kandidatKvalifikasjon (
    idKanKval INT AUTO_INCREMENT,
    idKandidat INT NOT NULL,
    idKvalifikasjon INT NOT NULL,
    PRIMARY KEY (idKanKval),
    FOREIGN KEY (idKandidat) REFERENCES kandidat(idKandidat),
    FOREIGN KEY (idKvalifikasjon) REFERENCES kvalifikasjon(idKvalifikasjon)
);

CREATE TABLE oppdrag (
    oppdragsNr INT AUTO_INCREMENT,
    orgNr INT NOT NULL,
    idKvalifikasjon INT,
    startDato DATE,
    sluttDato DATE,
    PRIMARY KEY (oppdragsNr),
    FOREIGN KEY (orgNr) REFERENCES bedrift(orgNr),
    FOREIGN KEY (idKvalifikasjon) REFERENCES kvalifikasjon(idKvalifikasjon)
);

CREATE TABLE historikk (
    idHistorikk INT AUTO_INCREMENT,
    startDato DATE,
    sluttDato DATE,
    oppdragsNr INT NOT NULL UNIQUE,
    idKandidat INT NOT NULL,
    PRIMARY KEY (idHistorikk),
    FOREIGN KEY (oppdragsNr) REFERENCES oppdrag(oppdragsNr),
    FOREIGN KEY (idKandidat) REFERENCES kandidat(idKandidat)
);