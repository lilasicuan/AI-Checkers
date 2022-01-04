import java.util.ArrayList;

public class ActionSequence {
    private ArrayList<Action> sequence;
    private Integer orderingValue;

    public ActionSequence(ArrayList<Action> sequence, int orderingValue) {
        this.sequence = sequence;
        this.orderingValue = orderingValue;
    }

    public ArrayList<Action> getActionSequence() {
        return sequence;
    }

    public Integer getOrderingValue() {
        return orderingValue;
    }
}
