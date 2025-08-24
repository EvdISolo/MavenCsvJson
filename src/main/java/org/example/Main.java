package org.example;

import com.opencsv.*;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.example.PareserCsvToJson.parseCSV;


public class Main {
    public static void main(String[] args) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String[] employee = "1,John,Smith,USA,25".split(",");
        String[] employee2 = "2, Ivan,Petrov,RU,23".split(",");
        List<String[]> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee2);


        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);


// Создание файла
        try
                (CSVWriter writer = new CSVWriter(new FileWriter("data.csv"))) {

            employees.forEach(writer::writeNext);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // parsing deserialization

//        List<Employee> data = null;
//        try (CSVReader csvReader = new CSVReader(new FileReader("data.csv"))) {
//            ColumnPositionMappingStrategy<Employee> strategy
//                    = new ColumnPositionMappingStrategy<>();
//            strategy.setType(Employee.class);
//            strategy.setColumnMapping("id", "firstName", "lastName", "country", "age");
//            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
//                    .withMappingStrategy(strategy)
//                    .build();
//            data = csv.parse();
//            data.forEach(System.out::println);
//        } catch (IOException ex) {
//            ex.printStackTrace();

//        }


            // serialization                        Cериализация

//        List<Employee> staff = new ArrayList<>();
//        staff.add(new Employee(1, "Nikita", "Petrov", "RU", 23));
//        staff.add(new Employee(2, "Ivan", "Ivanov", "RU", 25));
//       ColumnPositionMappingStrategy<Employee> strategy=
//               new ColumnPositionMappingStrategy<>();
//       strategy.setType(Employee.class);
//       strategy.setColumnMapping("id", "firstName", "lastName", "country", "age");
//       try(Writer writer = new FileWriter("staff_2.csv")){
//           StatefulBeanToCsv<Employee> sbc=
//                   new StatefulBeanToCsvBuilder<Employee>(writer)
//                           .withMappingStrategy(strategy)
//                           .build();
//           sbc.write(staff);
//       }
//       catch (IOException | CsvRequiredFieldEmptyException|CsvDataTypeMismatchException ex){
//           ex.printStackTrace();
//       }


//custom separator
//        CSVParser parser = new CSVParserBuilder()
//                .withSeparator('|')
//                .build();
//
//       try (CSVReader reader = new CSVReaderBuilder(new FileReader("data.csv"))
//           .build()) {
//           String[] nextLine;
//           while ((nextLine = reader.readNext()) != null) {
//               System.out.println(Arrays.toString(nextLine));
//           }
//       } catch (IOException | CsvValidationException ex) {
//           ex.printStackTrace();
//       }


            // Чтение файла
//            try (CSVReader reader = new CSVReader(new FileReader("data.csv"))) {
//
//                String[] nextLine;
//                while ((nextLine = reader.readNext()) != null) {
//                    System.out.println(Arrays.toString(nextLine));
//                }
//            } catch (IOException | CsvValidationException ex) {
//                ex.printStackTrace();
//            }

        }

    }














