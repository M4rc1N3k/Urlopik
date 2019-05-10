import java.time.LocalDate;
import java.util.Map;

public class Demapper implements ISerializer {

    private String outputString;

    public Demapper(Map<LocalDate,OffMode> yearMap){
        this.outputString = serialize(yearMap);
    }

    @Override
    public String serialize(Map<LocalDate, OffMode> yearMap) {
        return decomposeYearMap(yearMap);
    }

    private String decomposeYearMap(Map<LocalDate, OffMode> yearMap){

        int iterationNumber, symbolicValueToBeSaved;
        StringBuilder sb = new StringBuilder();

        for (LocalDate entry : yearMap.keySet()) {
            iterationNumber = entry.getDayOfYear();

            switch (yearMap.get(entry)){
                case WORKING:
                    symbolicValueToBeSaved = 0;
                    break;
                case OFF:
                    symbolicValueToBeSaved = 1;
                    break;
                case HOLIDAY:
                    symbolicValueToBeSaved = 2;
                    break;
                default:
                    symbolicValueToBeSaved = 0;
            }

            sb.append(symbolicValueToBeSaved);

            if (iterationNumber % 7 == 0){
                sb.append("\r\n");
            }
        }

        outputString = sb.toString();

        return outputString;
    }

}

