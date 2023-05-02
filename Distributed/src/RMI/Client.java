package RMI;

import RMI.server.Hello;
import RMI.server.Person;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        Registry reg = LocateRegistry.getRegistry(args[0], 1099);
        Hello stub = (Hello) reg.lookup("HelloServer");
        String result = stub.sayHello();

        System.out.println("Result: " + result);

        Person p = new Person("Paolo");

        result = stub.sayHello(p);

        System.out.println("Result: " + result);
    }
}
