//package java;

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

