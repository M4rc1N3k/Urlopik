package main;

public class Enums {

    public enum Kolor {

        CZERWONY(false),
        ZIELONY(true),
        NIEBIESKI(true);

        boolean ladny;

        private Kolor(boolean czyLadny) {
            ladny = czyLadny;
        }
    }

    public enum OffMode {
        //TODO: assign values to enums
        WORKING,
        OFF,
        HOLIDAY;

    }

    public enum OperationMode {
        SET,
        CANCEL,
        EXIT,
        DEFAULT;



        public static OperationMode fromInt (int x){
            switch (x){
                case 1:
                    return SET;
                case 2:
                    return CANCEL;
                case 3:
                    return EXIT;
            }
            return DEFAULT;
        }

    }

}
