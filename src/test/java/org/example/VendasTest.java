package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

class VendasTest {

    static private  Produto produtos;
    private Venda venda;


    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("Iniciando testes...");
        produtos = new Produto("Feijão", 5.99,10);
    }
    //Testar venda com quantidade menor que o estoque disponível.
    @Test
    @DisplayName("Testar venda com quantidade menor que o estoque disponível")
    void  vendaComQuantidadeMenorQueEstoque(){
        Venda venda = new Venda(produtos, 2);
        assertTrue(venda.realizarVenda());
        assertEquals(8, produtos.getEstoque());
        assertEquals(11.98, venda.getTotalVenda());
    }
    //Testar venda com quantidade igual ao estoque disponível.
    @Test
    @DisplayName("Testar venda com quantidade igual ao estoque disponível.")
    void  vendaComQuantidadeIgualAoEstoque(){
        Venda venda = new Venda(produtos, produtos.getEstoque());
        assertTrue(venda.realizarVenda());
    }

    //Testar venda com quantidade maior que o estoque disponível (deve falhar).
    @Test
    @DisplayName("Testar venda com quantidade maior que o estoque disponível (deve falhar).")
    void  vendaComQuantidadeMenorAoEstoque(){
        Venda venda = new Venda(produtos, produtos.getEstoque() + 1);
        assertFalse(venda.realizarVenda());
    }
    //Testar cálculo do total da venda corretamente.
    @Test
    @DisplayName("Testar cálculo do total da venda corretamente.")
    void valcularTotalDaVenda(){
        Venda venda = new Venda(produtos, 2);
        assertTrue(venda.realizarVenda());
        assertEquals(11.98, venda.getTotalVenda());
    }
    //Testar aumento de estoque do produto após uma venda.
    @Test
    @DisplayName("Testar aumento de estoque do produto após uma venda.")
    void AumentarEstoqueAposVenda(){
        Venda venda = new Venda(produtos, 3);
        venda.realizarVenda();
        assertEquals(7, produtos.getEstoque());
        produtos.setEstoque(produtos.getEstoque()+8);
        assertEquals(15, produtos.getEstoque());
    }
    // Testar diminuição do estoque do produto após uma venda bem-sucedida.
    @Test
    @DisplayName("Testar diminuição do estoque do produto após uma venda bem-sucedida.")
    void reduzirEstoque(){
        Venda venda = new Venda(produtos, 2);
        assertTrue(venda.realizarVenda());
        assertEquals(8, produtos.getEstoque());
    }
    //Testar realizar venda de um produto que não existe (deve falhar).
    @Test
    @DisplayName("Testar realizar venda de um produto que não existe (deve falhar).")
    void vendaComProdutoQueNaoExiste(){
        Produto p = new Produto("Produto Não Existente", 2.30, 2);
        assertNotEquals(p, produtos);
    }
    //Testar criação de venda com quantidade negativa (deve falhar).
    @Test
    @DisplayName("Testar criação de venda com quantidade negativa (deve falhar).")
    void vendaComQuantidadeNegativa(){
        assertThrows(IllegalArgumentException.class, ()-> {
           new Venda(produtos, -2);
        });
    }
    //Testar alteração do estoque após a tentativa de venda com estoque insuficiente.
    @Test
    @DisplayName("Testar alteração do estoque após a tentativa de venda com estoque insuficiente.")
    void alterarEstoqueAposVendaComEstoqueInsuficiente(){
        Venda venda = new Venda(produtos, produtos.getEstoque()+1);
        assertFalse(venda.realizarVenda());
        produtos.setEstoque(15);
        assertTrue(venda.realizarVenda());
        assertEquals(4, produtos.getEstoque());
    }

    //Testar criação de vários produtos e realizar vendas com estoque compartilhado.
    @Test
    @DisplayName("Testar criação de vários produtos e realizar vendas com estoque compartilhado.\n")
    void criarVariosProdutosEVenderComEstoqueCompartilhado() {
        ArrayList<Produto> produtos1 = new ArrayList<>();

        Produto farinha = new Produto("Farinha", 7.50, 10);
        Produto arroz = new Produto("Arroz", 5.00, 10);
        Produto macarrao = new Produto("Macarrão", 3.50, 10);

        produtos1.add(farinha);
        produtos1.add(arroz);
        produtos1.add(macarrao);

        Venda venda1 = new Venda(farinha, 2);
        venda1.realizarVenda();
        assertEquals(8, farinha.getEstoque());
        Venda venda2 = new Venda(arroz, 5);
        venda2.realizarVenda();
        assertEquals(5, arroz.getEstoque());
        Venda venda3 = new Venda(macarrao, 1);
        venda3.realizarVenda();
        assertEquals(9, macarrao.getEstoque());

    }
    //Testar calcular total de venda quando o preço do produto for alterado antes da venda.
    @Test
    @DisplayName("Testar calcular total de venda quando o preço do produto for alterado antes da venda.")
   void CalcularValorTotalQuandoPrecoForAlteradoAntesDaVenda(){
        Venda venda = new Venda(produtos, 2);
        produtos.setPreco(4.99);
        venda.realizarVenda();
        assertEquals(9.98,venda.getTotalVenda());
    }
    //Testar comportamento da venda quando o estoque inicial é zero.
    @Test
    @DisplayName("Testar comportamento da venda quando o estoque inicial é zero.")
    void VendaComEstoqueInicialZero(){
        Produto produtoZerado = new Produto("Miojo", 0.99, 0);
        Venda venda = new Venda(produtoZerado, 2);
        produtos.setPreco(4.99);
        assertFalse(venda.realizarVenda());
    }
    //Testar aumento do estoque após uma reposição e verificar se a venda é bem-sucedida posteriormente.
    @Test
    @DisplayName("Testar aumento do estoque após uma reposição e verificar se a venda é bem-sucedida posteriormente.")
    void AumentarEsstoqueETestarVenda(){
        produtos.setEstoque(produtos.getEstoque()+5);
        Venda venda = new Venda(produtos,3);
        assertTrue(venda.realizarVenda());
    }

    @AfterEach
    void resetAll(){
        produtos.setEstoque(10);
        produtos.setPreco(5.99);
        System.out.println("Resetando estoque... "+produtos.getEstoque());
        System.out.println("Resetando Preço... "+ produtos.getPreco());
    }

}
