package Map_reduce;

public enum Month {

    JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC, ERR;

    public static Month valueOf(int monthValue) {
        return switch (monthValue) {
            case 1 -> JAN;
            case 2 -> FEB;
            case 3 -> MAR;
            case 4 -> APR;
            case 5 -> MAY;
            case 6 -> JUN;
            case 7 -> JUL;
            case 8 -> AUG;
            case 9 -> SEP;
            case 10 -> OCT;
            case 11 -> NOV;
            case 12 -> DEC;
            default -> ERR;
        };
    }
}


