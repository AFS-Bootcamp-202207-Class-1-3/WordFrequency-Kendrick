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

                String[] wordsArr = inputStr.split(REGEX);
                List<WordInformation> wordInformationList = new ArrayList<>();
                Arrays.asList(wordsArr).stream().forEach(word->{wordInformationList.add(new WordInformation(word, 1));});

                Map<String, List<WordInformation>> wordInfoListMap = getWordInfoListMap(wordInformationList);

                wordInformationList.clear();

                // 生成count计数正确的 wordInformationList
                new ArrayList<>(wordInfoListMap.entrySet()).stream().forEach(entry->{wordInformationList.add(new WordInformation(entry.getKey(),entry.getValue().size()));});

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


    private Map<String, List<WordInformation>> getWordInfoListMap(List<WordInformation> wordInformationList) {
        return wordInformationList.stream().collect(Collectors.groupingBy(info -> info.getWord()));
    }


}
