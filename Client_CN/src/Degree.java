import java.io.Serial;
import java.io.Serializable;

public class Degree implements Executable, Serializable {

    @Serial
    private final static long serialVersionUID = -1L;

    private int number;
    private int degree;

    public Degree(int number, int degree) {
        this.number = number;
        this.degree = degree;
    }

    @Override
    public Object execute() {
        return (long) Math.pow(number, degree);
    }
}
