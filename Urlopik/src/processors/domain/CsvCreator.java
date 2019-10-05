import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map.Entry;

public class CsvCreator implements ISerializer<File> {

    @Override
    public File serialize(IMap map, File file) {

        var yearMap = map.getMap();
        try {
            var csvWriter = new CSVWriter(new FileWriter(file));

            String[] header = { "Subject", "Start Date", "All Day Event", "Start Time", "End Time", "Location", "Description"};
            csvWriter.writeNext(header);

            for (Entry<LocalDate, OffMode> entry :yearMap.entrySet()) {
                if (entry.getValue() == OffMode.OFF)
                {
                    String[] csvEntry = {"urlop", parseGoogleCalendarDate(entry.getKey()),"TRUE", "","","",""};
                    csvWriter.writeNext(csvEntry);
                }
            }

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return file;
        }
    }

    public String parseGoogleCalendarDate(LocalDate date){
        var monthValue = date.getMonthValue();
        var dayValue = date.getDayOfMonth();
        var yearValue = date.getYear();

        var month = monthValue>=10 ? String.valueOf(monthValue) : "0"+String.valueOf(monthValue);
        var day = dayValue>=10 ? String.valueOf(dayValue) : "0"+String.valueOf(dayValue);
        var year = String.valueOf(yearValue);

        return month+"/"+day+"/"+year;
    }
}
