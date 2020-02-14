package com.eduardo.cursomc.dto;

import com.eduardo.cursomc.domain.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "Mandatory")
	@Length(min = 5, max = 80, message = "The length must contain between 5 and 80 characters")
	private String nome;

	public OrderDTO() {
	}

	public OrderDTO(Category obj) {
		id = obj.getId();
		nome = obj.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
