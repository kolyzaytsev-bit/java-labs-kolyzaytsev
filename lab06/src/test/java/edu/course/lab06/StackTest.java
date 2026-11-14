package edu.course.lab06;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StackTest {

    @Test
    void newStackIsEmpty() {
        Stack<String> stack = new Stack<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void pushIncreasesSize() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
    }

    @Test
    void peekReturnsTopWithoutRemoving() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        assertEquals("b", stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void popReturnsInLifoOrder() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void popOnEmptyThrows() {
        Stack<String> stack = new Stack<>();
        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void peekOnEmptyThrows() {
        Stack<String> stack = new Stack<>();
        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @Test
    void nullIsRejected() {
        Stack<String> stack = new Stack<>();
        assertThrows(NullPointerException.class, () -> stack.push(null));
    }

    @Test
    void worksWithTwoDifferentTypes() {
        Stack<String> strings = new Stack<>();
        Stack<Integer> ints = new Stack<>();
        strings.push("x");
        ints.push(10);
        assertEquals("x", strings.pop()); // без приведения типов
        assertEquals(10, ints.pop());
    }
}