public class Istanziamento {

    public static final int NUM_CLIENT = 100;

    public static void main(String args[]) {

        Nastro n = new NastroImpl();
        for( int i = 0 ; i < NUM_CLIENT ; i++ ){
            new Thread(new Client(n)).start();
        }

    }
}
