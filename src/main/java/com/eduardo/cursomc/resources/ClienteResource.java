package com.eduardo.cursomc.resources;

import com.eduardo.cursomc.domain.Client;
import com.eduardo.cursomc.dto.ClientDTO;
import com.eduardo.cursomc.dto.ClientNewDTO;
import com.eduardo.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> find(@PathVariable final Integer id) {
		Client obj = clienteService.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDTO, @PathVariable final Integer id) {
		Client obj = clienteService.fromDTO(objDTO);
		obj.setId(id);
		clienteService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable final Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> list = clienteService.findAll();
		List<ClientDTO> listDTO = list.stream().map(ClientDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Client> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> listDTO = list.map(ClientDTO::new);
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO objDTO) {
		Client obj = clienteService.fromDTO(objDTO);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
