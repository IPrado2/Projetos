package model;

import java.util.Date;


public class Cliente {
    private String cpf;
    private String nome;
    private String sobrenome;
    private String dtnasc;
    private int idade;
    private String emaior;   

    public Cliente(String cpf, String nome, String sobrenome, String dtnasc, int idade, String emaior) {
        setCpf(cpf);
        setNome(nome);
        setSobrenome(sobrenome);
        setDtnasc(dtnasc);
        setIdade(idade);
        setEmaior(emaior);
    }
    
    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }


    public String getDtnasc() {
        return dtnasc;
    }

    public void setDtnasc(String dtnasc) {
        this.dtnasc = dtnasc;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmaior() {
        return emaior;
    }

    public void setEmaior(String emaior) {
        this.emaior = emaior;
    }
 
    
}
