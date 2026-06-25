package ru.aston.hometask2;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class StudentStreamProcessor {

    public void processStudents(Stream<Student> studentStream) {
        studentStream
                .peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("\n✅ Год выпуска найденной книги: " + year),
                        () -> System.out.println("\n❌ Такая книга отсутствует")
                );
    }
}