package ru.aston.hometask2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FileDataService {

    private final String filePath;

    public FileDataService(AppConfig config) {
        this.filePath = config.getFilePath();
    }

    public void generateSampleFile() {
        String data = """
                Иван Иванов|Java Basics,John Doe,1999,400;Clean Code,Robert Martin,2008,450;Effective Java,Joshua Bloch,2018,400;Head First Java,Kathy Sierra,2005,600;Thinking in Java,Bruce Eckel,2006,1100
                Петр Петров|Spring in Action,Craig Walls,2015,500;Clean Code,Robert Martin,2008,450;Modern Java in Action,Raoul-Gabriel Urma,2019,530;Java Concurrency in Practice,Brian Goetz,2006,380;Refactoring,Martin Fowler,2018,400
                Анна Смирнова|Introduction to Algorithms,Thomas Cormen,2009,1300;Grokking Algorithms,Aditya Bhargava,2016,250;Clean Code,Robert Martin,2008,450;Design Patterns,Erich Gamma,1994,400;Algorithms,Robert Sedgewick,2011,950
                Дмитрий Козлов|Database Internals,Alex Petrov,2019,500;Designing Data-Intensive Applications,Martin Kleppmann,2017,600;The Phoenix Project,Gene Kim,2013,350;Site Reliability Engineering,Betsy Beyer,2016,550;Effective Java,Joshua Bloch,2018,400
                Елена Волкова|Fluent Python,Luciano Ramalho,2015,800;Python Crash Course,Eric Matthes,2019,500;Automate the Boring Stuff,Al Sweigart,2015,500;High Performance Python,Micha Gorelick,2020,400;Clean Code,Robert Martin,2008,450
                """;
        try {
            Files.writeString(Paths.get(filePath), data);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка создания файла: " + e.getMessage(), e);
        }
    }

    public void processStudentsFromFile(Consumer<Stream<Student>> processor) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            Stream<Student> studentStream = lines.map(this::parseLineToStudent);
            processor.accept(studentStream);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения файла: " + e.getMessage(), e);
        }
    }

    Student parseLineToStudent(String line) {
        String[] parts = line.split("\\|");
        String studentName = parts[0];

        List<Book> books = Arrays.stream(parts[1].split(";"))
                .map(bookData -> {
                    String[] b = bookData.split(",");
                    return new Book(
                            b[0].trim(),
                            b[1].trim(),
                            Integer.parseInt(b[2]),
                            Integer.parseInt(b[3])
                    );
                })
                .toList();

        return new Student(studentName, books);
    }
}