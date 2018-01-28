DROP DATABASE IF EXISTS Oving7;
CREATE DATABASE Oving7;
USE Oving7;

DROP TABLE IF EXISTS kandidat;
DROP TABLE IF EXISTS bedrift;
DROP TABLE IF EXISTS kvalifikasjon;
DROP TABLE IF EXISTS oppdrag;
DROP TABLE IF EXISTS jobbhistorikk;

CREATE TABLE kandidat(
    fornavn VARCHAR(20) NOT NULL, 
    etternavn VARCHAR(20) NOT NULL, 
    telefon INT, 
    epost VARCHAR(30) PRIMARY KEY
); 


CREATE TABLE bedrift(
    organisasjonsnr INT PRIMARY KEY AUTO_INCREMENT, 
    bedrift_navn VARCHAR(30) NOT NULL, 
    telefon INT, 
    epost VARCHAR(30)
);

CREATE TABLE kvalifikasjon(
    beskrivelse VARCHAR(30) NOT NULL, 
    kvalifikasjons_id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE kvalifikasjon_kandidat(
    kvalifikasjons_id INT,
    epost VARCHAR(30),
    PRIMARY KEY(kvalifikasjons_id, epost),
    FOREIGN KEY(kvalifikasjons_id) REFERENCES kvalifikasjon(kvalifikasjons_id),
    FOREIGN KEY(epost) REFERENCES kandidat(epost)
);

CREATE TABLE oppdrag(
    oppdragsnummer INT PRIMARY KEY AUTO_INCREMENT, 
    bedrift_navn VARCHAR(30), 
    kvalifikasjons_id INT, 
    startdato DATE, 
    sluttdato DATE,
    epost VARCHAR(30),
    organisasjonsnr INT, 
    FOREIGN KEY (kvalifikasjons_id) REFERENCES kvalifikasjon(kvalifikasjons_id),
    FOREIGN KEY (epost) REFERENCES kandidat(epost),
    FOREIGN KEY (organisasjonsnr) REFERENCES bedrift(organisasjonsnr)  
);

CREATE TABLE jobbhistorikk(
    id INT PRIMARY KEY AUTO_INCREMENT, 
    startdato DATE, 
    sluttdato DATE, 
    antall_timer INT, 
    oppdragsnummer INT UNIQUE,
    FOREIGN KEY(oppdragsnummer) REFERENCES oppdrag(oppdragsnummer)
);

INSERT INTO bedrift(bedrift_navn, telefon, epost) VALUES(
    "Skolen", 911, "Skolen.school@skolen.com"
);

INSERT INTO bedrift(bedrift_navn, telefon, epost) VALUES(
    "Firma1", 118, "Firma.1@gmail.com"
);

INSERT INTO bedrift(bedrift_navn, telefon, epost) VALUES(
    "Hax", 9001, "Hackers.cheats@scripts.com"
);

INSERT INTO kandidat VALUES(
    "Nissen", "Jul", 12345678, "hei.der@hotmail.com"
);

INSERT INTO kandidat VALUES(
    "Lars", "Larsen", 666, "Ikke.les@dette.com"
);

INSERT INTO kandidat VALUES(
    "Han", "Der", 313, "Hvem.da@who.com"
);

INSERT INTO kandidat VALUES(
    "Han her", "Er ikke med", 1000, "han.her@Ikke.med"
);

INSERT INTO kandidat VALUES(
    "Heller ikke", "med", 2010, "Heller.ikke@med.com"
);

INSERT INTO kvalifikasjon(beskrivelse) VALUES(
    "Gå"
);

INSERT INTO kvalifikasjon(beskrivelse) VALUES(
    "Sove"
);

INSERT INTO kvalifikasjon(beskrivelse) VALUES(
    "Snakke"
);

INSERT INTO kvalifikasjon_kandidat(kvalifikasjons_id, epost) VALUES(
    1, "Ikke.les@dette.com"
);

/*INSERT INTO kvalifikasjon_kandidat*/

INSERT INTO kvalifikasjon_kandidat(kvalifikasjons_id, epost) VALUES(
    2, "Hvem.da@who.com"
);

INSERT INTO kvalifikasjon_kandidat(kvalifikasjons_id, epost) VALUES(
    3, "hei.der@hotmail.com"
); 

INSERT INTO oppdrag(bedrift_navn, kvalifikasjons_id, startdato, sluttdato, epost, organisasjonsnr) VALUES(
    "Skolen", 1, "2018-01-12", "2018-01-15", "Hvem.da@who.com", 1
);

INSERT INTO oppdrag(bedrift_navn, kvalifikasjons_id, startdato, sluttdato, epost, organisasjonsnr) VALUES(
    "Hax", 3, "2017-08-15", "2018-01-01", "hei.der@hotmail.com", 2
);

INSERT INTO oppdrag(bedrift_navn, kvalifikasjons_id, startdato, sluttdato, epost, organisasjonsnr) VALUES(
    "Firma1", 2, "2016-11-12", "2019-12-12", "Ikke.les@dette.com", 3
);

INSERT INTO oppdrag(bedrift_navn, kvalifikasjons_id, startdato, sluttdato, epost, organisasjonsnr) VALUES(
    "Skolen", NULL, "2020-02-01", "2021-12-24", "hei.der@hotmail.com", 2
);

INSERT INTO oppdrag(bedrift_navn, kvalifikasjons_id, startdato, sluttdato, epost, organisasjonsnr) VALUES(
    "Hax", 2, "2010-7-18", "2012-3-23", "Hvem.da@who.com", 1
);

INSERT INTO jobbhistorikk(startdato, sluttdato, antall_timer, oppdragsnummer) VALUES(
    "2018-01-12", "2018-01-15", 100, 1
);

INSERT INTO jobbhistorikk(startdato, sluttdato, antall_timer,  oppdragsnummer) VALUES(
    "2017-08-17", "2018-01-10", 670, 2
);

INSERT INTO jobbhistorikk(startdato, sluttdato, antall_timer,  oppdragsnummer) VALUES(
    "2010-06-12", "2009-12-13", -1000, 3  -- Don't question it!
);

SELECT * FROM kandidat; 
SELECT * FROM bedrift;
SELECT * FROM kvalifikasjon;
SELECT * FROM oppdrag;
SELECT * FROM jobbhistorikk;

-- Oppgave 1d)

-- 1) Lag en liste over alle bedriftene. Navn, telefon og epost til bedriften skal skrives ut.

SELECT bedrift_navn, telefon, epost FROM bedrift;


-- 2) Lag en liste over alle oppdragene. Om hvert oppdrag skal du skrive ut oppdragets nummer samt navn og telefonnummer til bedriften som tilbyr oppdraget.

SELECT o.oppdragsnummer, bed.bedrift_navn, telefon
FROM oppdrag o RIGHT JOIN bedrift bed
ON o.bedrift_navn = bed.bedrift_navn;

-- 3) Lag en liste over kandidater og kvalifikasjoner. 
-- Kandidatnavn og kvalifikasjonsbeskrivelse skal med i utskriften i tillegg til løpenumrene som identifiserer kandidat og kvalifikasjon.

SELECT DISTINCT kan.fornavn, kval.beskrivelse, CONCAT(link.kvalifikasjons_id, "   ||   ", link.epost) AS "Løpenummer"
FROM kandidat kan, kvalifikasjon kval, kvalifikasjon_kandidat link
WHERE kan.epost = link.epost AND kval.kvalifikasjons_id = link.kvalifikasjons_id; 


-- 4) Som oppgave 3), men få med de kandidatene som ikke er registrert med kvalifikasjoner.

SELECT DISTINCT kan.fornavn, kval.beskrivelse, CONCAT(link.kvalifikasjons_id, "   ||   ", link.epost) AS "Løpenummer"
FROM kandidat kan LEFT OUTER JOIN kvalifikasjon_kandidat link ON (kan.epost = link.epost) 
LEFT OUTER JOIN kvalifikasjon kval ON (link.kvalifikasjons_id = kval.kvalifikasjons_id)
WHERE kval.kvalifikasjons_id IS NULL;


-- 5) Skriv ut jobbhistorikken til en bestemt vikar, gitt kandidatnr. Vikarnavn, sluttdato, oppdragsnr og bedriftsnavn skal med.

SELECT CONCAT(kan.fornavn, " ", kan.etternavn) AS "Navn", jobb.sluttdato, opp.oppdragsnummer, opp.bedrift_navn
FROM jobbhistorikk jobb INNER JOIN oppdrag opp ON jobb.oppdragsnummer = opp.oppdragsnummer
    INNER JOIN kandidat kan ON opp.epost = kan.epost
WHERE opp.epost = "Hvem.da@who.com";