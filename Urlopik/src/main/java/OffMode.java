//package java;

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
