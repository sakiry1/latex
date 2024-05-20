import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        try (BufferedReader reader = new BufferedReader(new FileReader("TEST.txt"));
             FileWriter writer = new FileWriter("nfa_output.tex")) {
            writer.write("\\documentclass{article}\n");
            writer.write("\\usepackage{tikz}\n");
            writer.write("\\usetikzlibrary{automata, positioning}\n");
            writer.write("\\begin{document}\n");

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    processLine(line, writer);
                }
            }

            writer.write("\\end{document}\n");
        }
    }

    private static void processLine(String line, FileWriter writer) throws IOException {
        CharStream input = CharStreams.fromString(line);

        NFA2Lexer lexer = new NFA2Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        NFA2Parser parser = new NFA2Parser(tokens);
        ParseTree tree = parser.nfa();

        ParseTreeWalker walker = new ParseTreeWalker();
        NFACustomListener listener = new NFACustomListener();
        walker.walk(listener, tree);

        generateLatexDiagram(listener.states, listener.transitions, writer);
    }

    public static void generateLatexDiagram(Map<String, State> states, List<Transition> transitions, FileWriter writer) throws IOException {
        writer.write("\\begin{tikzpicture}[scale=1.5, node distance=0.5 and 3]\n");

        List<String> stateNames = new ArrayList<>(states.keySet());

        int rows = (int) Math.ceil(Math.sqrt(stateNames.size())); // Calcula el número de filas necesarias
        int cols = (int) Math.ceil((double) stateNames.size() / rows); // Calcula el número de columnas

        double xStep = 4; // Espacio horizontal entre los nodos
        double yStep = 3; // Espacio vertical entre los nodos

        for (int i = 0; i < stateNames.size(); i++) {
            String stateName = stateNames.get(i);
            State state = states.get(stateName);

            double x = (i % cols) * xStep;
            double y = -(i / cols) * yStep;

            String stateStyle = "state";
            if (state.isInitial) {
                stateStyle += ", initial";
            }
            if (state.isAccepting) {
                stateStyle += ", accepting";
            }

            writer.write(String.format("\\node[%s] (%s) at (%.2f, %.2f) {$%s$};\n",
                    stateStyle, stateName, x, y, stateName));
        }

        // Genera las transiciones
        for (Transition transition : transitions) {
            writer.write(String.format("\\path[->] (%s) edge[bend right=15] node {$%s$} (%s);\n",
                    transition.from, transition.symbol, transition.to));
        }

        writer.write("\\end{tikzpicture}\n\\newpage\n");
    }
}

class NFACustomListener extends NFA2BaseListener {
    Map<String, NFAToTikZ.State> states = new HashMap<>();
    List<NFAToTikZ.Transition> transitions = new ArrayList<>();

    @Override
    public void exitStates(NFA2Parser.StatesContext ctx) {
        for (NFA2Parser.StateContext stateCtx : ctx.state()) {
            String stateName = stateCtx.ID().getText();
            states.put(stateName, new NFAToTikZ.State(stateName, false, false));
        }
    }

    @Override
    public void exitInitial(NFA2Parser.InitialContext ctx) {
        String initialState = ctx.ID().getText();
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
