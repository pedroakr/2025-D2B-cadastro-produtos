import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static ArrayList<String> produtos = new ArrayList<>();
    static ArrayList<Double> precos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            while(true) {
                System.out.print("DIGITE A OPÇÃO\n1 - Cadastrar produto (nome e preço)\n2 - Listar produtos e preços\n3 - Produto mais caro\n4 - Total e média dos preços\n5 - Produtos com preço acima da media\n6 - Remover produto\n7 - Atualizar preço\n0 - Sair\n");
                System.out.print("\nOpção: ");

                int menu = scan.nextInt();
                if (menu == 0) break;
                switch (menu) {
                    case 1:
                        System.out.println("--------------- CADASTRO DE PRODUTO -------------\n");
                        cadastrarProduto(scan);
                        System.out.println("\n-------------------------------------------------");
                        break;

                    case 2:
                        System.out.println("---------- LISTA DE PRODUTOS E PREÇOS -----------\n");
                        listarItens();
                        System.out.println("\n-------------------------------------------------");
                        break;

                    case 3:
                        System.out.println("------------------ MAIOR PREÇO ------------------\n");
                        maisCaro();
                        System.out.println("\n-------------------------------------------------");
                        break;

                    case 4:
                        System.out.println("------------ TOTAL E MEDIA DE PREÇOS ------------\n");
                        System.out.printf("Total: %.2f %n", somaEMediaPreco());                   // Desafio Opcional: Exibir o total em dinheiro de todos os produtos cadastrados
                        System.out.printf("Media: %.2f %n", somaEMediaPreco()/precos.size());     // Media preço
                        System.out.println("\n-------------------------------------------------");
                        break;

                    case 5:
                        System.out.println("----------- PRODUTO(S) ACIMA DA MEDIA -----------\n");
                        produtoAcimaMedia();
                        System.out.println("\n-------------------------------------------------");
                        break;

                    case 6:
                        System.out.println("---------------- REMOVER PRODUTO ----------------\n");
                        removerProduto(scan);
                        System.out.println("\n-------------------------------------------------");
                        break;

                    case 7:
                        System.out.println("------------- ATUALIZAÇÃO DE PREÇO --------------\n");
                        atualizarPreco(scan);
                        System.out.println("\n-------------------------------------------------");
                        break;

                    default:
                        System.out.println("\nEntrada inválida");
                        break;
                }
            }
        }catch (InputMismatchException e){
                System.out.println("\nEntrada inválida");
        }
    }

    public static void cadastrarProduto(Scanner scan) {
        try {
            scan.nextLine();
            System.out.print("Produto: ");
            String produto = scan.nextLine().toUpperCase().trim();

            if (!produtos.contains(produto)){
                System.out.print("Preço: ");
                double preco = scan.nextDouble();
                if (preco > 0) {
                    produtos.add(produto);
                    precos.add(preco);
                    System.out.println("\nProduto Cadastrado!");
                } else System.out.println("\nPreço Inválido!");
            }else System.out.println("\nProduto já cadastrado.");     // Desafio opcional: Não permitir nomes de produtos repetidos.

        } catch (InputMismatchException e) {
            System.out.println("\nEntrada inválida.");
        }
    }

    public static void listarItens() {
        try {

            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto cadastrado.");
            }

            for (String itens : produtos) {
                int index= produtos.indexOf(itens);
                int numeracao = index + 1;
                System.out.println(numeracao+ " - " + itens + " R$ " + precos.get(index));
            }
        } catch (Exception e){
            System.out.println("Erro: " +e);
        }
    }

    public static void maisCaro(){
        try {
            double valorMaior = 0;

            if (precos.isEmpty()) {
                System.out.println("Nenhum produto cadastrado.");
            }

            for (double item : precos){
                if (valorMaior < item){
                    valorMaior = item;
                }
            }
            int index = precos.indexOf(valorMaior);
            int numeracao = index + 1;
            System.out.println(numeracao+ " - " +produtos.get(index)+ " R$ " +valorMaior);
        } catch (Exception e){
            System.out.println("Erro: " +e);
        }
    }

    public static double somaEMediaPreco() {
        try {
            double somaValores = 0;
            if (precos.isEmpty()) {
                System.out.println("Nenhum produto cadastrado.");
                return 0.0;
            }

            for (double item : precos) {
                somaValores += item;
            }
            return somaValores;

        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return 0.0;
        }
    }

    public static void produtoAcimaMedia(){
        try {
            double media = somaEMediaPreco()/precos.size();
            for (double item : precos) {
                if ( media < item) {
                    int index = precos.indexOf(item);
                    int numeracao = index + 1;
                    System.out.println(numeracao+ " - " +produtos.get(index)+ " R$ " +item);
                }
            }
        } catch (Exception e){
            System.out.println("Erro: " +e);
        }
    }

    public static void removerProduto(Scanner scan){
        try {
            listarItens();
            if (!produtos.isEmpty() ) {
                System.out.print("\nProduto (Nome completo): ");
                scan.nextLine();
                String produto = scan.nextLine().toUpperCase().trim();

                int index = produtos.indexOf(produto);

                produtos.remove(index);
                precos.remove(index);

                System.out.println("\n"+produto+" removido com sucesso!");
            }
        } catch (InputMismatchException e){
            System.out.println("\nValor inválido");
        } catch (IndexOutOfBoundsException e){
            System.out.println("\nProduto não encontrado.");
        }
    }

    public static void atualizarPreco(Scanner scan){
        try {
            listarItens();
            if (!produtos.isEmpty()){
                System.out.print("\nProduto (Nome completo): ");
                scan.nextLine();
                String produto = scan.nextLine().toUpperCase().trim();

                boolean produtoEncontrado = false;

                for (String item : produtos){
                    int index = produtos.indexOf(item);
                    if (produtos.get(index).equalsIgnoreCase(produto)){
                        System.out.print("Novo preço: ");
                        double preco = scan.nextDouble();
                        precos.set(index, preco);
                        produtoEncontrado = true;
                        System.out.println("\nPreço atualizado com sucesso!");
                    }
                } if (!produtoEncontrado) System.out.println("Produto não encontrado.");
            }
        } catch (Exception e){
            System.out.printf("Erro: " +e);
        }
    }
}


