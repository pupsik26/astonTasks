package ru.aston.hometask2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        String fileName = "students_data.txt";

        // 1. Создаем тестовый файл
        createSampleFile(fileName);

        try {
            // 2. Читаем файл и заполняем List<Student>
            List<Student> students = Files.lines(Paths.get(fileName))
                    .map(line -> {
                        // Разделяем строку на имя студента и строку с книгами
                        String[] parts = line.split("\\|");
                        String studentName = parts[0];

                        // Разделяем книги по точке с запятой, а затем каждую книгу по запятой
                        List<Book> books = Arrays.stream(parts[1].split(";"))
                                .map(bookData -> {
                                    String[] book = bookData.split(",");
                                    return new Book(
                                            book[0].trim(),
                                            book[1].trim(),
                                            Integer.parseInt(book[2]),
                                            Integer.parseInt(book[3])
                                    );
                                })
                                .toList();

                        return new Student(studentName, books);
                    })
                    .toList();

            System.out.println("--- Успешно загружено студентов: " + students.size() + " ---\n");

            students.stream()
                    // 1. Вывести каждого студента (использует переопределенный toString)
                    .peek(System.out::println)
                    // 2. Получить для каждого студента список книг (Stream<List<Book>>)
                    .map(Student::getBooks)
                    // 3. Получить книги (превращаем Stream<List<Book>> в Stream<Book>)
                    .flatMap(List::stream)
                    // 4. Отсортировать книги по количеству страниц
                    .sorted(Comparator.comparingInt(Book::getPages))
                    // 5. Оставить только уникальные книги (требует equals/hashCode в Book)
                    .distinct()
                    // 6. Отфильтровать книги, выпущенные после 2000 года
                    .filter(book -> book.getYear() > 2000)
                    // 7. Ограничить стрим на 3 элементах
                    .limit(3)
                    // 8. Получить из книг годы выпуска (Stream<Integer>)
                    .map(Book::getYear)
                    // 9. Метод короткого замыкания: вернуть Optional от года
                    .findFirst()
                    // 10. Вывести год или сообщение об отсутствии
                    .ifPresentOrElse(
                            year -> System.out.println("\n✅ Найденный год выпуска книги: " + year),
                            () -> System.out.println("\n❌ Такая книга отсутствует")
                    );

        } catch (Exception e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    // Вспомогательный метод для создания файла с данными (минимум 5 книг у каждого)
    private static void createSampleFile(String fileName) {
        String data = """
                Иван Иванов|Java Basics,John Doe,1999,400;Clean Code,Robert Martin,2008,450;Effective Java,Joshua Bloch,2018,400;Head First Java,Kathy Sierra,2005,600;Thinking in Java,Bruce Eckel,2006,1100
                Петр Петров|Spring in Action,Craig Walls,2015,500;Clean Code,Robert Martin,2008,450;Modern Java in Action,Raoul-Gabriel Urma,2019,530;Java Concurrency in Practice,Brian Goetz,2006,380;Refactoring,Martin Fowler,2018,400
                Анна Смирнова|Introduction to Algorithms,Thomas Cormen,2009,1300;Grokking Algorithms,Aditya Bhargava,2016,250;Clean Code,Robert Martin,2008,450;Design Patterns,Erich Gamma,1994,400;Algorithms,Robert Sedgewick,2011,950
                Дмитрий Козлов|Database Internals,Alex Petrov,2019,500;Designing Data-Intensive Applications,Martin Kleppmann,2017,600;The Phoenix Project,Gene Kim,2013,350;Site Reliability Engineering,Betsy Beyer,2016,550;Effective Java,Joshua Bloch,2018,400
                Елена Волкова|Fluent Python,Luciano Ramalho,2015,800;Python Crash Course,Eric Matthes,2019,500;Automate the Boring Stuff,Al Sweigart,2015,500;High Performance Python,Micha Gorelick,2020,400;Clean Code,Robert Martin,2008,450
                """;
        try {
            Files.writeString(Paths.get(fileName), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
