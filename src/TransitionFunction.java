import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TransitionFunction<AlphabetType> {

    private class DomainPair {
        private final State state;
        private final AlphabetType letter;

        private DomainPair(State state, AlphabetType letter) {
            this.state = state;
            this.letter = letter;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instanceof TransitionFunction<?>.DomainPair)) {
                return false;
            }

            DomainPair dp = DomainPair.class.cast(o);

            return this.state.equals(dp.state) &&
                    this.letter.equals(dp.letter);
        }

        @Override
        public int hashCode() {
            return 2 ^ state.hashCode() * 3 ^ letter.hashCode();
        }

    }
    private Map<DomainPair, State> mapping;

    public TransitionFunction() {
        this.mapping = new HashMap<>();
    }

    public void addRule(State state, AlphabetType letter, State result) {
        mapping.put(new DomainPair(state, letter), result);
    }

    public Optional<State> get(State state, AlphabetType letter) {
        DomainPair dp = new DomainPair(state, letter);

        if (!mapping.containsKey(dp)) {
            return Optional.empty();
        }

        return Optional.of(mapping.get(dp));
    }
}
