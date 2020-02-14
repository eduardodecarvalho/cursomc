package com.eduardo.cursomc.dto;

import com.eduardo.cursomc.services.validation.ClienteInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteInsert
public class ClientNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Mandatory")
    @Length(min = 5, message = "The length must contain between 5 and 80 characters")
    private String nome;
    @NotEmpty(message = "Mandatory")
    @Email(message = "Invalid email")
    private String email;
    @NotEmpty(message = "Mandatory")
    private String idOrRegister;
    private Integer type;

    @NotEmpty(message = "Mandatory")
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    @NotEmpty(message = "Mandatory")
    private String cep;

    @NotEmpty(message = "Mandatory")
    private String telephone1;
    private String telephone2;
    private String telephone3;

    private Integer cityId;

    public ClientNewDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdOrRegister() {
        return idOrRegister;
    }

    public void setIdOrRegister(String cpfOuCpnj) {
        this.idOrRegister = cpfOuCpnj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getTelephone3() {
        return telephone3;
    }

    public void setTelephone3(String telephone3) {
        this.telephone3 = telephone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

}
