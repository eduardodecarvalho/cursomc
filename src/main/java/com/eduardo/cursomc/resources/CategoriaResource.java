package com.eduardo.cursomc.resources;

import com.eduardo.cursomc.domain.Categoria;
import com.eduardo.cursomc.dto.PedidoDTO;
import com.eduardo.cursomc.services.CategoriaService;
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
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable final Integer id) {
		Categoria obj = categoriaService.find(id);
		return ResponseEntity.ok(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody PedidoDTO objDTO) {
		Categoria obj = categoriaService.fromDTO(objDTO);
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody PedidoDTO objDTO, @PathVariable final Integer id) {
		Categoria obj = categoriaService.fromDTO(objDTO);
		obj.setId(id);
		categoriaService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable final Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll() {
		List<Categoria> list = categoriaService.findAll();
		List<PedidoDTO> listDTO = list.stream().map(PedidoDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

    @GetMapping(value = "/page")
	public ResponseEntity<Page<PedidoDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
		Page<PedidoDTO> listDTO = list.map(PedidoDTO::new);
		return ResponseEntity.ok().body(listDTO);
	}
}
