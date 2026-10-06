
public class Robo {

    // protegendo as regras de alteração de energia e indicadores
    private int codigo;
    private String nome;
    private int ataque;
    private int defesa;
    private int energia;
    private int vitorias;
    private int derrotas;
    private int pontos;
    private int combates;

    public static final int ENERGIA_MIN = 30;

    /*
    O código deverá ser positivo e exclusivo; o nome não poderá estar vazio; o ataque deverá estar entre 10 e 30; 
    e a defesa, entre 0 e 20. Cada robô começará com 100 de energia e os demais indicadores zerados.
    Realizar um combate: possuir pelo menos 30 de energia
    
    */

    public Robo(int codigo, String nome, int ataque, int defesa) throws Exception {
        this.codigo = codigo;
        this.nome = nome;
        this.setAtaque(ataque);
        this.defesa = defesa;
        this.energia = 100;
        this.vitorias = 0;
        this.derrotas = 0;
        this.pontos = 0;
        this.combates = 0;
    }

    public void setAtaque(int ataque) throws Exception {
        if (ataque < 10 || ataque > 30) {
            throw new Exception("Ataque não pode ser maior que mil");
        }

        this.ataque = ataque;
    }

    public int getAtaque() {
        return this.ataque;
    }

    public int getDefesa() {
        return this.defesa;
    }

    // **disponível** quando possuir pelo menos 30 de energia ou **em recuperação** quando possuir menos de 30.
    public String situacao() {
        if (this.energia >= ENERGIA_MIN) {
           return "Disponível";
        } else {
            return "Em recuperação";
        }
    }

    public void adicionarCombate() {
        this.combates++;
    }

    public void consultarRobos() {
        System.out.println(
            "Nome: " + this.nome + " | " + 
            "Ataque: " + this.getAtaque() + " | " + 
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
            double aproveitamento = ((double) vitorias / combates) * 100;
            return aproveitamento;
        }
    }

    // public 

    // GETS
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

