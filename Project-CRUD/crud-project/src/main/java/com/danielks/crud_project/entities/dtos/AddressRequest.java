package com.danielks.crud_project.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressRequest(

        @Pattern(regexp = "^[0-9]{8}$")
        String zipCode,
        @NotBlank String street,
        @NotBlank String neighborhood,
        @NotBlank String city,
        @NotBlank String state,
        String number
) {
}
