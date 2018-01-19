SELECT oppdrag.* FROM oppdrag LEFT JOIN historikk ON oppdrag.oppdragsNr = historikk.oppdragsNr WHERE historikk.oppdragsNr IS NULL;

INSERT INTO bedrift(navn, tlf, email) VALUES ('Apple', 12345, 'apple@apple.com');

INSERT INTO kvalifikasjon(navn, beskrivelse) VALUES ('data', 'kan ting om data');

INSERT INTO oppdrag(orgNr, idKvalifikasjon, startDato, sluttDato)
    SELECT 1001010101010, idKvalifikasjon,