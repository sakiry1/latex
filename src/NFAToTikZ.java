import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NFAToTikZ {

    public static class State {
        String name;
        boolean isInitial;
        boolean isAccepting;

        public State(String name, boolean isInitial, boolean isAccepting) {
            this.name = name;
            this.isInitial = isInitial;
            this.isAccepting = isAccepting;
        }
    }

    public static class Transition {
        String from;
        String to;
        String symbol;

        public Transition(String from, String to, String symbol) {
            this.from = from;
            this.to = to;
            this.symbol = symbol;
        }
    }

    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("TEST.txt");


        NFA2Lexer lexer = new NFA2Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        NFA2Parser parser = new NFA2Parser(tokens);
        ParseTree tree = parser.nfa();

        ParseTreeWalker walker = new ParseTreeWalker();
        NFACustomListener listener = new NFACustomListener();
        walker.walk(listener, tree);

        generateLatexCircular(listener.states, listener.transitions);
    }

    public static void generateLatexCircular(Map<String, State> states, List<Transition> transitions) {
        StringBuilder sb = new StringBuilder();
        sb.append("\\documentclass{article}\n");
        sb.append("\\usepackage{tikz}\n");
        sb.append("\\usetikzlibrary{automata, positioning}\n");
        sb.append("\\begin{document}\n");
        sb.append("\\begin{tikzpicture}[shorten >=1pt, node distance=3cm, on grid, auto]\n");

        sb.append("\\tikzstyle{state}=[circle, draw, minimum size=1.5cm, text centered, text width=1.5cm]\n");

        int n = states.size();
        int i = 0;
        for (State state : states.values()) {
            double angle = 2 * Math.PI * i / n;
            double x = 5 * Math.cos(angle);
            double y = 5 * Math.sin(angle);
            if (state.isInitial) {
                sb.append(String.format("\\node[state, initial] (%s) at (%.2f, %.2f) {%s};\n", state.name, x, y, state.name)); //todo  REVISAR PQ el nodo normal se aleja
            } else if (state.isAccepting) {                                                                                       //TODO revisar con mas nodos
                sb.append(String.format("\\node[state, accepting] (%s) at (%.2f, %.2f) {%s};\n", state.name, x, y, state.name));  //toDO Y la documentacion README
            } else {
                sb.append(String.format("\\node[state] (%s) at (%.2f, %.2f) {%s};\n", state.name, x, y, state.name));
            }
            i++;
        }

        for (Transition transition : transitions) {
            sb.append(String.format("\\path[->] (%s) edge node {$%s$} (%s);\n",
                    transition.from, transition.symbol, transition.to));
        }

        sb.append("\\end{tikzpicture}\n");
        sb.append("\\end{document}\n");

        System.out.println(sb.toString());
    }


}

class NFACustomListener extends NFA2BaseListener {
    Map<String, NFAToTikZ.State> states = new HashMap<>();
    List<NFAToTikZ.Transition> transitions = new ArrayList<>();
    String initialState;
    List<String> acceptingStates = new ArrayList<>();

    @Override
    public void exitStates(NFA2Parser.StatesContext ctx) {
        for (NFA2Parser.StateContext stateCtx : ctx.state()) {
            String stateName = stateCtx.ID().getText();
            states.put(stateName, new NFAToTikZ.State(stateName, false, false));
        }
    }

    @Override
    public void exitInitial(NFA2Parser.InitialContext ctx) {
        initialState = ctx.ID().getText();
        states.get(initialState).isInitial = true;
    }

    @Override
    public void exitAccepting(NFA2Parser.AcceptingContext ctx) {
        for (TerminalNode id : ctx.ID()) {
            String stateName = id.getText();
            states.get(stateName).isAccepting = true;
        }
    }

    @Override
    public void exitTransition(NFA2Parser.TransitionContext ctx) {
        String from = ctx.ID(0).getText();
        String to = ctx.ID(1).getText();
        String symbol = ctx.SYMBOL().getText();
        transitions.add(new NFAToTikZ.Transition(from, to, symbol));
    }
}
