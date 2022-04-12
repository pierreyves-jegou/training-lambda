package traininglambda.groupbycollector;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import traininglambda.beans.Person;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupByCollectorTest {

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
                .build();

        johnDo = Person.builder()
                .id(2)
                .firstName("john")
                .lastName("do")
                .age(30)
                .sex("M")
                .build();

        janneDo = Person.builder()
                .id(3)
                .firstName("janne")
                .lastName("do")
                .age(18)
                .sex("F")
                .build();

        persons = Arrays.asList(jeanBon, johnDo, janneDo);
    }

    @Test
    void groupBySex() {
        // Grouper les personnes par sexe
        Map<String, List<Person>> personsBySex = null; //TODO
        assertThat(personsBySex.size()).isEqualTo(2);
        assertThat(personsBySex.get("M"))
                .hasSize(2)
                .containsExactlyInAnyOrder(jeanBon, johnDo);
        assertThat(personsBySex.get("F"))
                .hasSize(1)
                .containsExactlyInAnyOrder(janneDo);
    }

    @Test
    void groupBySexAndMajorMinor() {
        // Grouper les personnes par sexe puis par minor/majeur
        Map<String, Map<Boolean, List<Person>>> personsBySexAndMajorMinor = null; //TODO
        assertThat(personsBySexAndMajorMinor).hasSize(2);
        assertThat(personsBySexAndMajorMinor.get("M").get(Boolean.FALSE)).containsExactlyInAnyOrder(jeanBon);
        assertThat(personsBySexAndMajorMinor.get("M").get(Boolean.TRUE)).containsExactlyInAnyOrder(johnDo);
        assertThat(personsBySexAndMajorMinor.get("F").get(Boolean.TRUE)).containsExactlyInAnyOrder(janneDo);
    }

    @Test
    void averageAgeBySex(){
        // Obtenir la moyenne d'age par sexe
        Map<String, Double> personsBySexAndAverageAge = null;
        assertThat(personsBySexAndAverageAge.get("M")).isEqualTo(20.0);
        assertThat(personsBySexAndAverageAge.get("F")).isEqualTo(18.0);
    }

    @Test
    void plusJeunePersonParSex(){
          // Obtenir la personne la plus jeune par sexe
        Map<String, Optional<Person>> youngestPersonPerSex = null;
        assertThat(youngestPersonPerSex.get("M").get()).isEqualTo(jeanBon);
        assertThat(youngestPersonPerSex.get("F").get()).isEqualTo(janneDo);
    }


    @Test
    void plusJeuneAgeParSex(){
        // Obtenir la personne la plus jeune par sexe
        Map<String, Integer> minimumAgePerSex = null;
        assertThat(minimumAgePerSex.get("M")).isEqualTo(10);
        assertThat(minimumAgePerSex.get("F")).isEqualTo(18);
    }
}
