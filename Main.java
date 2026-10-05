import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Robo> robos = new ArrayList<>();

        int escolha = 0;
        Robo robo1 = null;
        Robo robo2 = null;

         /*
            O código deverá ser positivo e exclusivo; o nome não poderá estar vazio; o ataque deverá estar entre 10 e 30; 
            e a defesa, entre 0 e 20. Cada robô começará com 100 de energia e os demais indicadores zerados.
            Realizar um combate: possuir pelo menos 30 de energia
        */

       do {
            System.out.println("========== CAMPEONATO DE ROBÔS ==========");
            System.out.println(" (1) Cadastrar robô");
            System.out.println(" (2) Buscar robô por código");
            System.out.println(" (3) Listar robôs");
            System.out.println(" (4) Realizar combate (Etapa Escolha de robôs)");
            System.out.println(" (5) Executar Combate");
            System.out.println(" (6) Sair");
            System.out.println("=========================================");

            try {
                escolha = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, informe um número.");
                break;
            }

            switch (escolha) {
                case 1: {
                    System.out.println("Informe o código do robô");
                    int codigo = scanner.nextInt();

                    if (codigo <= 0) {
                        System.out.println("O código deve ser um número positivo.");
                        break;
                    }

                    boolean validaCodigo = false;

                    for (Robo robo : robos) {
                        if (robo.getCodigo() == codigo) {
                        validaCodigo = true;
                        break;
                        }
                    }

                    if (validaCodigo) {
                        System.out.println("Código já cadastrado.");
                        break;
                    }

                    System.out.println("Informe o nome do robô");
                    String nome = scanner.nextLine();

                    if (nome == null ) {
                        System.out.println("O nome não pode ser vazio.");
                        break;
                    }

                    System.out.println("Ataque (10 a 30)");
                    int ataque = scanner.nextInt();

                    if (ataque < 10 || ataque > 30) {
                        System.out.println("O ataque deve estar entre 10 e 30.");
                        break;
                    }

                    System.out.println("Defesa (0 a 20)");
                    int defesa = scanner.nextInt();

                    if (defesa < 0 || defesa > 20) {
                        System.out.println("A defesa deve estar entre 0 e 20.");
                        break;
                    }

                    Robo robo = new Robo(codigo, nome, ataque, defesa);
                    robos.add(robo);
                    System.out.println("Robô cadastrado com sucesso!");

                break;
                }

                case 2:{
                    int cdg;
                    try {
                        System.out.println("Informe o código do robô que deseja buscar:");
                        cdg = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida, informe um número.");
                        break; 
                    }
                    boolean encontrou = false;

                    for (Robo robo : robos) {

                        if (robo.getCodigo() == cdg) {
                            robo.consultarRobos();
                            encontrou = true;
                            break;
                        } 
                    }

                    if (!encontrou) {
                        System.out.println("Código do robô não encontrado.");
                    }
                    
                break;
                }

                case 3:{
                    for (Robo robo : robos) {
                        robo.consultarRobos();
                    }
                break;
                }

                /*
                Solicitar os códigos de dois robôs distintos. Ambos deverão existir e possuir pelo menos 30 de energia
                */
                case 4: {

                    if (robos.size() < 2) {
                        System.out.println("Impossível realizar combate. É necessário ter pelo menos dois robôs cadastrados." );
                        break;
                    }

                    int cod1;
                    int cod2;

                    System.out.println("Código do 1º robô:");
                    cod1 = scanner.nextInt();

                    System.out.println("Código do 2º robô:");
                    cod2 = scanner.nextInt();

                    if (cod1 == cod2) {
                        System.out.println( "Impossível realizar combate. Os códigos dos robôs devem ser diferentes.");
                        break;
                    }

                    robo1 = null;
                    robo2 = null;

                    for (Robo robo : robos) {

                        if (robo.getCodigo() == cod1) {
                            robo1 = robo;
                        }

                        if (robo.getCodigo() == cod2) {
                            robo2 = robo;
                        }
                    }

                    if (robo1 == null || robo2 == null) {

                        System.out.println( "Combate não pode ser realizado.");

                        if (robo1 == null) { 
                            System.out.println("O código " + cod1 + " não foi encontrado.");
                        }

                        if (robo2 == null) { 
                            System.out.println("O código " + cod2 +" não foi encontrado.");
                        }

                        // Não deixa vazio caso não encontre os códigos
                        robo1 = null;
                        robo2 = null;

                        break;
                    }

                    if (robo1.getEnergia() < Robo.energia_min || robo2.getEnergia() < Robo.energia_min) {

                        System.out.println("Os dois robôs precisam possuir pelo menos 30 de energia.");

                        // Atribui nulo porque os robôs não podem iniciar o combate.
                        robo1 = null;
                        robo2 = null;

                        break;
                    }

                    System.out.println("Robôs selecionados com sucesso! Prontos para inciar combate.");
                    System.out.println(robo1.getNome() + " x " + robo2.getNome());

                    break;
                }
                
                case 5:{

                    /*
                    O robô com menos pontos atacará primeiro em todas as rodadas daquele combate. 
                    Em caso de empate, começará o de menor código. Essa ordem será definida antes do início da luta.
                    */
                    if (robo1 == null || robo2 == null) {
                        System.out.println("Escolha os robôs na opção 4.");
                        break;
                    }

                break;
                }

                case 6:{
                    System.out.println("Saindo...!");
                break;
                }
            
                default:
                    System.out.println("Entrada inválida");
                break;
            }
       } while (escolha != 6);
        
        scanner.close();
    }
}
