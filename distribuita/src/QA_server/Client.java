package QA_server;

import java.io.*;
import java.net.Socket;

public class Client {

    private static final String address = "localhost";

    public static void main(String[] args) throws IOException {
        try (
                Socket socket = new Socket(address, Esercizio.PORT);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())
                        ),
                        true // Auto-flush
                );
                BufferedReader userIn = new BufferedReader(
                        new InputStreamReader(System.in)
                )
        ) {
            String question;
            while ((question = in.readLine()) != null) {
                System.out.println(question);
                if (question.equalsIgnoreCase("END")) break;
                String ans = userIn.readLine();
                out.println(ans);
            }
        }
    }
}
