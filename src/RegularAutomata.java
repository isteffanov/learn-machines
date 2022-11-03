import java.util.*;

public class RegularAutomata<AlphabetType> {

    private final Set<State> states;
    private final Set<AlphabetType> alphabet;
    private final TransitionFunction<AlphabetType> transitionFunction;
    private final State startingState;
    private final Set<State> finalStates;

    public RegularAutomata(
            Set<State> states,
            Set<AlphabetType> alphabet,
            TransitionFunction<AlphabetType> transitionFunction,
            State startingState,
            Set<State> finalStates)
    {
        assert states.containsAll(finalStates);
        assert states.contains(startingState);

        this.states = states;
        this.alphabet = alphabet;
        this.transitionFunction = transitionFunction;
        this.startingState = startingState;
        this.finalStates = finalStates;
    }

    public boolean parse(List<AlphabetType> word) {

        State currentState = startingState;
        Optional<State> nextState;

        for (AlphabetType letter : word) {
            nextState = transitionFunction.get(currentState, letter);

            if (!nextState.isPresent()) {
                return false;
            }

            currentState = nextState.get();
        }

        return finalStates.contains(currentState);
    }

    public static void main(String[] args) {
        State q1 = new State();
        State q2 = new State();
        State q3 = new State();
        State q4 = new State();

        Set<State> states = new HashSet<>();
        states.add(q1);
        states.add(q2);
        states.add(q3);
        states.add(q4);

        Set<Character> alphabet = new HashSet<>();
        alphabet.add('a');
        alphabet.add('b');

        TransitionFunction<Character> delta = new TransitionFunction<>();
        delta.addRule(q1, 'a', q3);
        delta.addRule(q3, 'b', q4);
        delta.addRule(q4, 'b', q3);
        delta.addRule(q3, 'a', q2);
        delta.addRule(q4, 'a', q2);

        Set<State> finalStates = new HashSet<>();
        finalStates.add(q4);

        RegularAutomata<Character> automata = new RegularAutomata<>(
                states,
                alphabet,
                delta,
                q1,
                finalStates
        );

        String word = new Scanner(System.in).nextLine();
        List<Character> parseWord = word.
                chars()
                .mapToObj(e -> (char) e)
                .toList();

        System.out.println(automata.parse(parseWord));
    }
}
