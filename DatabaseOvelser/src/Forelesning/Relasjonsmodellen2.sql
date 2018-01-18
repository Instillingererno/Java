USE forelesning;

-- Seleksjon er en SELECT * med bruk av WHERE --
/*
  Seleksjon, også kalt restriksjon,
  lager en ny relasjon ved å velge
  ut bestemte tupler i en
  eksisterende relasjon.
 */
-- SELECT * FROM --- WHERE ---;

-- Projeksjon er en SELECT der kolonner er definert --
/*
  Projeksjon, også kalt reduksjon,
  lager en ny relasjon ved å velge
  ut bestemte kolonner i en
  eksisterende relasjon.
 */
-- SELECT DISTINCT ---, ---, --- FROM ---; potensielt uten DISTINCT men helst med

/*
  Produkt, er det samme som et kartesisk produkt.
  Vi kombinerer altså alle tupler i en relasjon A
  med alle tupler i en relasjon B. Hivs A har n
  attributer og r tupler og B har m attributer
  og s tupler, vil A x B
 */
-- SELECT * FROM ---, ---; (FROM to forskjellige tabeller)

/*
  Indre forening, (join) henter de tuplene fra et
  produkt som oppfyller et krav på et felles
  attribut. Typisk er
  -de to relasjonene forskjellige
  -fellesattributt er primærnøkkel i den ene relasjonen og fremmednøkkel i den andre, og
  -fellesattributtet har samme navn i de to relasjonene, men må ikke.
 */
SELECT * FROM leverandor, leveranse
  WHERE leverandor.lev_nr = leveranse.lev_nr;

SELECT leverandor.*, prod_nr, antall
  FROM leverandor JOIN leveranse l
      ON leverandor.lev_nr = l.lev_nr;

SELECT * FROM leverandor NATURAL JOIN leveranse;


-- ANNET --
SELECT * FROM produkt WHERE kode='rød' AND vekt<15;

SELECT * FROM leveranse WHERE antall IN (100, 200, 400);
-- In forventer en liste av mulige svar --
SELECT * FROM leveranse WHERE antall NOT IN (100,200);

SELECT * FROM leveranse
  LEFT JOIN leverandor l ON leveranse.lev_nr = l.lev_nr;

SELECT * FROM leveranse
  LEFT OUTER JOIN leverandor l ON leveranse.lev_nr = l.lev_nr;
