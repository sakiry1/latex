// Generated from C:/Users/Katty/IdeaProjects/latex/NFA2.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NFA2Parser}.
 */
public interface NFA2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NFA2Parser#nfa}.
	 * @param ctx the parse tree
	 */
	void enterNfa(NFA2Parser.NfaContext ctx);
	/**
	 * Exit a parse tree produced by {@link NFA2Parser#nfa}.
	 * @param ctx the parse tree
	 */
	void exitNfa(NFA2Parser.NfaContext ctx);
	/**
	 * Enter a parse tree produced by {@link NFA2Parser#states}.
	 * @param ctx the parse tree
	 */
	void enterStates(NFA2Parser.StatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link NFA2Parser#states}.
	 * @param ctx the parse tree
	 */
	void exitStates(NFA2Parser.StatesContext ctx);
	/**
	 * Enter a parse tree produced by {@link NFA2Parser#state}.
	 * @param ctx the parse tree
	 */
	void enterState(NFA2Parser.StateContext ctx);
	/**
	 * Exit a parse tree produced by {@link NFA2Parser#state}.
	 * @param ctx the parse tree
	 */
	void exitState(NFA2Parser.StateContext ctx);
	/**
	 * Enter a parse tree produced by {@link NFA2Parser#initial}.
	 * @param ctx the parse tree
	 */
	void enterInitial(NFA2Parser.InitialContext ctx);
	/**
	 * Exit a parse tree produced by {@link NFA2Parser#initial}.
	 * @param ctx the parse tree
	 */
	void exitInitial(NFA2Parser.InitialContext ctx);
	/**
	 * Enter a parse tree produced by {@link NFA2Parser#accepting}.
	 * @param ctx the parse tree
	 */
	void enterAccepting(NFA2Parser.AcceptingContext ctx);
	/**
	 * Exit a parse tree produced by {@link NFA2Parser#accepting}.
	 * @param ctx the parse tree
	 */
	void exitAccepting(NFA2Parser.AcceptingContext ctx);
	/**
	 * Enter a parse tree produced by {@link NFA2Parser#transitions}.
	 * @param ctx the parse tree
	 */
	void enterTransitions(NFA2Parser.TransitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NFA2Parser#transitions}.
	 * @param ctx the parse tree
	 */
	void exitTransitions(NFA2Parser.TransitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NFA2Parser#transition}.
	 * @param ctx the parse tree
	 */
	void enterTransition(NFA2Parser.TransitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NFA2Parser#transition}.
	 * @param ctx the parse tree
	 */
	void exitTransition(NFA2Parser.TransitionContext ctx);
}