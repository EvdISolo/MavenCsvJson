package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.Employee;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class Main {

    public static void main(String[] args) {
        String[] employee = "1,John,Smith,USA,25".split(",");
        String[] employee2 = "2, Ivan,Petrov,RU,23".split(",");
        List<String[]> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee2);






// Создание файла
        try
                (CSVWriter writer = new CSVWriter(new FileWriter("data.csv"))) {

            employees.forEach(writer::writeNext);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        String json = listToJson(list);
        System.out.println(json);
        try {
            writeString(json, "employees.json");
            System.out.println("JSON записан в файл employees.json");
        } catch (
                IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public static List parseCSV(String[] columnMapping, String nameFile) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(nameFile);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        CSVReader csvReader = new CSVReader(fileReader);
        com.opencsv.bean.ColumnPositionMappingStrategy<Employee> strategyResult = new ColumnPositionMappingStrategy<>();
        strategyResult.setType(Employee.class);
        strategyResult.setColumnMapping(columnMapping);
        CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(csvReader).withMappingStrategy(strategyResult).build();
        List<Employee> list = csvToBean.parse();
        return list;
    }

    public static String listToJson(List<Employee> list) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        String json = gson.toJson(list, listType);
        return json;
    }

    public static void writeString(String json, String filePath) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
            writer.write(json);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}


