import java.io.Serial;
import java.io.Serializable;

public class NFibonacci implements Executable, Serializable {

    @Serial
    private final static long serialVersionUID = -1L;

    private int number;

    public NFibonacci(int number) {
        this.number = number;
    }

    public int getFibonacciValue(int number) {
        if (number <= 1) {
            return 0;
        } else if (number == 2) {
            return 1;
        } else {
            return getFibonacciValue(number - 1) + getFibonacciValue(number - 2);
        }
    }

    @Override
    public Object execute() {
        return getFibonacciValue(number);
    }
}