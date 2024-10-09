package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {
    private Produto product;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("Iniciando testes...");

    }

    //Instanciando um produto para fazer testes de atualização mais facil
    @BeforeEach
    void setup(){
        this.product = new Produto("Feijão", 7.99, 8);
    }


    //Testar criação de produto com valores válidos.
    @Test
    @DisplayName("Testar criação de produto com valores válidos")
    void CriarProduto() {
        this.product= new Produto("Arroz", 5.99, 10);
        assertEquals("Arroz", this.product.getNome());
        assertEquals(5.99, this.product.getPreco());
    }

    //Testar criação de produto com preço negativo (deve ser inválido).
    @Test
    @DisplayName("Testar criação de produto com preço negativo (deve ser inválido)")
    void CriarProdutoPrecoInvalido(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Arroz", -5.99, 10);
        });
    };

    //Testar criação de produto com estoque negativo (deve ser inválido).
    @Test
    @DisplayName("estar criação de produto com estoque negativo (deve ser inválido)")
    void  CriarProdutoComEstoqueNegativo(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Produto("Arroz", 5.99, -10);
        });
    }

    //Testar alteração do preço do produto para um valor válido.
    @Test
    @DisplayName("Testar alteração do preço do produto para um valor válido.")
    void AtualizarNomeComValorValid(){
        this.product.setNome("Macarrão");
        assertEquals("Macarrão", this.product.getNome());
    }

    //Testar alteração do nome do produto para um valor válido.
    @Test
    @DisplayName("Testar alteração do nome do produto para um valor válido.")
    void AtualizarProdutoComValorValido(){
        this.product.setPreco(6.49);
        assertEquals(6.49, this.product.getPreco());
    }
    //Testar alteração do estoque para um valor positivo.
    @Test
    @DisplayName("Testar alteração do estoque para um valor positivo.")
    void AtualizarProdutoComEstoqueValido(){
        this.product.setEstoque(10);
        assertEquals(10, this.product.getEstoque());
    }
    //Testar alteração do preço do produto para um valor negativo (deve falhar).
    @Test
    @DisplayName("Testar alteração do preço do produto para um valor negativo (deve falhar)")
    void AtualizarProdutoComValorNegativo(){
        assertThrows(IllegalArgumentException.class, ()->{
            this.product.setPreco(-6.49);
        });
    }


}