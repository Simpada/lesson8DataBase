import org.junit.jupiter.api.Test;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

public class DBTest {


    @Test
    void shouldRetrieveSavedBook() {
        var book = sampleBook();
        dao.save(book);
        assertThat(dao.retrieve(book.getId()))
                .usingRecursiveComparison()
                .isEqualTo(book)
                //.isNotSameAs(book)
                ;
    }


    private Book sampleBook() {
        var book = new Book();
        book.setTitle("Java in a Nutshell");
        book.setAuthor("Benjamin Evans and David Flanagan");
        book.setYear(2018);
        return book;
    }

}
