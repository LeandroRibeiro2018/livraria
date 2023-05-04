package br.com.fiap.livraria.service;

import br.com.fiap.livraria.models.dto.CreateUpdateLivroDTO;
import br.com.fiap.livraria.models.dto.LivroDTO;
import br.com.fiap.livraria.models.dto.UpdatePrecoLivroDTO;
import br.com.fiap.livraria.models.entity.Livro;

import java.util.List;

public interface LivroService {
    List<LivroDTO> buscarLivros(String titulo);
    LivroDTO buscarPorId(int id);
    LivroDTO criar(CreateUpdateLivroDTO createUpdateLivroDTO);
    LivroDTO atualizar(CreateUpdateLivroDTO stockCreateUpdateDTO, int id);
    LivroDTO atualizarPreco(UpdatePrecoLivroDTO updatePrecoLivroDTO, int id);
    Livro delete(int id);


}
