package com.eduardo.cursomc.services.validation;

import com.eduardo.cursomc.domain.Client;
import com.eduardo.cursomc.domain.enums.TipoCliente;
import com.eduardo.cursomc.domain.repositories.ClienteRepository;
import com.eduardo.cursomc.dto.ClientNewDTO;
import com.eduardo.cursomc.services.exception.FieldMessage;
import com.eduardo.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClientNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getType().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getIdOrRegister())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getType().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getIdOrRegister())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Optional<Client> aux = clienteRepository.findByEmail(objDto.getEmail());
        if (aux.isPresent()) {
            list.add(new FieldMessage("email", "E-mail já existente."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}