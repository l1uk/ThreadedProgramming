package Counter_design;

import java.io.IOException;

public interface CounterInterface {
    void reset();
    void increment();
    int value() throws IOException;
}
