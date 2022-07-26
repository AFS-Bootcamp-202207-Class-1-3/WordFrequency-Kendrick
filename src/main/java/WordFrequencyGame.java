import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    private static final String REGEX = "\\s+";
    private static final String DELIMITER = "\n";


    public String getWordFrequency(String inputStr) {

        try {
            String[] wordsArr = inputStr.split(REGEX);
            List<WordInformation> wordInformationList = new ArrayList<>();
            Arrays.asList(wordsArr).stream().forEach(word -> {
                wordInformationList.add(new WordInformation(word, 1));
            });

            Map<String, List<WordInformation>> wordInfoListMap = getWordInfoListMap(wordInformationList);

            wordInformationList.clear();
            generateWordInformationList(wordInfoListMap, wordInformationList);

            wordInformationList.sort((info1, info2) -> info2.getWordCount() - info1.getWordCount());

            return getStringResult(wordInformationList);
        } catch (SecurityException e) {
            return e.getMessage();
        }
    }


    private Map<String, List<WordInformation>> getWordInfoListMap(List<WordInformation> wordInformationList) throws SecurityException{
        return wordInformationList.stream().collect(Collectors.groupingBy(info -> info.getWord()));
    }

    private String getStringResult(List<WordInformation> wordInformationList) throws SecurityException{
        StringJoiner joiner = new StringJoiner(DELIMITER);

        wordInformationList.stream().forEach(info -> {
            joiner.add(info.getWord() + " " + info.getWordCount());
        });

        return joiner.toString();
    }

    private void generateWordInformationList(Map<String, List<WordInformation>> wordInfoListMap, List<WordInformation> wordInformationList) throws SecurityException{
        // generate wordInformationList
        new ArrayList<>(wordInfoListMap.entrySet()).stream().forEach(entry -> {
            wordInformationList.add(new WordInformation(entry.getKey(), entry.getValue().size()));
        });
    }

}
