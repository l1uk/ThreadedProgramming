package Counter_design;

public class Clients_spawner {
    public static void main(String[] args){
        new Thread(new Client() ).start();
    }
}
