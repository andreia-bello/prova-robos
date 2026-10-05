
public class Robo {

    private int codigo;
    private String nome;
    private int ataque;
    private int defesa;
    private int energia;
    private int vitorias;
    private int derrotas;
    private int pontos;
    private int combates;

    public static final int energia_min = 30;

    /*
    O código deverá ser positivo e exclusivo; o nome não poderá estar vazio; o ataque deverá estar entre 10 e 30; 
    e a defesa, entre 0 e 20. Cada robô começará com 100 de energia e os demais indicadores zerados.
    Realizar um combate: possuir pelo menos 30 de energia
    
    */

    public Robo(int codigo, String nome, int ataque, int defesa) {
        this.codigo = codigo;
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;
        this.energia = 100;
        this.vitorias = 0;
        this.derrotas = 0;
        this.pontos = 0;
        this.combates = 0;
    }

    public String situacao() {
        if (energia >= energia_min) {
           return "Disponível";
        } else {
            return "Em recuperação";
        }
    }


    public void consultarRobos() {
        System.out.println(
            "Nome: " + this.nome + " | " + 
            "Ataque: " + this.ataque + " | " + 
            "Defesa: " + this.defesa +" | " + 
            "Energia: " + this.energia + " | " + 
            "Vitórias: " + this.vitorias + " | " + 
            "Derrotas: " + this.derrotas + " | " +
            "Pontos: " + this.pontos + " | " +
            "Combates: " + this.combates + " | " +
            "Situação: " + situacao() );
    }

    public void receberDano(int dano) {
        energia = energia - dano;
        if (energia < 0) {
            energia = 0;
        }
    }

    public void recuperarEnergia(int quantidade) {
        energia = energia + quantidade;
        if (energia > 100) {
            energia = 100;
        }
    }

    public void gastarPontos(int quantidade) {
        pontos -= quantidade;
    }

    public void registrarVitoria() {
        vitorias++;
        pontos += 3;
        combates++;
    }

    public void registrarDerrota() {
        derrotas++;
        combates++;
    }

    public void registrarEmpate() {
        pontos++;
        combates++;
    }

    public double calculoAproveitamento() {
        if (combates == 0) {
            return 0;
        } else {
            double aproveitamento = (vitorias / combates) * 100;
            return aproveitamento;
        }
    }

    public int getPontos() { 
        return pontos; 
    }

    public int getCodigo() { 
        return codigo; 
    }

    public int getEnergia() { 
        return energia; 
    }

    public String getNome() {
        return nome;
    }

}

