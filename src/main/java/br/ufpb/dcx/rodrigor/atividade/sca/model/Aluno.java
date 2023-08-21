package br.ufpb.dcx.rodrigor.atividade.sca.model;

import java.time.LocalDate;

public class Aluno {
    private String matricula;
    private String nome;
    private LocalDate dataNascimento;

    public Aluno(){
        
    }

    public Aluno(String matricula, String nome, LocalDate dataNascimento) {
        this.matricula = matricula;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    // Métodos Getters e Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if(matricula == null || matricula.length() == 0)
            throw new IllegalArgumentException("matricula == null!!!!!! cuida!!");

        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
