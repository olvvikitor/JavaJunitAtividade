package org.example;

public class Produto {
    private String nome;
    private double preco;
    private int estoque;

    public Produto(String nome, double preco, int estoque) {
        if(preco<0) throw new IllegalArgumentException("Preço inferior a 0.0");
        if( estoque < 0) throw  new IllegalArgumentException("Estoque inferior a 0");

        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        if(preco<0) throw new IllegalArgumentException("Preço inferior a 0.0");

        this.preco = preco;
    }

    public int getEstoque() {
        return this.estoque;
    }

    public void setEstoque(int estoque) {
        if( estoque < 0) throw  new IllegalArgumentException("Estoque inferior a 0");
        this.estoque = estoque;
    }

    public boolean diminuirEstoque(int quantidade) {
        if (this.estoque >= quantidade) {
            this.estoque -= quantidade;
            return true;
        }
        return false;
    }

    public void aumentarEstoque(int quantidade) {
        this.estoque += quantidade;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
