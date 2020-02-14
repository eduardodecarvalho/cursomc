package com.eduardo.cursomc.services;

import com.eduardo.cursomc.domain.City;
import com.eduardo.cursomc.domain.Client;
import com.eduardo.cursomc.domain.Address;
import com.eduardo.cursomc.domain.enums.TipoCliente;
import com.eduardo.cursomc.domain.repositories.ClienteRepository;
import com.eduardo.cursomc.domain.repositories.EnderecoRepository;
import com.eduardo.cursomc.dto.ClientDTO;
import com.eduardo.cursomc.dto.ClientNewDTO;
import com.eduardo.cursomc.services.exception.DataIntegrityException;
import com.eduardo.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getAddresses());
		return obj;
	}
	
	public Client find(Integer id) {
		return repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + id + ", Nome: " + Client.class.getName()));
	}
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível pois há entidades relacionadas.");
		}
	}

	public List<Client> findAll(){
		return repo.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy,
                                 String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, 
				Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		City cid = new City(objDto.getCidadeId(), null, null);
		Address end = new Address(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getAddresses().add(end);
		cli.getTelephones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelephones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelephones().add(objDto.getTelefone3());
		}
		return cli;
	}
}
