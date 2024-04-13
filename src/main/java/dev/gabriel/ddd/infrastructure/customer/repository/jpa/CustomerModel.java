package dev.gabriel.ddd.infrastructure.customer.repository.jpa;

import dev.gabriel.ddd.domain.customer.entity.Customer;
import dev.gabriel.ddd.domain.customer.value.Address;
import dev.gabriel.ddd.infrastructure._shared.EntityModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "t_customer")
public class CustomerModel implements EntityModel<Customer> {
    @Id
    private UUID id;

    @NotBlank
    private String name;

    private String street;

    private Integer number;

    private String zip;

    private String city;

    public static CustomerModel from(Customer customer) {
        var address = customer.getAddress();

        return new CustomerModel(
                customer.getId(),
                customer.getName(),
                address == null ? null : address.street(),
                address == null ? null : address.number(),
                address == null ? null : address.zip(),
                address == null ? null : address.city()
        );
    }

    @Override
    public Customer toEntity() {
        Customer customer = new Customer(this.id, this.name);
        customer.setAddress(new Address(this.street, this.number, this.zip, this.city));
        return customer;
    }
}
