import java.util.Random;

public class SampleData {

    private static final Random random = new Random();

    static Book sampleBook() {
        var book = new Book();
        book.setTitle(pickOne(
            "The Adventures of ",
            "Becoming ",
            "The start of "
        ) + sampleFullName());
        book.setAuthor(sampleFullName());
        book.setYear(random.nextInt(2023));
        return book;
    }

//    public static Library sampleLibrary() {
//
//    }

    private static String sampleFullName() {
        return pickOne("Marcus", "Simone", "Brienne")
                + " " +
                pickOne("Chaves", "Skywalker", "Burgesson");
    }

    private static String pickOne(String... alternatives) {
        return alternatives[random.nextInt(alternatives.length)];
    }

}
