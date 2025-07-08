package org.example.springboothz.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.springboothz.viewResolver.InternalField;
import static org.example.springboothz.viewResolver.InternalField.DEFAULT_FILTER;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@JsonFilter(DEFAULT_FILTER)
public class Employee {
    @InternalField
    private String id;
    private String name;
    private String address;

    @InternalField
    private String company;
    private String designation;
}
