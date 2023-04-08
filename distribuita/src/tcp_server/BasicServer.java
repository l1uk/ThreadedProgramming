package tcp_server;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicServer implements Runnable {

    ServerSocket listener;

    Class reqHandlerClass;

    public BasicServer(ServerSocket listener, Class reqHandlerClass) {
        this.listener = listener;
        this.reqHandlerClass = reqHandlerClass;
    }

    @Override
    public void run() {
        System.out.println("Server " + Thread.currentThread().getId() + " Listening on " + listener.getLocalPort());
        while (true) {
            Socket incomingConnection = null;
            try {
                incomingConnection = listener.accept();
                System.out.println("### SERVER ID= "+ Thread.currentThread().getId()+ " ### Received connection request");
                BasicRequestHandler a = (BasicRequestHandler) reqHandlerClass.getConstructor(Socket.class).newInstance(incomingConnection);
                new Thread(a).start();
                System.out.println("### SERVER ### Replied");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

}
