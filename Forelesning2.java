import java.util.Arrays;

class Forelesning2 {

    public static void main(String[] args) {

        int[] num1 = new int[4]; //Uansett hvor den deklareres får en tallarray startverdier lik 0

        //kopiering
        num1[2] = 5;
        num1[3] = 7;

        System.out.println("Num1 " + Arrays.toString(num1));

        int[] num2 = new int[num1.length];
        //Alt 1
        for(int i = 0; i < num2.length; i++) {
            num2[i] = num1[i];
        }
        System.out.println("Num2 " + Arrays.toString(num2));
        //Alt 2
        int[] num3 = new int[6];
        System.arraycopy(num1, 0, num3, 2, 3); // fraTab, fraIndeks, tilTab, tilIndeks, antall

        System.out.println("Num3 " + Arrays.toString(num3) + "\n");


        String[] food = new String[4];
        food[1] = "meat";
        food[3] = "cheese";

        System.out.println("Food " + Arrays.toString(food));

        //kopiering
        String[] food2 = new String[food.length];
        //Alt1
        for(int i = 0; i < food2.length; i++) {
            //food2[i] = food[i]; //->  her blir kun referansene kopiert altså grunn kopiering
            if(food[i] != null) food2[i] = new String(food[i]); //->  her blir ett helt nytt objekt laget for food2, altså dyp kopiering
        }
        System.out.println("Food2 " + Arrays.toString(food2));


        //utvide tabeller
        // 1. Opprett tabell større enn den gamle (num)
        int[] num4 = new int[6];
        // 2. Kopier over data fra gammeø til ny tabell
        System.arraycopy(num1, 0, num4, 0, num1.length);
        System.out.println("Num4 " + Arrays.toString(num4));
        // 3. Sett referansen num til å peke til den nye (og større) tabellen
        num1 = num4;
        System.out.println("Num1 " + Arrays.toString(num1));


    }

}
