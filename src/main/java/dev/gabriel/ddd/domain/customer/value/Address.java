package dev.gabriel.ddd.domain.customer.value;

public record Address(String street, int number, String zip, String city) {
    public Address(String street, int number, String zip, String city) {
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.city = city;
        validate();
    }

    public void validate() {
        if (this.street.isBlank()) {
            throw new IllegalArgumentException("Street must not be blank");
        }
        if (this.zip.isBlank()) {
            throw new IllegalArgumentException("Zip must not be blank");
        }
        if (this.city.isBlank()) {
            throw new IllegalArgumentException("City must not be blank");
        }
    }
}
