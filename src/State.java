public class State {
    private static int counter = 0;
    private final int identifier;

    public State() {

        this.identifier = counter;
        counter++;

    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof State s)) {
            return false;
        }

        return this.identifier == s.identifier;
    }

    @Override
    public int hashCode() {
        return this.identifier;
    }

    @Override
    public String toString() {

        return String.format("q%d", this.identifier);

    }
}
