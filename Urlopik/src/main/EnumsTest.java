package main;

public class EnumsTest {

    public static void main(String[] args) {

        System.out.println(czyLadny(Enums.Kolor.CZERWONY));
        System.out.println(czyLadny(Enums.Kolor.ZIELONY));
        System.out.println();


        for (Enums.Kolor kolor : Enums.Kolor.values()) {
            System.out.println(czyLadny(kolor));
        }

    }

        public static String czyLadny(Enums.Kolor kolor) {
        String czyLadny = (kolor.ladny) ? "ladny" : "brzydki";

        return "Kolor "+kolor.toString().toLowerCase()+" jest "+czyLadny;
    }
}
