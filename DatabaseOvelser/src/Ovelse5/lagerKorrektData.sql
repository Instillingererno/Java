USE ovelse5;

INSERT INTO borettslag(navn, adresse, etableringsAar)
    VALUES ('Engåsen borettslag', 'Engåsveien 1-15', 1980);

INSERT INTO boenhet(adresse, idBorettslag)
    SELECT 'Engåsveien 1', idBorettslag
        FROM borettslag
        WHERE navn = 'Engåsen borettslag'
        LIMIT 1;

INSERT INTO leilighet(leilighetsNr, antallRom, areal, etasje, idBoenhet)
    SELECT 100, 3, 100, 1, idBoenhet
        FROM boenhet
        WHERE adresse = 'Engåsveien 1'
        LIMIT 1;

INSERT INTO medlemmer(navn, tlf, email, leilighetsNr)
    SELECT 'Ole Geirsson', '123456', 'ole@hotmail.com', leilighetsNr
        FROM leilighet
        WHERE leilighetsNr = 100
        LIMIT 1;

INSERT INTO medlemmer(navn, tlf, email, leilighetsNr)
    VALUES ('Tom Tomsson', '54321', 'tom@gmail.com', NULL);