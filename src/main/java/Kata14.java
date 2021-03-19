import java.io.IOException;

public class Kata14 {
    public static void main(String[] args) throws IOException {
        BookProcessor processor = new BookProcessor();
        Ngram ngram = new Ngram();

        processor.bookParser("src/main/resources/Frankenstein.txt");     //enter file to be read directory here.
        processor.ngramStore();
        processor.bookWriter("src/main/resources/Output.txt", ngram.storyWriter(processor.getNgramMap()));       //modify output file name here
    }
}
