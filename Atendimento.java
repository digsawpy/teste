package model.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Atendimento implements Serializable {

    private static int proximoNumeroAtendimento = 1;

    private int numeroAtendimento;
    private LocalDateTime dataHora;
    private Dono dono;
    private Animal animalAtendido;
    private List<Procedimento> procedimentosRealizados;
    private double valorTotal;    
    private List<Produto> produtosComprados;

    ////////////////////////////////////////////////////////

    public Atendimento(Dono dono, Animal animal) {
        this.numeroAtendimento = Atendimento.proximoNumeroAtendimento++;
        this.dataHora = LocalDateTime.now();
        this.dono = dono;
        this.animalAtendido = animal;
        this.procedimentosRealizados = new ArrayList<>();
        this.valorTotal = 0;
        this.produtosComprados = new ArrayList<>();
    }

    ////////////////////////////////////////////////////////

    public int getNumeroAtendimento() {
        return numeroAtendimento;
    }

    public Dono getDono() {
        return dono;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Animal getAnimal() {
        return animalAtendido;
    }

    ////////////////////////////////////////////////////////

    public List<Procedimento> getProcedimentosRealizados() {
        return procedimentosRealizados;
    }

    public void adicionarProcedimento(Procedimento procedimento) {
        procedimentosRealizados.add(procedimento);
    }

    public void removerProcedimento(Procedimento procedimento) {
        procedimentosRealizados.remove(procedimento);
    }
    
    ////////////////////////////////////////////////////////

    private String converterLocalDateParaString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataHora.format(formato);
        return dataFormatada;
    }

    ////////////////////////////////////////////////////////

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    ////////////////////////////////////////////////////////

    public double calcularValorTotal() {
        double valorTotal = 0;
        for (Procedimento procedimento : procedimentosRealizados) {
            valorTotal += procedimento.getValor();
        }
        return valorTotal;
    }

    ////////////////////////////////////////////////////////

    /*
    private boolean existeProduto(Produto produto) {
        for (Produto p : produtosComprados) {
            if (p.equals(produto)) {
                return true;
            }
        }
        return false;
    }
    */

    public boolean comprarProduto(Produto produto, int quantidade) {
        if (produto != null && quantidade > 0) {
            if (quantidade <= produto.getQuantidadeEstoque()) {
                Produto produtoComprado = new Produto(
                    produto.getId(), 
                    produto.getNome(), 
                    produto.getDescricao(), 
                    produto.getPrecoVenda(), 
                    quantidade
                );
                produtosComprados.add(produtoComprado);
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
                return true;
            }
        }
        return false;
    }

    public double calcularPrecoTotalProdutosComprados() {
        double precoTotal = 0.0;        
        for (Produto produto : produtosComprados) {
            precoTotal += produto.getPrecoVenda() * produto.getQuantidadeEstoque();
        }        
        return precoTotal;
    }

    public StringBuilder[] relatorioProdutosComprados() {
        StringBuilder[] vetorDeStringBuilders = new StringBuilder[2];
        vetorDeStringBuilders[0] = new StringBuilder();
        vetorDeStringBuilders[0].append("\n");
        double subtotal2 = 0.0;
        if (!produtosComprados.isEmpty()) {
            vetorDeStringBuilders[0].append("Produtos comprados pelo dono:\n");
            for (Produto produto : produtosComprados) {
                vetorDeStringBuilders[0].append("  -> ").append(produto.getNome()).append(" [R$ " + produto.getPrecoVenda() + "]").append("\n");
                subtotal2 += produto.getPrecoVenda();
            }
            vetorDeStringBuilders[0].append("\n");
            vetorDeStringBuilders[0].append("     Subtotal 2: R$ ").append(subtotal2).append("\n\n");
        }
        vetorDeStringBuilders[1] = new StringBuilder();
        vetorDeStringBuilders[1].append(subtotal2);
        return vetorDeStringBuilders;
    }

    ////////////////////////////////////////////////////////

    public String gerarFatura() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==========================").append("\n");
        stringBuilder.append("Fatura do Atendimento #").append(numeroAtendimento).append("\n");
        stringBuilder.append("==========================").append("\n");
        stringBuilder.append("Data: ").append(converterLocalDateParaString()).append("\n");
        stringBuilder.append("--------------------------").append("\n");
        stringBuilder.append("\nProcedimentos Realizados:\n");
        double subtotal1 = 0.0;
        for (Procedimento procedimento : procedimentosRealizados) {
            stringBuilder.append("- ").append(procedimento.getNome()).append(":\n");
            List<Produto> produtos = procedimento.getProdutos();
            if (produtos != null && !produtos.isEmpty()) {
                stringBuilder.append("  Produtos Utilizados:\n");
                for (Produto produto : produtos) {
                    stringBuilder.append("  -> ").append(produto.getNome()).append(" [R$ " + produto.getPrecoVenda() + "]").append("\n");
                    subtotal1 += produto.getPrecoVenda();
                }
            }
        }
        if (subtotal1 > 0) {
            stringBuilder.append("\n     Subtotal 1: R$ ").append(subtotal1).append("\n\n");
            stringBuilder.append("--------------------------").append("\n");
        }
        StringBuilder[] relatorioProdutos = relatorioProdutosComprados();
        if (relatorioProdutos != null) {
            stringBuilder.append(relatorioProdutos[0].toString());
            stringBuilder.append("--------------------------").append("\n");
            double total = subtotal1 + (Double.parseDouble(relatorioProdutos[1].toString()));
            stringBuilder.append("TOTAL: R$ ").append(total).append("\n");
            stringBuilder.append("--------------------------").append("\n");
        }
        return stringBuilder.toString();
    }

    ////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Atendimento{" +
                "numeroAtendimento=" + numeroAtendimento +
                ", dataHora=" + dataHora +
                ", animalAtendido=" + animalAtendido +
                ", procedimentosRealizados=" + procedimentosRealizados +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atendimento that = (Atendimento) o;
        return numeroAtendimento == that.numeroAtendimento &&
                Objects.equals(dataHora, that.dataHora) &&
                Objects.equals(animalAtendido, that.animalAtendido) &&
                Objects.equals(procedimentosRealizados, that.procedimentosRealizados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroAtendimento, dataHora, animalAtendido, procedimentosRealizados);
    }

}
