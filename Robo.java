public abstract class Robo {

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
    public static final int ENERGIA_MAX = 100;

    public Robo(int codigo, String nome, int ataque, int defesa) throws Exception {

        if (codigo <= 0) {
            throw new Exception("O código deve ser um número positivo.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome deve ser preenchido.");
        }
        if (ataque < 10 || ataque > 30) {
            throw new Exception("O ataque deve estar entre 10 e 30.");
        }
        if (defesa < 0 || defesa > 20) {
            throw new Exception("A defesa deve estar entre 0 e 20.");
        }

        this.codigo = codigo;
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;
        this.energia = ENERGIA_MAX;
        this.vitorias = 0;
        this.derrotas = 0;
        this.pontos = 0;
        this.combates = 0;
    }


	public int getAtaque() {
        return this.ataque;
    }

    public int getDefesa() {
        return this.defesa;
    }

    public abstract String getTipo();

    // **disponível** quando possuir pelo menos 30 de energia ou **em recuperação** quando possuir menos de 30.
    public String situacao() {
        if (this.energia >= ENERGIA_MIN) {
           return "Disponível";
        } else {
            return "Em recuperação";
        }
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
        if (dano < 0) {
            return; 
        }
        energia = energia - dano;
        if (energia < 0) {
            energia = 0;
        }
    }

    public void recuperarEnergia(int quantidade) throws Exception {
        if (quantidade <= 0 || quantidade % 10 != 0) {
            throw new Exception("A quantidade deve ser um número positivo e múltiplo de 10.");
        }

        int custo = quantidade / 10;

        if (this.pontos < custo) {
            throw new Exception("Pontos insuficientes. Custo: " + custo + " | Pontos: " + this.pontos);
        }
        if (this.energia + quantidade > ENERGIA_MAX) {
            throw new Exception("A energia final não pode ultrapassar " + ENERGIA_MAX + ".");
        }

        // só chega aqui se TODAS as condições passaram
        this.pontos -= custo;
        this.energia += quantidade;
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

    // Ataque usado no cálculo do golpe, não altera o atributo ataque)
    public int getAtaqueEfetivo() {
        return this.ataque;
    }

    // Ajuste final no dano que ESTE robô vai receber
    public int ajustarDanoRecebido(int dano) {
        return dano;
    }

    // Dano que ESTE robô (agressor) causa no alvo na rodada (1 a 5)
    public int calcularDano(Robo alvo, int rodada) {
        int dano = this.getAtaqueEfetivo() - alvo.getDefesa();  // 1) ataque c/ modificador - defesa

        if (dano < 5) {                                         // 2) dano mínimo original
            dano = 5;
        }

        if (rodada % 2 == 0) {                                  // 3) bônus das rodadas pares
            dano += 5;
        }

        return alvo.ajustarDanoRecebido(dano);                  // 4) redução do defensivo
    }

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