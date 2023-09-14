package model.model;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable {

    private static int idGeral = 1;
    private static int quantidadeEstoque = 0;

    private int id;
    private String nome;
    private String descricao;
    private double precoVenda;
    private int quantidade;
    

    ////////////////////////////////////////////////////////

    public Produto(int id, String nome, String descricao, double precoVenda) {
        this.id = Produto.idGeral++;
        this.nome = nome;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.quantidade = 0;
    }

    public Produto(int id, String nome, String descricao, double precoVenda, int quantidade) {
        this.id = Produto.idGeral++;
        this.nome = nome;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

    ////////////////////////////////////////////////////////

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeEstoque() {
        return Produto.quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        Produto.quantidadeEstoque += quantidadeEstoque;
    }

    ////////////////////////////////////////////////////////

    public boolean venderProduto(int quantidadeVendida) {
        if (quantidadeVendida > 0 && quantidadeVendida <= quantidadeEstoque) {
            quantidadeEstoque -= quantidadeVendida;
            return true;
        }
        return false;
    }

    ////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", precoVenda=" + precoVenda +
                ", quantidade=" + quantidade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id &&
                Double.compare(produto.precoVenda, precoVenda) == 0 &&
                Produto.quantidadeEstoque == produto.getQuantidadeEstoque() &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(descricao, produto.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, precoVenda, quantidade);
    }

}
