import java.io.Serializable;

public class Arrangement implements Serializable {
    String navn;
    Idrettsgren[] idrettsgrener;

    public Arrangement(String navn, int idrettsgrener) {
        this.navn = navn;
        this.idrettsgrener = new Idrettsgren[idrettsgrener];
    }

    public String toString() {
        String temp = navn;
        temp += "\nMaks idrettsgrener " + idrettsgrener.length;
        if(idrettsgrener != null) {
            for(Idrettsgren i : idrettsgrener) {
                temp += (i != null) ? "\n" + i.toString() : "";
            }
        } else {
            temp += "\nIngen idrettsgrener enda";
        }
        return temp;
    }

    public boolean regIdrett(Idrettsgren x) {
        for(Idrettsgren i : idrettsgrener) {
            if(i != null) {
                if(i.getNavn().equals(x.getNavn())) return false;
            }
        }
        for(int i = 0; i < idrettsgrener.length; i++) {
            if(idrettsgrener[i] == null) {
                idrettsgrener[i] = x;
                return true;
            }
        }
        return false;
    }
}
