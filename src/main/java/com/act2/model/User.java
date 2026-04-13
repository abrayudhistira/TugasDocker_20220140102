package com.act2.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @NotBlank(message = "ID tidak boleh kosong")
    private String id;
    @NotBlank(message = "Nama tidak boleh kosong")
    @Size(min = 3, max = 50, message = "Nama minimal 3 huruf")
    private String nama;
    @NotBlank(message = "NIM tidak boleh kosong")
    @Pattern(regexp = "^[0-9]*$", message = "NIM harus berupa angka")
    private String nim;
    @NotBlank(message = "Gender harus dipilih")
    private String gender;

}
