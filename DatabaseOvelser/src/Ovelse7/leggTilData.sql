USE ovelse7;

INSERT INTO bedrift(navn, tlf, email) VALUES ('Apple', 12345, 'apple@apple.com');
INSERT INTO bedrift(`orgNr`, `navn`, `tlf`, `email`) VALUES ('2', 'Microsoft', '54321', 'microsoft@ms.com');
INSERT INTO bedrift(`orgNr`, `navn`, `tlf`, `email`) VALUES ('3', 'Yahoo', '68543', 'yahoo@hotmail.com');
INSERT INTO bedrift(`orgNr`, `navn`, `tlf`, `email`) VALUES ('4', 'Google', '54556', 'google@gmail.com');

INSERT INTO kandidat(fornavn, etternavn, tlf, email) VALUES ('Kari', 'Solbakken', '21421341', 'kari.s@gmail.com');
INSERT INTO `ovelse7`.`kandidat` (`fornavn`, `etternavn`, `tlf`, `email`) VALUES ('Mats', 'Kvelland', '56143561', 'mads.k@hotmail.com');
INSERT INTO `ovelse7`.`kandidat` (`fornavn`, `etternavn`, `tlf`, `email`) VALUES ('Marie', 'ftrgahn', '635415846', 'ijunsfd');
INSERT INTO kandidat(fornavn, etternavn, tlf, email) VALUES ('Kan', 'Ingenting', '1244243342', 'kan.i@apple.com');

INSERT INTO kvalifikasjon(navn, beskrivelse) VALUES ('data', 'kan ting om data');
INSERT INTO kvalifikasjon(navn, beskrivelse) VALUES ('stol', 'kan ting om stoler');
INSERT INTO `ovelse7`.`kvalifikasjon` (`navn`, `beskrivelse`) VALUES ('bil', 'kan ting om biler');
INSERT INTO `ovelse7`.`kvalifikasjon` (`navn`, `beskrivelse`) VALUES ('java', 'kan java');
INSERT INTO `ovelse7`.`kvalifikasjon` (`navn`, `beskrivelse`) VALUES ('python', 'kan python');


INSERT INTO kandidatkvalifikasjon(idKandidat, idKvalifikasjon) VALUES (1,1);
INSERT INTO kandidatkvalifikasjon(idKandidat, idKvalifikasjon) VALUES (1,2);
INSERT INTO kandidatkvalifikasjon(idKandidat, idKvalifikasjon) VALUES (2,3);
INSERT INTO kandidatkvalifikasjon(idKandidat, idKvalifikasjon) VALUES (3,4);
INSERT INTO kandidatkvalifikasjon(idKandidat, idKvalifikasjon) VALUES (3,5);

INSERT INTO oppdrag(orgNr, idKvalifikasjon, startDato, sluttDato, idKandidat)
    SELECT 1, idKvalifikasjon, '2018-01-19', '2018-01-22', 1
    FROM kvalifikasjon WHERE navn = 'data'
    LIMIT 1;
INSERT INTO oppdrag(orgNr, idKvalifikasjon, startDato, sluttDato, idKandidat)
    SELECT 1, idKvalifikasjon, '2018-02-19', '2018-02-22', 2
    FROM kvalifikasjon WHERE navn = 'stol'
    LIMIT 1;
INSERT INTO oppdrag(orgNr, idKvalifikasjon, startDato, sluttDato)
    SELECT 2, idKvalifikasjon, '2018-03-19', '2018-03-22'
    FROM kvalifikasjon WHERE navn = 'bil'
    LIMIT 1;
INSERT INTO oppdrag(orgNr, idKvalifikasjon, startDato, sluttDato)
    SELECT 3, idKvalifikasjon, '2018-04-19', '2018-04-22'
    FROM kvalifikasjon WHERE navn = 'java'
    LIMIT 1;
INSERT INTO oppdrag(orgNr, idKvalifikasjon, startDato, sluttDato)
    SELECT 4, idKvalifikasjon, '2018-04-19', '2018-04-22'
    FROM kvalifikasjon WHERE navn = 'python'
    LIMIT 1;
INSERT INTO oppdrag(orgNr, idKvalifikasjon, startDato, sluttDato, idKandidat)
    SELECT 1, idKvalifikasjon, '2018-01-19', '2018-01-22', 2
    FROM kvalifikasjon WHERE navn = 'stol'
    LIMIT 1;

INSERT INTO historikk(oppdragsNr, startDato, sluttDato) VALUES (6, '2018-01-19', '2018-01-23');
INSERT INTO historikk(oppdragsNr, startDato, sluttDato) VALUES (1, '2018-01-19', '2018-01-30');
INSERT INTO historikk(oppdragsNr, startDato, sluttDato) VALUES (2, '2018-02-19', '2018-02-23');