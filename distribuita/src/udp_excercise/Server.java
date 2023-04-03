package udp_excercise;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Server {
    private final Random rand = new Random();


    List<Question> questions;
    Integer port = 7776;
    public Server(List<Question> questions) {
        this.questions = questions;
    }
    private void serve() throws IOException {
        while(true){
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server up and running...");
                while (true) {
                    try (
                            Socket socket = serverSocket.accept();
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream())
                            );
                            PrintWriter out = new PrintWriter(
                                    new BufferedWriter(
                                            new OutputStreamWriter(
                                                    socket.getOutputStream()
                                            )
                                    ),
                                    true // Auto-flush
                            )
                    ) {
                        serveClient(in, out);
                    }
                }
            }
        }
    }
    private void serveClient(BufferedReader in, PrintWriter out) throws IOException {
        String another;
        do{
            Question next = nextQuestion();
            out.println(next.getQuestion());

            String ans = in.readLine();
            String correct = next.getAnswer();
            if (correct.equalsIgnoreCase(ans)) {
                out.println("That's correct! Want another? (y/n)");
            } else {
                out.println(
                        "Wrong, the correct answer is " + correct
                                + ". Want another? (y/n)"
                );
            }
            another = in.readLine();
        } while ("y".equalsIgnoreCase(another));
        out.println("END");
    }
    private Question nextQuestion(){
        int index = rand.nextInt(questions.size());
        return questions.get(index);
    }
    private static List loadFile(String fileName) throws IOException {
        List<Question> result = new ArrayList<>();
        File f = new File("questions.txt");
        BufferedReader r = new BufferedReader(new FileReader(f));
        while(true){
            String q = r.readLine();
            String a = r.readLine();
            if(q==null || a == null)
                break;
            result.add(new Question(q,a));
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        new Server(loadFile("questions.txt")).serve();
    }
}
