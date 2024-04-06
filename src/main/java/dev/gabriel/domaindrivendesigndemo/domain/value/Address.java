package dev.gabriel.domaindrivendesigndemo.domain.value;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Address {

    private final String street;
    private final int number;
    private final String zip;
    private final String city;

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
