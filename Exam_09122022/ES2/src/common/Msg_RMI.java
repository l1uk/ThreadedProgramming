package common;

import java.io.Serial;
import java.io.Serializable;

public class Msg_RMI implements Serializable {
    @Serial
    private final long SerialVersionUID = 1L;
    public int dest;
    public String txt;

    public Msg_RMI(int t, String tt) {
        dest = t;
        txt = tt;
    }
}
