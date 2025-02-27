package fr.nakaoni.inm.domain.bank;

public class Bank {
    private Long id;
    private String name;

    public Bank() { }

    public Bank(String name) {
        this.name = name;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }
}
