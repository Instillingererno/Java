USE ovelse5;

SELECT
  borettslag.navn AS 'Navn',
  COUNT(DISTINCT b.idBoenhet) AS Antall_boenheter,
  COUNT(l.leilighetsNr) AS Antall_leiligheter,
  MAX(l.etasje) AS Høyeste_etasje,
  COUNT(m.idMedlemmer) AS Antall_leietakere
FROM borettslag INNER JOIN
  boenhet b ON borettslag.idBorettslag = b.idBorettslag INNER JOIN
  leilighet l ON b.idBoenhet = l.idBoenhet LEFT JOIN
  medlemmer m ON l.leilighetsNr = m.leilighetsNr;
