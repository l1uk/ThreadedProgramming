// open rain.txt file, count the years and for every year spawn a thread that finds the month with the most rain (MAP)
// then, find the global maximum (REDUCE)


package Map_reduce;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class ClientMap {

    private static final String address = "localhost";

    public static String fileName = "Files" + File.separator + "rain.txt";

    private static File rainFile;


    // List that contains for each entry an array of 12 RainData objects (entry = year)
    private static List<RainData[]> rainDataPerYear = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        rainFile = new File(fileName);
        parseFile();

        ClientReduce reducer = new ClientReduce(InetAddress.getByName(address), Server.PORT);
        CyclicBarrier barrier = new CyclicBarrier(rainDataPerYear.size(), reducer);


        for (RainData[] rainData : rainDataPerYear) {
            new Thread(
                    new ClientThread(InetAddress.getByName(address), Server.PORT, rainData, barrier, reducer)
            ).start();
        }


    }

    private static void parseFile() throws IOException {
        RainData[] yearData = new RainData[12];
        BufferedReader in = new BufferedReader(new FileReader(rainFile));
        int i = 0;
        while (in.ready()) {
            yearData[i] = new RainData(in.readLine());
            i++;
            if (i == 12) {
                i = 0;
                rainDataPerYear.add(yearData);
                yearData = new RainData[12];
            }
        }
    }
}
