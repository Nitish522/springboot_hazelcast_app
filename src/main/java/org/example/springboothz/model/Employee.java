package org.example.springboothz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class Employee {
    private String id;
    private String name;
    private String address;
    private String company;
    private String designation;
}
