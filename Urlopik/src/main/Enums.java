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
        SET(1),
        CANCEL(2),
        EXIT(3);

        private int value;

        OperationMode (int value){
            this.value = value;
        }

    }

}
