import java.util.*;
import java.util.stream.Collectors;

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

                new ArrayList<>(map.entrySet()).stream().forEach(entry->{wordInformationList.add(new WordInformation(entry.getKey(),entry.getValue().size()));});

                wordInformationList.sort((info1, info2) -> info2.getWordCount() - info1.getWordCount());

                StringJoiner joiner = new StringJoiner(DELIMITER);

                wordInformationList.stream().forEach(info -> {
                    joiner.add(info.getWord() + " " + info.getWordCount());
                });
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String, List<WordInformation>> getListMap(List<WordInformation> wordInformationList) {
        return wordInformationList.stream().collect(Collectors.groupingBy(info -> info.getWord()));
    }


}
