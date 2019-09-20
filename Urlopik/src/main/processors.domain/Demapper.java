import java.io.*;
import java.time.LocalDate;
import java.util.Map;

public class Demapper implements ISerializer<File> {

    private Map<LocalDate,OffMode> yearMap;
    private File file;

    public Demapper(Map<LocalDate,OffMode> yearMap, File file){
        this.yearMap = yearMap;
        this.file = file;
    }

    @Override
    public File serialize(IMap map, File file) {
        Map<LocalDate,OffMode> yearMap = map.getMap();
        decomposeYearMap(yearMap, file);
        return file;
    }

    private void decomposeYearMap(Map<LocalDate, OffMode> yearMap, File file){

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

        String outputString = sb.toString();

        try {
            saveToFile(file, outputString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void saveToFile(File file, String outputString) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"))) {
            writer.write(outputString);
        }
    }
}

