package RMI_task_observer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pi implements Task<BigDecimal>, Serializable {
    private static final long serialVersionUID = 1;
    private final int digits;

    public Pi(int digits) {
        this.digits = digits;
    }

    public BigDecimal execute() {
        return computePi(digits);
    }

    private static BigDecimal computePi(int digits) {
        int scale = digits + 5;
        final BigDecimal FOUR = BigDecimal.valueOf(4);
        BigDecimal pi =
                arctanOfReciprocal(5, scale)
                        .multiply(FOUR)
                        .subtract(arctanOfReciprocal(239, scale))
                        .multiply(FOUR);
        return pi.setScale(digits, RoundingMode.HALF_UP);
    }

    private static BigDecimal arctanOfReciprocal(int reciprocal, int scale) {
        final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
        BigDecimal x = BigDecimal.valueOf(reciprocal);
        BigDecimal x2 = x.pow(2);
        BigDecimal numerator =
                BigDecimal.ONE.divide(x, scale, ROUNDING_MODE);
        BigDecimal result = numerator;
        BigDecimal term;
        int i = 1;
        do {
            numerator = numerator.divide(x2, scale, ROUNDING_MODE);
            BigDecimal denominator = BigDecimal.valueOf(2 * i + 1);
            term = numerator.divide(denominator, scale, ROUNDING_MODE);
            if (i % 2 == 0) {
                result = result.add(term);
            } else {
                result = result.subtract(term);
            }
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);
        return result;
    }

}