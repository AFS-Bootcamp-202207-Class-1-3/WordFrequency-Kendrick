import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    private static final String REGEX = "\\s+";
    private static final String DELIMITER = "\n";

    public String getWordFrequency(String inputStr) {

        if (inputStr.split(REGEX).length == 1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] wordsArr = inputStr.split(REGEX);

                List<WordInformation> wordInformationList = new ArrayList<>();
                for (String word : wordsArr) {
                    WordInformation wordInformation = new WordInformation(word, 1);
                    wordInformationList.add(wordInformation);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordInformation>> map = getListMap(wordInformationList);

                wordInformationList.clear();
                for (Map.Entry<String, List<WordInformation>> entry : map.entrySet()) {
                    WordInformation wordInformation = new WordInformation(entry.getKey(), entry.getValue().size());
                    wordInformationList.add(wordInformation);
                }

                wordInformationList.sort((info1, info2) -> info2.getWordCount() - info1.getWordCount());

                StringJoiner joiner = new StringJoiner(DELIMITER);
                for (WordInformation w : wordInformationList) {
                    String s = w.getWord() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String, List<WordInformation>> getListMap(List<WordInformation> wordInformationList) {
        Map<String, List<WordInformation>> map = new HashMap<>();
        for (WordInformation wordInformation : wordInformationList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordInformation.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInformation);
                map.put(wordInformation.getWord(), arr);
            } else {
                map.get(wordInformation.getWord()).add(wordInformation);
            }
        }


        return map;
    }


}
