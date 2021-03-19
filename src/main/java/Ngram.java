import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Ngram {
    //Used to set a 200 word limit as per exercise specification
    private static final int WORD_LIMIT = 200;
    private static final int LINE_LIMIT = 12;

    /**
     * selects random string from String List
     * @param strings
     * @return random string from String List
     */

    public String randomString(List<String> strings) {
        Random random = new Random();
        int rInt = random.nextInt(strings.size());
        return strings.get(rInt);
    }


    /**
     * generates 200 words of text via storyBuilder using keys and references from ngramMap
     * @param ngramMap provides keys and references for storyBuilder
     * @return returns a list of strings to be written to file
     */

    public String storyWriter(Map<String, ArrayList<String>> ngramMap) {
        String randKey = randomString(new ArrayList<String>(ngramMap.keySet()));
        StringBuilder storyBuilder = new StringBuilder();
        int counter = 0;
        while (ngramMap.containsKey(randKey) && counter < WORD_LIMIT) {
            String s = randomString(ngramMap.get(randKey));
            storyBuilder.append(s).append(" ");
            randKey = randKey.split(" ")[1] + " " + s;
            counter++;
            if (counter % LINE_LIMIT == 0) {
                storyBuilder.append(System.getProperty("line.separator"));
            }
        }
        return storyBuilder.toString().trim();
    }
}
