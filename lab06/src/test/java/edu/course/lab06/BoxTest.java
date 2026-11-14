package edu.course.lab06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoxTest {

    @Test
    void boxOfStringStoresAndReturnsString() {
        Box<String> box = new Box<>("hello");
        assertEquals("hello", box.get());
        box.set("world");
        assertEquals("world", box.get());
    }

    @Test
    void boxOfIntegerStoresAndReturnsInteger() {
        Box<Integer> box = new Box<>(42);
        assertEquals(42, box.get());
        box.set(7);
        assertEquals(7, box.get());
    }

    @Test
    void typeParameterPreventsWrongAssignment() {
        Box<String> box = new Box<>("text");
        assertEquals("text", box.get());
    }
}