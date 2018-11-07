package main.resources;

public class Enums {

    public enum OffMode {

        WORKING,
        OFF,
        HOLIDAY;


        public static OffMode fromInt (int x){
            switch (x){
                case 0:
                    return WORKING;
                case 1:
                    return OFF;
                case 2:
                    return HOLIDAY;
            }
            return null;
        }
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
