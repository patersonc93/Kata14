import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookProcessor {
    private Map<String, ArrayList<String>> ngramMap;
    private List<String> bagOfWords;

    public void setNgramMap(Map<String, ArrayList<String>> ngramMap) {
        this.ngramMap = ngramMap;
    }

    public void setBagOfWords(List<String> bagOfWords) {
        this.bagOfWords = bagOfWords;
    }

    public Map<String, ArrayList<String>> getNgramMap() {
        return ngramMap;
    }

    public List<String> getBagOfWords() {
        return bagOfWords;
    }

    /**
     * reads filePath and splits each word into individual string
     * @param filePath path to input file representing book
     * @throws FileNotFoundException when path is invalid
     */

    public void bookParser(String filePath) throws FileNotFoundException {
        bagOfWords = new ArrayList<>();
        File myBook = new File(filePath);
        Scanner bookScanner = new Scanner(myBook);
        Pattern p = Pattern.compile("[^a-zA-Z0-9]\\p{Punct}");
        Matcher m = p.matcher("");

        while (bookScanner.hasNextLine()) {
            String row = bookScanner.nextLine().trim();
            m = m.reset(row);
            row = m.replaceAll(" ");
            String[] tempWords = row.split("\\s+");
            for (String word : tempWords) {
                if (!word.isEmpty()) {
                    bagOfWords.add(word);
                }
            }
        }
    }

    /**
     * creates a Map object from the list of strings generated in bookParser method
     */

    public void ngramStore() {
        ngramMap = new HashMap<String, ArrayList<String>>();
        int set = bagOfWords.size() - 3;
        for (int i = 0; i <= set; i++) {
            String key = bagOfWords.get(i) + " " + bagOfWords.get(i + 1);
            String s = bagOfWords.get(i + 2);
            if (ngramMap.containsKey(key)) {
                ngramMap.get(key).add(s);
            } else {
                ArrayList<String> ngramList = new ArrayList<String>();
                ngramList.add(s);
                ngramMap.put(key, ngramList);
            }
        }
    }

    /**
     * writes file to user specified location
     * @param fileName path to output file
     * @param value content being written to file
     * @throws IOException when path is invalid
     */

    public void bookWriter(String fileName, String value) throws IOException {
        String s = value;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(s);
        writer.close();
    }
}
