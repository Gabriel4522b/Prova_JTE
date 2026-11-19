package com.exemplo.despesas.model;

public class Categoria {
    private int id;
    private String nome;
    private String descricao;
    private Double limite;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getLimite() { return limite; }
    public void setLimite(Double limite) { this.limite = limite; }
}