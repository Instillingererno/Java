USE ovelse15;

-- 1.
/*
    Sett opp en SELECT-setning som er UNION mellom alle forlag med Oslo-nummer
    (telefonnummer begynner med 2) og alle som ikk er Oslo-nummer. Får du med
    forlaget med NULL-verdi på telefonnummer? Hvis ikke, utvid unionen med en
    mengde til.
 */
SELECT forlag_navn, telefon FROM forlag WHERE telefon REGEXP '^[2]'
UNION
SELECT forlag_navn, telefon FROM forlag WHERE telefon REGEXP '[^2]*'
UNION
SELECT forlag_navn, telefon FROM forlag WHERE telefon IS NULL;

-- 2.
/*
    Sett opp SQL-setninger som finner gjennomsnittlig alder på forfattere der
    fødselsåret er oppgitt. For forfattere der dødsåret ikke er oppgitt, skal
    du kun ta med de som født etter året 1900. Tips for å få ut året i år:
      MySQL: SELECT YEAR(CURRENT_DATE) FROM ... hvilken tabell som helst...
 */
-- Forfattere der fødselsår er oppgitt,
-- Har funnet en feil i dataen, Nick Hornby var født i 1957 ikke 1857
SELECT ROUND(
            AVG(
                CASE
                    WHEN (dod_aar IS NULL AND fode_aar > 1900)
                        THEN (YEAR(CURRENT_DATE) - fode_aar)
                    ELSE (dod_aar - forfatter.fode_aar)
                END
            ), 1
       ) AS 'Gjennomsnitt alder'
FROM forfatter
WHERE fode_aar IS NOT NULL;

-- 3.
/*
    Sett opp SQL-setninger som finner hvor stor andel av forfatterne som
    ble med i beregningene under b)
 */
SELECT
    CONCAT(
        ROUND(
            (SELECT COUNT(fornavn)
            FROM forfatter
            WHERE (dod_aar IS NULL AND fode_aar > 1900) OR (dod_aar IS NOT NULL)
            LIMIT 1)
            / COUNT(fornavn)*100, 1
        ), "%"
    ) AS 'Andel forfattere tatt med i gjennomsnitts alder beregnelse'
FROM forfatter;
