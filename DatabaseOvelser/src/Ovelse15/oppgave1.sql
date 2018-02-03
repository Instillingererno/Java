USE ovelse15;

-- 1. List ut all informasjon (ordrehode og ordredetalj) om ordrer for leverandør nr 44.
SELECT *
FROM ordrehode
    NATURAL JOIN ordredetalj
WHERE levnr = 44;

-- 2. Finn navn og by ("LevBy") for leverandører som kan levere del nummer 1.
SELECT DISTINCT navn, levby
FROM levinfo
    INNER JOIN prisinfo USING (levnr)
    INNER JOIN delinfo USING (delnr)
WHERE delnr = 1;

-- 3. Finn nummer, navn og pris for den leverandør som kan levere del nummer 201 til billigst pris.
SELECT DISTINCT levnr, navn, MIN(pris)
FROM levinfo
    INNER JOIN prisinfo USING (levnr)
    INNER JOIN delinfo USING (delnr)
WHERE delnr = 201;

-- 4. Lag fullstendig oversikt over ordre nr 16, med ordrenr, dato, delnr, beskrivelse, kvantum, (enhets-)pris og beregnet beløp (=pris*kvantum).
SELECT ordrenr, dato, delnr, beskrivelse, kvantum, pris AS 'enhets pris', ROUND(pris*kvantum, 1) AS 'beregnet beløp'
FROM levinfo
    INNER JOIN prisinfo USING (levnr)
    INNER JOIN delinfo USING (delnr)
    INNER JOIN ordredetalj USING (delnr)
    INNER JOIN ordrehode USING (ordrenr)
WHERE ordrenr = 16 AND prisinfo.levnr = ordrehode.levnr;

-- 5. Finn delnummer og leverandørnummer for deler som har en pris som er høyere enn prisen for del med katalognr X7770.
SELECT delnr, levnr
FROM prisinfo
WHERE pris > (SELECT pris FROM prisinfo WHERE katalognr = 'X7770');

-- 6. i:
/*
    Tenk deg at tabellen levinfo skal deles i to.
    Sammenhengen mellom by og fylke skal tas ut av tabellen.
    Det er unødvendig å lagre fylketilhørigheten for hver
    forekomst av by. Lag én ny tabell som inneholder byer
    og fylker. Fyll denne med data fra levinfo. Lag også en
    tabell som er lik levinfo unntatt kolonnen Fylke. (Denne
    splittingen av tabellen levinfo gjelder bare i denne
    oppgaven. I resten av oppgavesettet antar du at du har
     den opprinnelige levinfo-tabellen.)
 */
CREATE TABLE byFylke(
    idByFylke INT AUTO_INCREMENT,
    sted VARCHAR(20),
    fylke VARCHAR(20),
    PRIMARY KEY (idByFylke)
);
INSERT INTO byFylke(sted, fylke)
    SELECT DISTINCT levby, fylke
FROM levinfo;

CREATE TABLE nyLevInfo
    SELECT levnr, navn, adresse, levby, postnr
    FROM levinfo;

-- 6. ii:
/*
    Lag en virtuell tabell (view) slik at brukerne i størst
    mulig grad kan jobbe på samme måte mot de to nye tabellene
    som den gamle. Prøv ulike kommandoer mot tabellen (select,
    update, delete, insert). Hvilke begrensninger, hvis noen,
    har brukerne i forhold til tidligere?
 */
CREATE VIEW byTilhorighet AS
    SELECT * FROM nyLevInfo
    INNER JOIN byFylke ON nyLevInfo.levby = byFylke.sted;

-- 7.
/*
    Anta at en vurderer å slette opplysningene om de
    leverandørene som ikke er representert i Prisinfo-tabellen.
    Finn ut hvilke byer en i tilfelle ikke får leverandør i.
    (Du skal ikke utføre slettingen.) (Tips: Svaret skal bli
    kun én by, "Ål".)   FEIL I OPPGAVEN DER ER TO LEVERANDØRER
    SOM IKKE OPPSTÅR I PRISINFO 12 og 81
 */
SELECT levby
FROM prisinfo
    RIGHT JOIN levinfo USING (levnr)
WHERE prisinfo.levnr IS NULL;

-- 8.
/*
    Finn leverandørnummer for den leverandør som kan levere
    ordre nr 18 til lavest totale beløp (vanskelig).
 */
SELECT levinfo.levnr, navn, SUM((pris*kvantum))
FROM levinfo
    INNER JOIN prisinfo USING (levnr)
    INNER JOIN delinfo USING (delnr)
    INNER JOIN ordredetalj USING (delnr)
    INNER JOIN ordrehode USING (ordrenr)
WHERE ordrenr = 18
GROUP BY navn
HAVING COUNT(*) > 1
ORDER BY SUM((pris*kvantum)) ASC
LIMIT 1;