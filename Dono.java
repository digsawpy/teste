package model.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dono implements Serializable {

    private static int idGeral = 1;

    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private List<Animal> animais;
    private List<Atendimento> atendimentos;

    ////////////////////////////////////////////////////////

    public Dono(String nome) {
        this.id = Dono.idGeral++;
        this.nome = nome;
        this.animais = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
    }

    public Dono(String nome, String endereco, String telefone, String email) {
        this.id = Dono.idGeral++;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.animais = new ArrayList<>();
        this.atendimentos = new ArrayList<>();
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    ////////////////////////////////////////////////////////

    public List<Animal> getAnimais() {
        return animais;
    }

    private boolean animalJaExiste(Animal animal) {
        for (Animal a : animais) {
            if (a.getNome().equalsIgnoreCase(animal.getNome())) {
                return true;
            }
        }
        return false;
    }

    public boolean adicionarAnimal(Animal animal) {
        if (!animalJaExiste(animal)) {
            animais.add(animal);
            return true;
        }
        return false;
    }

    public boolean removerAnimal(Animal animal) {
        if (animalJaExiste(animal)) {
            animais.remove(animal);
            return true;
        }
        return false;
    }

    ////////////////////////////////////////////////////////
    
    public void adicionarAtendimento(Atendimento atendimento) {
        atendimentos.add(atendimento);
    }

    public double calcularValorTotalAtendimentos() {
        double valorTotal = 0.0;        
        for (Atendimento atendimento : atendimentos) {
            valorTotal += atendimento.calcularValorTotal();
        }        
        return valorTotal;
    }

    ////////////////////////////////////////////////////////

    private String mostrarAtendimentosPorAnimal(Animal animalEspecifico) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Atendimentos para o animal: ").append(animalEspecifico.getNome()).append("\n");
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.getAnimal() == animalEspecifico) {
                stringBuilder.append("ID do Atendimento: ").append(atendimento.getNumeroAtendimento()).append("\n");
                stringBuilder.append("Data do Atendimento: ").append(atendimento.getDataHora()).append("\n");
                stringBuilder.append("Procedimentos realizados:\n");
                double valorTotalProcedimentos = 0.0;
                for (Procedimento procedimento : atendimento.getProcedimentosRealizados()) {
                    stringBuilder.append("  - ").append(procedimento.getNome()).append("\n");
                    stringBuilder.append("  - ").append(procedimento.getValor()).append("\n");
                    valorTotalProcedimentos += procedimento.getValor();
                    List<Produto> produtos = procedimento.getProdutos();
                    if (produtos != null && !produtos.isEmpty()) {
                        stringBuilder.append("    Produtos utilizados/vendidos:\n");
                        double valorTotalProdutos = 0.0;
                        for (Produto produto : produtos) {
                            stringBuilder.append("      - ").append(produto.getNome()).append("\n");
                            valorTotalProdutos += produto.getPrecoVenda();
                        }
                        stringBuilder.append("    Valor total dos produtos: ").append(valorTotalProdutos).append("\n");
                    }
                }
                stringBuilder.append("Valor total dos procedimentos: ").append(valorTotalProcedimentos).append("\n");
                stringBuilder.append("------------------------------------\n");
            }
        }    
        return stringBuilder.toString();
    }
    
    ////////////////////////////////////////////////////////
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dono{\n");
        stringBuilder.append("  id=").append(id).append(",\n");
        stringBuilder.append("  nome='").append(nome).append("',\n");
        stringBuilder.append("  endereco='").append(endereco).append("',\n");
        stringBuilder.append("  telefone='").append(telefone).append("',\n");
        stringBuilder.append("  email='").append(email).append("',\n");
        stringBuilder.append("  animais=[\n");
        for (Animal animal : animais) {
            stringBuilder.append("    ").append(animal.toString()).append(",\n");
            String atendimentosPorAnimal = mostrarAtendimentosPorAnimal(animal);
            stringBuilder.append(atendimentosPorAnimal);
        }
        stringBuilder.append("  ]\n");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    ////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dono dono = (Dono) o;
        return id == dono.id &&
                Objects.equals(nome, dono.nome) &&
                Objects.equals(endereco, dono.endereco) &&
                Objects.equals(telefone, dono.telefone) &&
                Objects.equals(email, dono.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, endereco, telefone, email);
    }

}
