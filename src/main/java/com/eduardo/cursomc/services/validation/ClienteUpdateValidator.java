package com.eduardo.cursomc.services.validation;

import com.eduardo.cursomc.domain.Cliente;
import com.eduardo.cursomc.domain.repositories.ClienteRepository;
import com.eduardo.cursomc.dto.ClienteDTO;
import com.eduardo.cursomc.services.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest httpServletRequest;
	
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

    	Map<String, String> map = (Map<String, String>)
                httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        List<FieldMessage> list = new ArrayList<>();

        Optional<Cliente> aux = clienteRepository.findByIsNotIdAndEmail(objDto.getId(), objDto.getEmail());

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