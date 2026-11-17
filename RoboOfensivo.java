public class RoboOfensivo extends Robo {

    public RoboOfensivo(int codigo, String nome, int ataque, int defesa) throws Exception {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public int getAtaqueEfetivo() {
        return getAtaque() + 5;   // só no cálculo, o atributo continua igual
    }
}
