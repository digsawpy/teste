package model.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Animal implements Serializable {

    private int id;
    private String nome;
    private String especie;
    private String raca;
    private LocalDate dataNascimento;
    private int idade;
    private String historicoMedico;

    ////////////////////////////////////////////////////////

    public Animal(String nome, Dono dono) {
        this.nome = nome;
    }
    
    public Animal(int id, String nome, String especie, String raca, String dataNascimento, String historicoMedico) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.idade = calcularIdade(this.dataNascimento);
        this.historicoMedico = historicoMedico;
    }

    ////////////////////////////////////////////////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    ////////////////////////////////////////////////////////

    private String converterLocalDateParaString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataNascimento.format(formato);
        return dataFormatada;
    }

    ////////////////////////////////////////////////////////

    public String getDataNascimento() {
        String dataNascimento;
        dataNascimento = converterLocalDateParaString();
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
    }

    public int calcularIdade(LocalDate dataNascimento) {
        Period periodo = Period.between(dataNascimento, LocalDate.now());
        return periodo.getYears();
    }

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }

    ////////////////////////////////////////////////////////
    
    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", especie='" + especie + '\'' +
                ", raca='" + raca + '\'' +
                ", dataNascimento='" + converterLocalDateParaString() + '\'' +
                ", idade=" + idade +
                ", historicoMedico='" + historicoMedico + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                idade == animal.idade &&
                Objects.equals(nome, animal.nome) &&
                Objects.equals(especie, animal.especie) &&
                Objects.equals(raca, animal.raca) &&
                Objects.equals(dataNascimento, animal.dataNascimento) &&
                Objects.equals(historicoMedico, animal.historicoMedico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, especie, raca, dataNascimento, idade, historicoMedico);
    }
}
