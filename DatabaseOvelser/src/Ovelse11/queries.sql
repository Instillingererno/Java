USE ovelse11;

-- 1. Finn alle borettslag etablert i årene 1975-1985
SELECT * FROM borettslag
WHERE etabl_aar BETWEEN 1975 AND 1985;

-- 2. Skriv ut en liste over andelseiere. Listen skal ha linjer som ser slik ut (tekster i kursiv er data fra databasen):
SELECT CONCAT(fornavn, " ", etternavn, ","," ansiennitet:", " ", ansiennitet, " år") AS Andelseiere
FROM andelseier ORDER BY ansiennitet DESC;

-- 3. I hvilken år ble det eldste borettslaget etablert
SELECT MIN(etabl_aar)
FROM borettslag;

-- 4. Finn adressene til alle bygninger som inneholder leiligheter med minst tre rom.
SELECT DISTINCT bygn_adr
FROM bygning
    INNER JOIN leilighet USING (bygn_id)
WHERE ant_rom > 3;

-- 5. Finn antall bygninger i borettslaget "Tertitten".
SELECT COUNT(bygn_id)
FROM bygning
    INNER JOIN borettslag USING (bolag_navn)
WHERE bolag_navn = "Tertitten";

-- 6. Lag en liste som viser antall bygninger i hvert enkelt borettslag. Listen skal være sortert på borettslagnavn. Husk at det kan finnes borettslag uten bygninger - de skal også med.
SELECT bolag_navn, COUNT(bygn_id) AS "Antall bygninger"
FROM borettslag
    LEFT JOIN bygning USING (bolag_navn)
GROUP BY bolag_navn
ORDER BY bolag_navn;

-- 7. Finn antall leiligheter i borettslaget "Tertitten".
SELECT COUNT(leil_nr) AS "Antall leiligheter"
FROM leilighet
    INNER JOIN bygning USING (bygn_id)
 WHERE bolag_navn = "Tertitten";

-- 8. Hvor høyt kan du bo i borettslaget "Tertitten".
SELECT MAX(etasje) AS "Høyeste etasje"
FROM leilighet
    INNER JOIN bygning USING (bygn_id)
    INNER JOIN borettslag b USING (bolag_navn)
WHERE bolag_navn = "Tertitten";

-- 9. Finn navn og nummer til andelseiere som ikke har leilighet
SELECT CONCAT(fornavn, " ", etternavn) AS "Navn", telefon
FROM andelseier
    LEFT JOIN leilighet USING (and_eier_nr)
WHERE leil_nr IS NULL;

-- 10. Finn antall andelseiere pr borettslag, sortert etter antallet. Husk at det kan finnes borettslag uten andelseiere - de skal også med.
SELECT bolag_navn, COUNT(and_eier_nr) AS "Antall andelseiere"
FROM borettslag
    LEFT JOIN andelseier USING (bolag_navn)
GROUP BY bolag_navn
ORDER BY COUNT(and_eier_nr) DESC;

-- 11. Skriv ut en liste over alle andelseiere. For de som har leilighet, skal leilighetsnummeret skrives ut.
SELECT andelseier.*, leil_nr
FROM andelseier
    LEFT JOIN leilighet USING (and_eier_nr);

-- 12. Hvilke borettslag har leiligheter med eksakt 4 rom?
SELECT DISTINCT bolag_navn
FROM bygning
    INNER JOIN leilighet USING (bygn_id)
WHERE ant_rom = 4;

/*
    13. Skriv ut en liste over antall andelseiere
    pr postnr og poststed, begrenset til de som bor
    i leiligheter tilknyttet et borettslag. Husk
    at postnummeret til disse er postnummeret til
    bygningen de bor i, og ikke postnummeret til
    borettslaget. Du trenger ikke ta med poststeder
    med 0 andelseiere. (Ekstraoppgave: Hva hvis vi
    vil ha med poststeder med 0 andelseiere?)
 */
SELECT postnr, poststed, COUNT(and_eier_nr)
FROM poststed
    INNER JOIN bygning USING (postnr)
    INNER JOIN leilighet USING (bygn_id)
    INNER JOIN andelseier USING (and_eier_nr);

-- ekstra
SELECT postnr, poststed, COUNT(and_eier_nr) AS "Antall andelseiere"
FROM poststed
    LEFT JOIN bygning USING (postnr)
    LEFT JOIN leilighet USING (bygn_id)
    LEFT JOIN andelseier USING (and_eier_nr)
GROUP BY postnr
ORDER BY COUNT(and_eier_nr) DESC;


