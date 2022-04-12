package traininglambda.customcollector;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import traininglambda.beans.Address;
import traininglambda.beans.Person;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomCollectorTest {

    private static List<Person> persons;

    @BeforeAll
    static void beforeAll(){
        Person jeanBon = Person.builder()
                .id(1)
                .firstName("jean")
                .lastName("bon")
                .age(12)
                .email("jeanbon@cgi.com")
                .email("jeanbon@gmail.com")
                .address(Address.builder()
                        .number("19")
                        .street("rue des mouettes")
                        .town("vic la gardiole")
                        .country("France")
                        .build())
                .build();

        Person johnDo = Person.builder()
                .id(2)
                .firstName("john")
                .lastName("do")
                .age(25)
                .email("johndo@cgi.com")
                .email("johndo@gmail.com")
                .email("johndo@yahoo.fr")
                .address(Address.builder()
                        .number("21")
                        .street("rue des Herisson")
                        .town("Sydney")
                        .country("Australie")
                        .build())
                .build();

        persons = Arrays.asList(jeanBon, johnDo);
    }

    public Collector<Person, List<String>, Map<String, Long>> emailStatisticCollectorBuilder(){
        return new Collector<>() {
            @Override
            public Supplier<List<String>> supplier() {
                return () -> new ArrayList<>();
            }

            @Override
            public BiConsumer<List<String>, Person> accumulator() {
                return (list, person) -> {
                    Pattern emailPattern = Pattern.compile(".*@(.*)");
                    person.getEmails().forEach(email -> {
                        Matcher matcher = emailPattern.matcher(email);
                        if(matcher.find()){
                            list.add(matcher.group(1));
                        }
                    });
                };
            }

            @Override
            public BinaryOperator<List<String>> combiner() {
                return (list1, list2) -> {
                    List mergeList = new ArrayList();
                    mergeList.addAll(list1);
                    mergeList.addAll(list2);
                    return mergeList;
                };
            }

            @Override
            public Function<List<String>, Map<String, Long>> finisher() {
                return list ->
                    list.stream().collect(Collectors.groupingBy(val -> val, Collectors.counting()));
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Set.of(Characteristics.CONCURRENT);
            }
        };
    }

    @Test
    void emailProviderStatistics(){

        Map<String, Long> emailProviderStatistics = persons
                .stream()
                .collect(emailStatisticCollectorBuilder());

        //Map<String, Long> emailProviderStatistics = null; //TODO
        System.out.println(emailProviderStatistics);
        assertThat(emailProviderStatistics.get("cgi.com")).isEqualTo(2);
        assertThat(emailProviderStatistics.get("yahoo.fr")).isEqualTo(1);
        assertThat(emailProviderStatistics.get("gmail.com")).isEqualTo(2);
    }

}
