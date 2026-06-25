package ru.aston.hometask2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void equals_sameBooks_shouldBeEqual() {
        Book book1 = new Book("Clean Code", "Robert Martin", 2008, 450);
        Book book2 = new Book("Clean Code", "Robert Martin", 2008, 450);

        assertEquals(book1, book2);
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void equals_differentBooks_shouldNotBeEqual() {
        Book book1 = new Book("Clean Code", "Robert Martin", 2008, 450);
        Book book2 = new Book("Effective Java", "Joshua Bloch", 2018, 400);

        assertNotEquals(book1, book2);
    }

    @Test
    void toString_shouldContainAllFields() {
        Book book = new Book("Clean Code", "Robert Martin", 2008, 450);
        String result = book.toString();

        assertTrue(result.contains("Clean Code"));
        assertTrue(result.contains("Robert Martin"));
        assertTrue(result.contains("2008"));
        assertTrue(result.contains("450"));
    }

    @Test
    void getters_shouldReturnCorrectValues() {
        Book book = new Book("Title", "Author", 2020, 300);

        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals(2020, book.getYear());
        assertEquals(300, book.getPages());
    }
}