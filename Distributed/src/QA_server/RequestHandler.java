package QA_server;

import tcp_server.BasicRequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RequestHandler extends BasicRequestHandler {
    private final Random rand = new Random();


    List<Question> questions;

    private ServerSocket serverSocket;

    public RequestHandler(Socket incomingConnection) throws IOException {
        super(incomingConnection);
        this.questions = this.loadFile();
    }


    private List loadFile() throws IOException {
        List<Question> result = new ArrayList<>();
        File f = new File("Files/questions.txt");
        BufferedReader r = new BufferedReader(new FileReader(f));
        while (true) {
            String q = r.readLine();
            String a = r.readLine();
            if (q == null || a == null)
                break;
            result.add(new Question(q, a));
        }
        return result;
    }


    public void serve(BufferedReader in, PrintWriter out) throws IOException {
        String another;
        do {
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

    private Question nextQuestion() {
        int index = rand.nextInt(questions.size());
        return questions.get(index);
    }
}
