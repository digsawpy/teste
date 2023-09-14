package model.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Procedimento implements Serializable {

    private static int idGeral = 1;

    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private List<Produto> produtosUtilizados;

    ////////////////////////////////////////////////////////

    public Procedimento(String nome, String descricao, double valor) {
        this.id = Procedimento.idGeral++;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.produtosUtilizados = new ArrayList<>();
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    ////////////////////////////////////////////////////////

    public List<String> obterNomesProdutos() {
        List<String> nomes = new ArrayList<>();
        for (Produto produto : produtosUtilizados) {
            nomes.add(produto.getNome());
        }
        return nomes;
    }

    public List<Produto> getProdutos() {
        return produtosUtilizados;
    }
    
    public void adicionarProduto(Produto produto) {
        produtosUtilizados.add(produto);
    }

    public void removerProduto(Produto produto) {
        produtosUtilizados.remove(produto);
    }

    ////////////////////////////////////////////////////////

    public double calcularValorTotalProdutos() {
        double valorTotal = 0.0;
        for (Produto produto : produtosUtilizados) {
            valorTotal += produto.getPrecoVenda();
        }
        return valorTotal;
    }
    

    ////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Procedimento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Procedimento that = (Procedimento) o;
        return id == that.id &&
                Double.compare(that.valor, valor) == 0 &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, valor);
    }

}
