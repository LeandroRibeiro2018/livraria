package br.com.fiap.livraria.controller.dto;

import br.com.fiap.livraria.models.entity.Livro;
import lombok.Data;


import java.util.Date;
@Data
public class LivroDTO {

    private int id;
    private String titulo;
    private String descricao;
    private double preco;
    private String ISBN;
    private Date dataDePublicacao;

    public LivroDTO(){}

    public LivroDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.descricao = livro.getDescricao();
        this.ISBN = livro.getISBN();
        this.dataDePublicacao = livro.getDataDePublicacao();
        this.preco = livro.getPreco();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getDataDePublicacao() {
        return dataDePublicacao;
    }

    public void setDataDePublicacao(Date dataDePublicacao) {
        this.dataDePublicacao = dataDePublicacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
