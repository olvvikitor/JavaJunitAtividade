package org.example;

public class Venda {
    private Produto produto;
    private int quantidadeVendida;
    private double totalVenda;

    public Venda(Produto produto, int quantidadeVendida) {
        if(quantidadeVendida<0) throw new IllegalArgumentException("quantidade inferior a 0.0");
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
    }

    public boolean realizarVenda() {
        if (this.produto.diminuirEstoque(quantidadeVendida)) {
            this.totalVenda = this.produto.getPreco() * this.quantidadeVendida;
            return true;
        }
        return false;
    }

    public double getTotalVenda() {
        return totalVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
}
