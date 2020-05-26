package helperClass;

public class Number implements Comparable<Number>{

    private Double aDouble;
    private Long aLong;

    public Number(Double aDouble) {
        this.aDouble = aDouble;
    }

    public Number(Long aLong) {
        this.aLong = aLong;
    }

    public Double getDouble() {
        return this.aDouble;
    }

    public Long getLong() {
        return this.aLong;
    }


    @Override
    public int compareTo(Number o) {
        if (aLong != null && o.getLong() != null) {
            if (aLong.equals(o.getLong()))
                return 0;
        } else if (aDouble != null && o.getDouble() != null) {
            if (aDouble.equals(o.getDouble()))
                return 0;
        }
        double thisDouble = aDouble == null ? (double) aLong : aDouble;
        double oDouble = o.getDouble() == null ? (double) o.getLong() : o.getDouble();
        if (thisDouble - oDouble > 0)
            return 1;
        else if (thisDouble - oDouble < 0)
            return -1;
        return 0;
    }
}
