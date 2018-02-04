import dyrehage.Rovdyrfabrikk;
import dyrehage.SkandinaviskeRovdyr;

public class klient {

    public static void main(String[] args) {
        Rovdyrfabrikk test = new Rovdyrfabrikk();
        SkandinaviskeRovdyr binne = test.nyBinne(20180131, "Adresseveien 1", "Binne nr 1", 2000);
        SkandinaviskeRovdyr hannbjørn = test.nyHannbjørn(20180131, "Adresseveien 2", "Hannbjørn nr 1", 2002);
        SkandinaviskeRovdyr tispe = test.nyUlvetispe(20180131, "Adresseveien 3", "Tispe nr 1", 2010);
        SkandinaviskeRovdyr hannulv = test.nyUlvehann(20180131, "Adresseveien 4", "Hannulv nr 1", 2011);

        binne.leggTilNyttKull();
        hannbjørn.leggTilNyttKull();
        tispe.leggTilNyttKull();
        hannulv.leggTilNyttKull();

        System.out.println(
                binne.skrivUtInfo() +
                        "\n"+hannbjørn.skrivUtInfo() +
                        "\n"+tispe.skrivUtInfo() +
                        "\n"+hannulv.skrivUtInfo()
        );

    }
}
