// Generated from C:/Users/Katty/IdeaProjects/latex/NFA2.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link NFA2Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface NFA2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link NFA2Parser#nfa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNfa(NFA2Parser.NfaContext ctx);
	/**
	 * Visit a parse tree produced by {@link NFA2Parser#states}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStates(NFA2Parser.StatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link NFA2Parser#state}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitState(NFA2Parser.StateContext ctx);
	/**
	 * Visit a parse tree produced by {@link NFA2Parser#initial}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitial(NFA2Parser.InitialContext ctx);
	/**
	 * Visit a parse tree produced by {@link NFA2Parser#accepting}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccepting(NFA2Parser.AcceptingContext ctx);
	/**
	 * Visit a parse tree produced by {@link NFA2Parser#transitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransitions(NFA2Parser.TransitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link NFA2Parser#transition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransition(NFA2Parser.TransitionContext ctx);
}