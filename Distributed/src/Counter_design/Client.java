package Counter_design;

import java.io.IOException;

public class Client implements Runnable{

    @Override
    public void run() {
        try(Client_proxy proxy = new Client_proxy();){
            proxy.reset();
            Thread.sleep(400);
            for( int i = 0 ; i < 1000 ; i+=1 ){
                proxy.increment();
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +", Value: " + proxy.value());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
