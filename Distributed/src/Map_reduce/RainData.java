package Map_reduce;

import java.io.*;

public class RainData implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;
    private int year;
    private Month month;

    // total mm of rain fallen
    private float mm;

    // max consecutive days of rain during month
    private int consecutiveDays;

    public RainData(String fileRow) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(fileRow));

        tokenizer.nextToken();
        year = (int)tokenizer.nval;
        tokenizer.nextToken();
        if (tokenizer.ttype == StreamTokenizer.TT_WORD)
            month = Month.valueOf(tokenizer.sval);
        else
            month = Month.valueOf((int)tokenizer.nval);
        tokenizer.nextToken();
        mm = (float)tokenizer.nval;
        tokenizer.nextToken();
        consecutiveDays = (int)tokenizer.nval;

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public float getMm() {
        return mm;
    }

    public void setMm(float mm) {
        this.mm = mm;
    }

    public int getConsecutiveDays() {
        return consecutiveDays;
    }

    public void setConsecutiveDays(int consecutiveDays) {
        this.consecutiveDays = consecutiveDays;
    }
    public String toString() {
        return(year+";"+month+";"+mm+";"+consecutiveDays);
    }

}
