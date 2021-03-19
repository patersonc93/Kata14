

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BookProcessorTest {
    private BookProcessor classUnderTest;


    @BeforeEach
    public void setup() {
        classUnderTest = new BookProcessor();       //makes new instance of class under test
    }

    @Test
    void givenAValidInputFile_whenBookParserIsCalled_thenBagOfWordsShouldReturnStringsFromBook() throws FileNotFoundException {
        classUnderTest.bookParser("src/test/resources/mockBook");
        var result = classUnderTest.getBagOfWords();
        assertEquals(Arrays.asList("This", "is", "a", "test", "file", "for", "testing"), result);
    }

    @Test
    void givenAnInvalidInputFile_whenBookParserIsCalled_thenAnExceptionIsThrown() {
        assertThrows(FileNotFoundException.class,() -> classUnderTest.bookParser("src/test/resources/fakeBook"));
    }

    @Test
    void givenAValidBagOfWords_whenNgramStoreIsCalled_thenShouldReturnNgramMap() {
        classUnderTest.setBagOfWords(Arrays.asList("One", "Two", "Three", "Four", "One", "Two", "Five"));
        classUnderTest.ngramStore();
        var result = classUnderTest.getNgramMap();
        assertEquals(Arrays.asList("Three", "Five"), result.get("One Two"));
        assertEquals(Arrays.asList("One"), result.get("Three Four"));
    }
}
