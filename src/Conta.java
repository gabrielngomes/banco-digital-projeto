import java.text.DecimalFormat;
import java.util.Random;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int sequencial = 1;
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = sequencial++;
        this.cliente = cliente;
        gerarCartao();
    }

    @Override
    public void sacar(double valor) {
        saldo -= valor;
    }

    @Override
    public void depositar(double valor) {
        this.saldo += valor;
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

    @Override
    public void gerarCartao() {
        Random random = new Random();
        String numeroCartao = "";

        for (int i = 0; i < 4; i++) {
            numeroCartao += new DecimalFormat("0000").format(random.nextInt(9999));
            numeroCartao += " ";
        }

        System.out.println("Parabéns pela abertura da Conta!\n O número do seu novo cartão é: " + numeroCartao);

    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {

        System.out.printf("Titular: %s\n", this.cliente.getNome());
        System.out.printf("Agência: %s\n", this.agencia);
        System.out.printf("Número: %s\n", this.numero);
        System.out.printf("Saldo: %s\n", this.saldo);
    }
}
