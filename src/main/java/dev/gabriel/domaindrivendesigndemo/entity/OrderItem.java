package dev.gabriel.domaindrivendesigndemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
public class OrderItem {

    private UUID id;
    private String name;
    @Getter
    private double price;

}
