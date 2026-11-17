public class RoboDefensivo extends Robo {

    public RoboDefensivo(int codigo, String nome, int ataque, int defesa) throws Exception {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public int ajustarDanoRecebido(int dano) {
        return Math.max(2, dano - 3);   // reduz 3, mas nunca abaixo de 2
    }
    
}
