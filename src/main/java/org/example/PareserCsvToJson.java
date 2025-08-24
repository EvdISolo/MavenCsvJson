package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PareserCsvToJson {
    static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        FileReader fileReader = null;
        try {

            fileReader = new FileReader(fileName);

        } catch (IOException e) {
            System.out.println(e);
            return null;
        } finally {

            try {

                if (fileReader != null) {

                    fileReader.close();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        CSVReader csvReader = new CSVReader(fileReader);
        ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Employee.class);
        strategy.setColumnMapping(columnMapping);

        CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                .withMappingStrategy(strategy)
                .build();
        return csv.parse();
    }
}
