USE ovelse5;

INSERT INTO borettslag(navn, adresse, etableringsAar)
VALUES ('Engåsen borettslag', 'Engåsveien 1-15', 1980);

INSERT INTO boenhet(adresse, idBorettslag)
  VALUES ('Engåsveien 1', 0);

INSERT INTO leilighet(leilighetsNr, antallRom, areal, etasje, idBoenhet)
  VALUES (100, 3, 100, 1, 0);

INSERT INTO medlemmer(navn, tlf, email, leilighetsNr)
  VALUES ('Ole Geirsson', '123456', 'ole@hotmail.com', 0);
