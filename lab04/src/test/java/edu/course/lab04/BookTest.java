package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {

    @Test
    void equalByIsbnFindsValueInHashMap() {
        Book stored = new Book("978-3-16-148410-0", "Effective Java");
        Book lookup = new Book("978-3-16-148410-0", "Effective Java (2nd ed.)");

        Map<Book, String> shelf = new HashMap<>();
        shelf.put(stored, "top shelf");

        assertEquals("top shelf", shelf.get(lookup));
        assertEquals(stored.hashCode(), lookup.hashCode());
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book a = new Book("111", "A");
        Book b = new Book("222", "A");
        assertNotEquals(a, b);
    }

    @Test
    void equalsIsReflexiveSymmetricAndNullSafe() {
        Book a = new Book("111", "A");
        Book b = new Book("111", "B");
        Book c = new Book("111", "A");

        assertTrue(a.equals(a));
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.equals(c));

        assertFalse(a.equals(null));
        assertFalse(a.equals("111"));
    }
}