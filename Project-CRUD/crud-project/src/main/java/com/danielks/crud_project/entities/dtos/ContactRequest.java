package com.danielks.crud_project.entities.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ContactRequest(
        @NotBlank
        @Size(min = 3)
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "^[0-9]{10,11}$")
        String phone,

        @NotNull @Past LocalDate birthday,

        @Valid AddressRequest address
) {}
