package br.com.fiap.livraria.controller.dto;


import lombok.Data;

@Data
public class UpdatePrecoLivroDTO {
    private double preco;

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
