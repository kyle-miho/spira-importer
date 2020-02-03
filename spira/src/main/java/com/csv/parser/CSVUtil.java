package com.csv.parser;

import com.csv.CSV;
import com.csv.CoreCSV;
import com.spira.testcase.TestCase;
import com.spira.testcase.folder.TestCaseFolder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static TestCaseFolder parseCSV(CSV csv) throws Exception {
        File file = new File(csv.getFilePath());

        List<CSVRecord> csvRecords = _getCSVRecords(file);

        for (CSVRecord csvRecord : csvRecords) {
            List<String> row = new ArrayList<>();

            for (String cell : csvRecord) {
                row.add(cell);
            }

            csv.addRow(row);
        }

        return csv.getRootFolder();
    }

    private static List<CSVRecord> _getCSVRecords(File file) throws IOException {
        CSVParser csvParser =
                CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.EXCEL);

        return csvParser.getRecords();
    }
}
