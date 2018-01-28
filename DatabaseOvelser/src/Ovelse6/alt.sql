USE ovelse6;

-- 1a
SELECT tittel, utgitt_aar FROM bok WHERE utgitt_aar > 1995;

-- 1b
SELECT bok.tittel, forlag.forlag_navn FROM bok, forlag;
-- resultatet blir at man alle mulige sammensetninger av tittel og forlag_navn

-- 1c
SELECT * FROM forlag, bok
    where forlag.forlag_id = bok.forlag_id;

SELECT * FROM forlag
  NATURAL JOIN bok;

-- 1d
SELECT bok_id FROM bok UNION SELECT bok_id FROM bok_forfatter;
-- Spørringen gir alle bok_id'ene som eksisterer både i bok og bok_id

SELECT fornavn FROM konsulent UNION SELECT fornavn FROM forfatter;
-- Spørringen gir alle fornavnene til folk som er både konsulenter og forfatter


-- 2a
SELECT forlag_navn FROM forlag;
-- brukte en projeksjon

-- 2b
SELECT DISTINCT forlag.forlag_id FROM forlag
  WHERE bok.forlag_id NOT IN (SELECT bok.forlag_id FROM bok);
-- projeksjon, seleksjon og minus

-- 2c
SELECT * FROM forfatter WHERE fode_aar = 1948;
-- seleksjon

-- 2d
SELECT forlag_navn, adresse FROM forlag
  INNER JOIN bok ON forlag.forlag_id = bok.forlag_id
    WHERE bok.tittel = 'Generation X';
-- Seleksjon og projeksjon, naturlig forening

-- 2e
SELECT tittel FROM bok
  INNER JOIN bok_forfatter ON bok.bok_id = bok_forfatter.bok_id
  INNER JOIN forfatter ON bok_forfatter.forfatter_id = forfatter.forfatter_id
WHERE forfatter.etternavn = 'Hamsun';
-- Seleksjon og projeksjon, naturlig forening

-- 2f
SELECT bok.tittel, bok.utgitt_aar, forlag.forlag_navn, forlag.adresse, forlag.telefon
  FROM bok RIGHT OUTER JOIN forlag ON bok.forlag_id = forlag.forlag_id;
-- Seleksjon og projeksjon, naturlig forening