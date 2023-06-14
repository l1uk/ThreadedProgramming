package ES1;

import java.io.Serializable;

public class Priority implements Serializable {
    public static final int MIN = 1;
    public static final int MAX = 5;
    public Integer value;

    public Priority(int value) {
        if (value >= MIN && value <= MAX)
            this.value = value;
        else this.value = MIN;
    }
}
