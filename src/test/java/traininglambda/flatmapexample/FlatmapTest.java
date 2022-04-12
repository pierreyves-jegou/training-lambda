package traininglambda.flatmapexample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import traininglambda.beans.Address;
import traininglambda.beans.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FlatmapTest {

    private static List<Person> persons;

    @BeforeAll
    static void beforeAll() {
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
                .address(Address.builder()
                        .number("21")
                        .street("rue des Herisson")
                        .town("Sydney")
                        .country("Australie")
                        .build())
                .build();

        persons = Arrays.asList(jeanBon, johnDo);
    }

    @Test
    void allGmailComEmailsBadExample() {
        // Récupérer toutes les adresses gmail
        List<String> gmailAddressList = new ArrayList<>();
        persons.stream()
                .map(person -> person.getEmails())
                .forEach(emails -> {
                    gmailAddressList.addAll(emails.stream()
                            .filter(email -> email.endsWith("@gmail.com"))
                            .collect(Collectors.toList()));
                });

        assertThat(gmailAddressList)
                .hasSize(2)
                .containsExactlyInAnyOrder("jeanbon@gmail.com", "johndo@gmail.com");
    }

    @Test
    void allGmailComEmailsBetterExample(){


        List<String> gmailAddressList = null; // TODO

        assertThat(gmailAddressList)
                .hasSize(2)
                .containsExactlyInAnyOrder("jeanbon@gmail.com", "johndo@gmail.com");

    }

//    Stream<Stream<X>      -> flatMap ->	Stream<X>
//    Stream<String[]>      -> flatMap ->	Stream<String>
//    Stream<Set<String>>   -> flatMap ->	Stream<String>
//    Stream<List<String>>  -> flatMap ->	Stream<String>
//    Stream<List<Object>>  -> flatMap ->	Stream<Object>

}
