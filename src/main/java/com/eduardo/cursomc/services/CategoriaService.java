package com.eduardo.cursomc.services;

import com.eduardo.cursomc.domain.Category;
import com.eduardo.cursomc.domain.repositories.CategoriaRepository;
import com.eduardo.cursomc.dto.PedidoDTO;
import com.eduardo.cursomc.services.exception.DataIntegrityException;
import com.eduardo.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Category find(Integer id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Category update(Category obj) {
		Category newObj = find(obj.getId());
		updateData(newObj, obj);
		return categoriaRepository.save(newObj);
	}

	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}

	public void delete(Integer id) {
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir uma categoria que possui produtos.");
		}
	}
	
	public List<Category> findAll(){
		return categoriaRepository.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy,
                                   String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, 
				Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Category fromDTO(PedidoDTO objDTO) {
		return new Category(objDTO.getId(), objDTO.getNome());
	}
}
