USE ovelse7;

-- 1. Lag en liste over alle bedriftene. Navn, telefon og epost til bedriften skal skrives ut.
SELECT navn, tlf, email FROM bedrift;

-- 2. Lag en liste over alle oppdragene. Om hvert oppdrag skal du skrive ut oppdragets nummer samt navn og telefonnummer til bedriften som tilbyr oppdraget.
SELECT oppdragsNr, navn, tlf,
    CASE WHEN idHistorikk IS NULL THEN "Ikke påbegynt" ELSE "Påbegynt/ferdig" END AS "Status"
    FROM bedrift INNER JOIN oppdrag USING (orgNr) LEFT JOIN historikk USING (oppdragsNr) WHERE oppdragsNr IS NOT NULL
    ORDER BY oppdragsNr;

-- 3. Lag en liste over kandidater og kvalifikasjoner. Kandidatnavn og kvalifikasjonsbeskrivelse skal med i utskriften i tillegg til løpenumrene som identifiserer kandidat og kvalifikasjon
SELECT idKandidat, CONCAT(fornavn, " ", etternavn) AS "Navn", idKvalifikasjon, beskrivelse FROM kvalifikasjon INNER JOIN kandidatkvalifikasjon USING (idKvalifikasjon) INNER JOIN kandidat USING (idKandidat);

-- 4. Som oppgave 3), men få med de kandidatene som ikke er registrert med kvalifikasjoner.
SELECT idKandidat, CONCAT(fornavn, " ", etternavn) AS "Navn", idKvalifikasjon, beskrivelse FROM kvalifikasjon INNER JOIN kandidatkvalifikasjon USING (idKvalifikasjon) RIGHT JOIN kandidat USING (idKandidat);

-- 5. Skriv ut jobbhistorikken til en bestemt vikar, gitt kandidatnr. Vikarnavn, sluttdato, oppdragsnr og bedriftsnavn skal med.
SELECT CONCAT(fornavn, " ", etternavn) AS "Navn", historikk.sluttDato, oppdragsNr, navn FROM kandidat INNER JOIN historikk USING(idKandidat) INNER JOIN oppdrag USING (oppdragsNr) INNER JOIN bedrift USING (orgNr) WHERE idKandidat = 1;