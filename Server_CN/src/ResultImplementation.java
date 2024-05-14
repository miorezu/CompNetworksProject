import java.io.Serializable;

public class ResultImplementation implements Result, Serializable {

    Object output;
    double scoreTime;

    public ResultImplementation(Object object, double time) {
        output = object;
        scoreTime = time;
    }

    public Object output() {
        return output;
    }

    public double scoreTime() {
        return scoreTime;
    }
}