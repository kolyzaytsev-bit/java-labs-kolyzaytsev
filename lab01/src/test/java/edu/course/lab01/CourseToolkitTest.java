package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }

    @Test
    void returnsTrueForZero() {
        boolean result = CourseToolkit.isEven(0);

        assertTrue(result);
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        assertTrue(CourseToolkit.isEven(-8));
    }

    @Test
    void returnsFalseForNumberBelowTwo() {
        assertFalse(CourseToolkit.isPrime(1));
        assertFalse(CourseToolkit.isPrime(0));
        assertFalse(CourseToolkit.isPrime(-7));
    }

    @Test
    void returnsTrueForSmallPrimes() {
        assertTrue(CourseToolkit.isPrime(2));
        assertTrue(CourseToolkit.isPrime(3));
        assertTrue(CourseToolkit.isPrime(97));
    }

    @Test
    void returnsFalseForCompositeNumbers() {
        assertFalse(CourseToolkit.isPrime(9));
        assertFalse(CourseToolkit.isPrime(100));
    }

    @Test
    void handlesSquareOfPrime() {
        assertFalse(CourseToolkit.isPrime(49));
        assertFalse(CourseToolkit.isPrime(121));
    }

    @Test
    void detectsPalindrome() {
        assertTrue(CourseToolkit.isPalindrome("level"));
        assertTrue(CourseToolkit.isPalindrome(""));
    }

    @Test
    void rejectsNonPalindrome() {
        assertFalse(CourseToolkit.isPalindrome("java"));
        assertFalse(CourseToolkit.isPalindrome("Abba"));
    }

    @Test
    void rejectsNullText() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }

    @Test
    void returnsFractionalAverage() {
        assertEquals(2.5, CourseToolkit.average(new int[] {1, 2, 3, 4}), 1e-9);
    }

    @Test
    void handlesNegativeValues() {
        assertEquals(-2.0, CourseToolkit.average(new int[] {-1, -2, -3}), 1e-9);
    }

    @Test
    void keepsInputArrayUnchanged() {
        int[] values = {1, 2, 3};
        CourseToolkit.average(values);
        assertArrayEquals(new int[] {1, 2, 3}, values);
    }

    @Test
    void rejectsNullAndEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[0]));
    }
}
