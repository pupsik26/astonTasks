package ru.aston.hometask2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileDataServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void parseLineToStudent_shouldParseCorrectly() {
        AppConfig config = new AppConfig(() -> "dummy", () -> null);
        FileDataService service = new FileDataService(config);

        String line = "Иван Иванов|Clean Code,Robert Martin,2008,450;Effective Java,Joshua Bloch,2018,400";
        Student student = service.parseLineToStudent(line);

        assertEquals("Иван Иванов", student.getName());
        assertEquals(2, student.getBooks().size());

        Book first = student.getBooks().get(0);
        assertEquals("Clean Code", first.getTitle());
        assertEquals("Robert Martin", first.getAuthor());
        assertEquals(2008, first.getYear());
        assertEquals(450, first.getPages());
    }

    @Test
    void generateSampleFile_shouldCreateFileWithData() {
        Path testFile = tempDir.resolve("test.txt");
        AppConfig config = new AppConfig(() -> testFile.toString(), () -> null);
        FileDataService service = new FileDataService(config);

        service.generateSampleFile();

        assertTrue(Files.exists(testFile));
        assertDoesNotThrow(() -> {
            String content = Files.readString(testFile);
            assertTrue(content.contains("Иван Иванов"));
            assertTrue(content.contains("Елена Волкова"));
        });
    }

    @Test
    void processStudentsFromFile_shouldReadAndProcessLazily() {
        Path testFile = tempDir.resolve("students.txt");
        String data = """
                Иван Иванов|Book A,Author A,2010,300;Book B,Author B,2015,400
                Петр Петров|Book C,Author C,2005,500
                """;
        assertDoesNotThrow(() -> Files.writeString(testFile, data));

        AppConfig config = new AppConfig(() -> testFile.toString(), () -> null);
        FileDataService service = new FileDataService(config);

        List<Student> loadedStudents = new ArrayList<>();
        service.processStudentsFromFile(stream ->
                stream.forEach(loadedStudents::add)
        );

        assertEquals(2, loadedStudents.size());
        assertEquals("Иван Иванов", loadedStudents.get(0).getName());
        assertEquals("Петр Петров", loadedStudents.get(1).getName());
        assertEquals(2, loadedStudents.get(0).getBooks().size());
        assertEquals(1, loadedStudents.get(1).getBooks().size());
    }

    @Test
    void processStudentsFromFile_shouldThrowOnMissingFile() {
        AppConfig config = new AppConfig(() -> "/nonexistent/path.txt", () -> null);
        FileDataService service = new FileDataService(config);

        assertThrows(RuntimeException.class, () ->
                service.processStudentsFromFile(stream -> {})
        );
    }
}