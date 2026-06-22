package ru.aston.hometask2;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StudentStreamProcessorTest {

    @Test
    void processStudents_shouldFindYearOfFirstMatchingBook() {
        Student s1 = new Student("Иван", List.of(
                new Book("Old Book", "A", 1999, 100),
                new Book("Big Book", "B", 2010, 1000),
                new Book("Small New", "C", 2015, 200)
        ));
        Student s2 = new Student("Пётр", List.of(
                new Book("Another", "D", 2020, 300)
        ));

        StudentStreamProcessor processor = new StudentStreamProcessor();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            processor.processStudents(Stream.of(s1, s2));
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("2015"), "Должен быть найден год 2015, получено: " + output);
    }

    @Test
    void processStudents_shouldPrintNotFoundWhenNoBooksAfter2000() {
        Student s1 = new Student("Иван", List.of(
                new Book("Old1", "A", 1990, 100),
                new Book("Old2", "B", 1995, 200)
        ));

        StudentStreamProcessor processor = new StudentStreamProcessor();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            processor.processStudents(Stream.of(s1));
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("Такая книга отсутствует"),
                "Должно быть сообщение об отсутствии, получено: " + output);
    }

    @Test
    void processStudents_shouldHandleEmptyStream() {
        StudentStreamProcessor processor = new StudentStreamProcessor();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            processor.processStudents(Stream.empty());
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("Такая книга отсутствует"));
    }

    @Test
    void processStudents_shouldDeduplicateBooks() {
        Book duplicate = new Book("Clean Code", "Martin", 2008, 450);
        Student s1 = new Student("Иван", List.of(duplicate));
        Student s2 = new Student("Пётр", List.of(duplicate));
        Student s3 = new Student("Анна", List.of(
                new Book("Other", "X", 2010, 100)
        ));

        StudentStreamProcessor processor = new StudentStreamProcessor();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            processor.processStudents(Stream.of(s1, s2, s3));
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("2010"), "Должен быть год 2010, получено: " + output);
    }
}