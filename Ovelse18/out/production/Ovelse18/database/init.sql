USE ovelse18;

DROP TABLE IF EXISTS eksemplar;
DROP TABLE IF EXISTS boktittel;

CREATE TABLE boktittel (
    isbn VARCHAR(30),
    forfatter VARCHAR(30),
    tittel VARCHAR(30),
    PRIMARY KEY (isbn)
);

CREATE TABLE eksemplar (
    eks_id INT AUTO_INCREMENT,
    isbn VARCHAR(30),
    laant_av VARCHAR(30),
    eks_nr INT,
    PRIMARY KEY (eks_id),
    FOREIGN KEY (isbn) REFERENCES boktittel (isbn)
)