package no.kristiania.library;

public class Library {
    private String name;
    private String address;
    private long id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
