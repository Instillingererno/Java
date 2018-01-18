USE ovelse5;

SELECT leilighet.* FROM leilighet
  LEFT OUTER JOIN medlemmer m ON leilighet.leilighetsNr = m.leilighetsNr
  WHERE m.leilighetsNr IS NULL;

SELECT DISTINCT leilighet.* FROM leilighet
  WHERE leilighet.leilighetsNr NOT IN (
    SELECT leilighetsNr FROM medlemmer WHERE leilighetsNr IS NOT NULL
  )