import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        /*
        Supplier<Stream<String>> wordsStream = () -> Stream.of("hi", "hello", "abc", "def", "ceq");

        List<String> excitingWords, eyeWords, upperCaseWords;
        excitingWords = wordsStream.get().map(word -> word+"!").toList();
        eyeWords = wordsStream.get().map(word -> word.replace("i", "eye")).toList();
        upperCaseWords = wordsStream.get().map(String::toUpperCase).toList();

        List<String> shortWords, wordsWithB, evenLengthWords;
        shortWords = wordsStream.get().filter(word -> word.length()<3).toList();
        wordsWithB = wordsStream.get().filter(word -> word.contains("b")).toList();
        evenLengthWords = wordsStream.get().filter(word -> word.length()%2==0).toList();


        List<String> p5;
        wordsStream.get()
                .map(String::toUpperCase)
                .filter(word -> word.length()<4)
                .filter(word -> word.contains("E"))
                .forEach(System.out::println);
        wordsStream.get().filter(word -> word.length()<4 && (word.contains("Q") || word.contains("q"))).forEach(System.out::println);
        String p6 = wordsStream.get().reduce("", (acc, word) -> acc+word.toUpperCase());
        String p7 = wordsStream.get().map(String::toUpperCase).reduce("", (acc, word) -> acc+word);
        String p8 = wordsStream.get().reduce("", (acc, word) -> acc.isEmpty() ? word : acc + "," + word);
        int p9 = wordsStream.get().mapToInt(String::length).reduce(0, Integer::sum);
        long p10 = wordsStream.get().filter(word -> word.contains("h")).count();
        System.out.println(p10);
        */

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        System.out.println(numbers.stream().filter(num -> num%4==0).map(num->num+1).mapToInt(num -> num).sum() %2);
    }

}