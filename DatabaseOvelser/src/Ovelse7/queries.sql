USE ovelse7;

SELECT oppdrag.* FROM oppdrag LEFT JOIN historikk ON oppdrag.oppdragsNr = historikk.oppdragsNr WHERE historikk.oppdragsNr IS NULL;

INSERT INTO bedrift(navn, tlf, email) VALUES ('Apple', 12345, 'apple@apple.com');

INSERT INTO kvalifikasjon(navn, beskrivelse) VALUES ('data', 'kan ting om data');

INSERT INTO oppdrag(orgNr, idKvalifikasjon, startDato, sluttDato)
    SELECT 1, idKvalifikasjon, '2018-01-19', '2018-01-22'
        FROM kvalifikasjon WHERE navn = 'data'
            LIMIT 1;

INSERT INTO kvalifikasjon(navn, beskrivelse) VALUES ('stol', 'kan ting om stoler');

INSERT INTO oppdrag(orgNr, idKvalifikasjon, startDato, sluttDato)
    SELECT 1, idKvalifikasjon, '2018-01-19', '2018-01-22'
    FROM kvalifikasjon WHERE navn = 'stol'
    LIMIT 1;
