package com.example.MyProject;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


public class TransactionRequest {
    @NotNull(message = "Name cannot be null")
    private String name;

    @Email(message = "Email should be valid")
    private String email;
}