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
            System.out.println(" (4) Escolha de Robôs para Combate");
            System.out.println(" (5) Executar Combate");
            System.out.println(" (6) Recuperar Energia");
            System.out.println(" (7) Sair");

            try {
                escolha = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, informe um número.");
                break;
            }

            switch (escolha) {
                case 1: { // (1) Cadastrar robô
                    // NOVO: pergunta o tipo
                    System.out.println("Tipo do robô: (1) Ofensivo  (2) Defensivo  (3) Equilibrado");
                    int tipo = scanner.nextInt();

                    if (tipo < 1 || tipo > 3) {
                        System.out.println("Tipo inválido.");
                        break;
                    }

                    System.out.println("Informe o código do robô");
                    int codigo = scanner.nextInt();

                    // O código deverá ser positivo e exclusivo;
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

                    String limpaEnter = scanner.nextLine();

                    //o nome não poderá estar vazio
                    System.out.println("Informe o nome do robô");
                    String nome = scanner.nextLine();

                    if (nome.isEmpty()) {
                        System.out.println("O nome não pode ser vazio.");
                        break;
                    }

                    //  o ataque deverá estar entre 10 e 30
                    System.out.println("Ataque (10 a 30)");
                    int ataque = scanner.nextInt();

                    if (ataque < 10 || ataque > 30) {
                        System.out.println("O ataque deve estar entre 10 e 30.");
                        break;
                    }

                    //e a defesa, entre 0 e 20
                    System.out.println("Defesa (0 a 20)");
                    int defesa = scanner.nextInt();

                    if (defesa < 0 || defesa > 20) {
                        System.out.println("A defesa deve estar entre 0 e 20.");
                        break;
                    }

                    // ===== SUBSTITUI o try antigo =====
                    try {
                        Robo robo = null;

                        switch (tipo) {

                            case 1: 
                                robo = new RoboOfensivo(codigo, nome, ataque, defesa); 
                            break;

                            case 2: 
                                robo = new RoboDefensivo(codigo, nome, ataque, defesa); 
                            break;

                            case 3: 
                                robo = new RoboEquilibrado(codigo, nome, ataque, defesa); 
                            break;
                        }

                        robos.add(robo);
                        System.out.println("Robô cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                break;
                }

                case 2:{ // (2) Buscar robô por código
                    try {
                        Robo robo = buscaRobo(scanner, robos, 0);
                        robo.consultarRobos();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    
                break;
                }

                case 3:{ //(3) Listar robôs
                    for (Robo robo : robos) {
                        robo.consultarRobos();
                    }
                break;
                }

                /*
                Solicitar os códigos de dois robôs distintos. Ambos deverão existir e possuir pelo menos 30 de energia
                */
                case 4: { // (4) Realizar combate (Etapa Escolha de robôs)

                    if (robos.size() < 2) {
                        System.out.println("Impossível realizar combate. É necessário ter pelo menos dois robôs cadastrados." );
                        break;
                    }

                    robo1 = null;
                    robo2 = null;

                    try {
                        robo1 = buscaRobo(scanner, robos, 1);
                        robo2 = buscaRobo(scanner, robos, 2);

                        if (robo1.getCodigo() == robo2.getCodigo()) {
                            throw new Exception( "Impossível realizar combate. Os códigos dos robôs devem ser diferentes.");
                        }

                        //possuir pelo menos 30 de energia
                        if (robo1.getEnergia() < Robo.ENERGIA_MIN || robo2.getEnergia() < Robo.ENERGIA_MIN) {

                            throw new Exception("Os dois robôs precisam possuir pelo menos 30 de energia.");
                        }
                        
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Robôs Prontos para inciar combate.");
                    System.out.println(robo1.getNome() + " x " + robo2.getNome());
                    

                    break;
                }
                
                case 5:{ //(5) Executar Combate

                    if (robo1 == null || robo2 == null) {
                        System.out.println("Escolha os robôs primeiro (opção 4).");
                        break;
                    }

                    /* O combate terá até cinco rodadas
                    O robô com menos pontos atacará primeiro em todas as rodadas daquele combate. 
                    Em caso de empate, começará o de menor código. Essa ordem será definida antes do início da luta.
                    */
                    Robo roboPrimeiro = null;
                    Robo roboSegundo = null;

                    if (robo1.getPontos() < robo2.getPontos()) {
                        roboPrimeiro = robo1;
                        roboSegundo = robo2;
                    } else if (robo1.getPontos() > robo2.getPontos()) {
                        roboPrimeiro = robo2;
                        roboSegundo = robo1;
                    } else {

                        if (robo1.getCodigo() < robo2.getCodigo()) {
                            roboPrimeiro = robo1;
                            roboSegundo = robo2;
                        } else {
                            roboPrimeiro = robo2;
                            roboSegundo = robo1;
                        }

                    }

                    System.out.println("Começa atacando: " + roboPrimeiro.getNome());

                    for (int i = 0; i < 5; i++) {

                        System.out.println( (i + 1) + "ª Rodada");

                        int dano = roboPrimeiro.getAtaque() - roboSegundo.getDefesa();

                        // dano mínimo de 5
                        if (dano < 5) {
                            dano = 5;
                        }

                        if ( (i + 1) % 2 == 0) {
                            dano += 5;
                        }

                        roboSegundo.receberDano(dano);

                        System.out.println(roboPrimeiro.getNome() + " causou " + dano + " de dano em "+ roboSegundo.getNome() + " (energia: " + roboSegundo.getEnergia() + ")");

                        if (roboSegundo.getEnergia() == 0) {
                            break; 
                        }

                        // Ataque do segundo
                        dano = roboSegundo.getAtaque() - roboPrimeiro.getDefesa();

                        if (i % 2 == 0) {
                            dano += 5;
                        }

                        roboPrimeiro.receberDano(dano);

                        System.out.println(roboSegundo.getNome() + " causou " + dano + " de dano em "+ roboPrimeiro.getNome() + " (energia: " + roboPrimeiro.getEnergia() + ")");

                        if (roboPrimeiro.getEnergia() == 0) {
                            break;
                        }

                    }

                    /*
                    Caso ambos sobrevivam às cinco rodadas, vencerá aquele com mais energia restante. Se as energias forem iguais, haverá empate.
                    O vencedor receberá três pontos e uma vitória; o perdedor receberá uma derrota. Em caso de empate, cada participante receberá um ponto, 
                    sem alteração nas vitórias e derrotas.
                    A energia restante será mantida para os próximos combates.
                    */

                    if (roboPrimeiro.getEnergia() == roboSegundo.getEnergia()) {
                        roboPrimeiro.registrarEmpate();
                        roboSegundo.registrarEmpate();
                        System.out.println("Empate!");
                    } else if (roboPrimeiro.getEnergia() > roboSegundo.getEnergia()) {
                        roboPrimeiro.registrarVitoria();
                        roboSegundo.registrarDerrota();
                        System.out.println("Vencedor: " + roboPrimeiro.getNome());
                    } else {
                        roboSegundo.registrarVitoria();
                        roboPrimeiro.registrarDerrota();
                        System.out.println("Vencedor: " + roboSegundo.getNome());
                    }

                    // limpa a seleção pro próximo combate
                    robo1 = null;
                    robo2 = null;

                break;
                }

                case 6: { // (6) Recuperar Energia

                    /*
                    **Recuperar energia:** solicitar o código do robô e a quantidade de energia desejada, que deverá ser um número positivo e múltiplo de 10. 
                    Cada bloco de 10 de energia custará um ponto do campeonato. A operação somente será aceita se o robô tiver pontos suficientes e a energia 
                    final não ultrapassar 100. Se alguma condição falhar, a operação inteira deverá ser recusada.
                    */
                    try {
                        Robo robo = buscaRobo(scanner, robos, 0);

                        System.out.println("Informe a quantidade de energia: ");
                        int qtdEnergia = scanner.nextInt();

                        // positivo e múltiplo de 10
                        if (qtdEnergia <= 0 || qtdEnergia % 10 != 0) {
                            throw new Exception("A quantidade deve ser um número positivo e múltiplo de 10.");
                        }

                        // cada bloco de 10 custa 1 ponto
                        int custo = qtdEnergia / 10;

                        // A operação somente será aceita se o robô tiver pontos suficientes
                        if (robo.getPontos() < custo) {
                            throw new Exception("Pontos insuficientes. Custo: " + custo + " | Pontos: " + robo.getPontos());
                        }

                        // energia final não pode passar de 100
                        if ((robo.getEnergia() + qtdEnergia) > 100) {
                            throw new Exception("A energia final não pode ultrapassar 100.");
                        }

                        // continua

                        
                    } catch (InputMismatchException e) {
                        System.out.println("Entrada inválida, informe um número.");
                        scanner.nextLine(); 
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }
                
                case 7:{
                    System.out.println("Saindo...!");
                break;
                }
            
                default:
                    System.out.println("Entrada inválida");
                break;
            }
       } while (escolha != 7);
        
        scanner.close();
    }

    public static Robo buscaRobo(Scanner scanner, ArrayList<Robo> robos, int numero) throws Exception {
        int cdg;
        try {
            System.out.println("Informe o código do robô " + (numero > 0 ? numero + " " : "") + "que deseja buscar:");
            cdg = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new Exception("Entrada inválida, informe um número."); 
        }
        Robo roboEncontrado = null;

        for (Robo robo : robos) {

            if (robo.getCodigo() == cdg) {
                roboEncontrado = robo;
                break;
            } 
        }

        if (roboEncontrado == null) {
            throw new Exception("Código do robô não encontrado.");
        }

        return roboEncontrado;
    }
}
