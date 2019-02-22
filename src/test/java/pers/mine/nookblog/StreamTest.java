package pers.mine.nookblog;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author yebukong
 * @description TODO
 * @since 2018-10-25 14:04
 */
public class StreamTest {
    @Test
    public void testSet() {
        Set<Integer> integerSet = Set.of(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(integerSet);
    }

    @Test
    public void testList() {
        List<Integer> integerSet = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println(integerSet);
    }

    @Test
    public void testMap() {
        Map<String, String> stringMap = Map.of("k1", "v1", "k2", "v2", "k3", "v3");
        System.out.println(stringMap);
        Map.Entry<String, String> entry1 = Map.entry("k1", "v1");
        Map.Entry<String, String> entry2 = Map.entry("k11", "v11");
        Map.Entry<String, String> entry3 = Map.entry("k12", "v12");
        Map<String, String> mapOfEntries = Map.ofEntries(entry1, entry2, entry3);
        System.out.println(mapOfEntries);
    }

    @Test
    public void testStream1() {
        Optional<Integer> integerOptional = Stream.ofNullable(Integer.valueOf("1232")).findAny();
        System.out.println(integerOptional.get());
    }

    @Test
    public void testStream2() {
        Stream.of(1, 2, 3, 4, 5, 6).dropWhile(x -> x == 3)/*.takeWhile(x -> x == 2)*/
                .forEach(System.out::println);
    }

    @Test
    public void testStream3() {
        IntStream.of(1, 2, 3, 4, 5, 6).forEach(System.out::println);
    }

    @Test
    public void testStream4() {
        String[] urlPrefixes = new String[]{""};
        String[] urlPrefixes1 = {};
        System.out.println(urlPrefixes.length);
        System.out.println(urlPrefixes1.length);
        IntStream.iterate(1, i -> i < 10, i -> i + 2).forEach(System.out::println);
    }

}
