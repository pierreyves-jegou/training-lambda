package traininglambda.tomapcollector;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import traininglambda.beans.Address;
import traininglambda.beans.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ToMapCollectorTest {

    private static List<Person> persons;
    private static Person jeanBon;
    private static Person johnDo;
    private static Person janneDo;

    @BeforeAll
    static void beforeAll() {
        jeanBon = Person.builder()
                .id(1)
                .firstName("jean")
                .lastName("bon")
                .age(10)
                .sex("M")
                .email("jeanbon@cgi.com")
                .email("jeanbon@gmail.com")
                .address(Address.builder()
                        .number("19")
                        .street("rue des mouettes")
                        .town("Sydney")
                        .country("France")
                        .build())
                .build();

        johnDo = Person.builder()
                .id(2)
                .firstName("john")
                .lastName("do")
                .age(30)
                .sex("M")
                .email("johndo@cgi.com")
                .email("johndo@gmail.com")
                .address(Address.builder()
                        .number("21")
                        .street("rue des Herisson")
                        .town("Sydney")
                        .country("Australie")
                        .build())
                .build();

        janneDo = Person.builder()
                .id(3)
                .firstName("janne")
                .lastName("do")
                .age(10)
                .sex("F")
                .build();

        persons = Arrays.asList(jeanBon, johnDo, janneDo);
    }

    @Test
    void personPerFirstNameAndLastName() {

        //// TODO : commit solution
        Map<String, Person> personPerFirstNameAndLastName = persons
                .stream()
                .collect(Collectors.toMap(
                        person -> person.getFirstName() + person.getLastName(),
                        Function.identity()
                        )
                );

//        Map<String, Person> personPerFirstNameAndLastName = null;// TODO

        assertThat(personPerFirstNameAndLastName).hasSize(3);
        assertThat(personPerFirstNameAndLastName.get("jeanbon")).isNotNull();
        assertThat(personPerFirstNameAndLastName.get("johndo")).isNotNull();
        assertThat(personPerFirstNameAndLastName.get("jannedo")).isNotNull();
    }

    @Test
    void youngestPersonPerTownWithKeyConflict(){

        // Ex : obtenir une map avec comme clef la ville de la personne. En cas de conflict, garder la personne la plus jeune

        //// TODO : commit solution
        Map<Integer, Person> personPerAge = persons
                .stream()
                .collect(Collectors.toMap(
                        person -> person.getAge(),
                        Function.identity(),
                        (a, b) -> a
                        )
                );
//
//        Map<Integer, Person> personPerAge = null; //TODO

        assertThat(personPerAge).hasSize(2);
        assertThat(personPerAge.get(10).getFirstName()).isEqualTo("jean");
        assertThat(personPerAge.get(30).getFirstName()).isEqualTo("john");

    }

    @Test
    void otherMapSupplier(){
        TreeMap<String, Person> personPerFirstNameAndLastNameOrdered = persons
                .stream()
                .collect(Collectors.toMap(
                        person -> person.getFirstName() + person.getLastName(),
                        Function.identity(),
                        (a, b) -> a,
                        TreeMap::new
                        )
                );

        //Map<String, Person> personPerFirstNameAndLastName = null;// TODO

        assertThat(personPerFirstNameAndLastNameOrdered.firstEntry().getValue().getFirstName()).isEqualTo("janne");
    }

}
