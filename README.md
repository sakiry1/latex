# Latex
## Introduction

This project aims to develop a software tool that visualizes state diagrams based on the specification of a non-deterministic automaton (NFA). The tool converts specifications provided in a specific grammar into LaTeX code capable of visualizing the corresponding state diagrams.
### Installation of Components

Below are the steps for installing the necessary components to develop and run this project.
1. Java Installation
   Java is required to execute ANTLR and the main program. Follow these steps:

   Windows:

       Download the JDK from Oracle's website.
       Run the installer and follow the instructions.
       Set the JAVA_HOME environment variable and add bin to your PATH.

2. Installation of ANTLR

    ANTLR is used to generate the parser from the grammar. Follow these steps:

        Download the ANTLR JAR from ANTLR's official website.
        Save the JAR in a specific directory, for example, C:\antlr\antlr-4.13.1-complete.jar.

3. Installation of IntelliJ IDEA

    IntelliJ IDEA is an Integrated Development Environment (IDE) that will be used to write and run the Java code:

         Download and install IntelliJ IDEA from JetBrains website.
         Follow the installer instructions.

### 4. Configuring ANTLR in IntelliJ IDEA

ANTLR works seamlessly with IntelliJ IDEA through a plugin that provides support for ANTLR grammar files and facilitates code generation. Here's how to set up ANTLR in IntelliJ IDEA:

1. **Install IntelliJ IDEA Plugin for ANTLR:**
    - Open IntelliJ IDEA.
    - Go to **File > Settings** (on Windows) or **IntelliJ IDEA > Preferences** (on macOS).
    - In the Settings/Preferences dialog, navigate to **Plugins**.
    - Click on the **Marketplace** tab.
    - Search for "ANTLR v4 grammar plugin" and click on **Install**.
    - After installation, restart IntelliJ IDEA to activate the plugin.

2. **Create a New ANTLR Grammar File:**
    - In IntelliJ IDEA, right-click on your project directory in the Project tool window.
    - Select **New > ANTLR v4**. If this option doesn't appear, ensure that your project is configured to recognize ANTLR files (typically by having a `.g4` extension).

3. **Define Your Grammar:**
    - Inside the newly created ANTLR grammar file, define your grammar rules according to the ANTLR syntax.

4. **Generate Lexer and Parser Code:**
    - After defining your grammar, IntelliJ IDEA will automatically generate the lexer and parser code.
    - If you need to manually trigger code generation, you can do so by right-clicking inside the grammar file and selecting **Generate ANTLR Recognizer**.

5. **Configure ANTLR Code Generation:**
    - By default, IntelliJ IDEA generates lexer and parser code in the same directory as your grammar file.
    - You can customize code generation settings by navigating to **File > Settings > Editor > ANTLR v4**.

6. **Utilize ANTLR Features:**
    - Once configured, you can take advantage of ANTLR features such as syntax highlighting, code completion, and code navigation within your grammar files.

By following these steps, you'll have ANTLR set up and integrated into your IntelliJ IDEA environment, allowing you to work efficiently with ANTLR grammar files and generate lexer and parser code seamlessly.

![Parse tree](https://imgur.com/a/xwgtvfx)

### Grammar Definition

The grammar defines the structure of the input file that specifies an NFA. The grammar file (NFA2.g4) is as follows:

        grammar NFA2;

        nfa : 'states' states 'initial' initial 'accepting' accepting 'transitions' transitions;
        states : state (',' state)*;
        state : ID;
        initial : ID;
        accepting : ID (',' ID)*;
        transitions : transition (',' transition)*;
        transition : ID '->' ID 'on' SYMBOL;
        SYMBOL : [a-zA-Z0-9];
        ID : [a-zA-Z_][a-zA-Z_0-9]*;
        WS : [ \t\r\n]+ -> skip;

This grammar defines rules for specifying states, transitions, and initial and accepting states of an NFA.
Interpreter Explanation

The interpreter takes a text file that adheres to the defined grammar, parses it, and generates the LaTeX code necessary to visualize the state diagram.
### Data Input

The input file (TEST.txt) should follow this structure, based on the defined grammar:

### Generation of LaTeX Code

The generated LaTeX code is as follows:

        \documentclass{article}
        \usepackage{tikz}
        \usetikzlibrary{automata, positioning}
        \begin{document}
        \begin{tikzpicture}[shorten >=1pt, node distance=3cm, on grid, auto]
        \tikzstyle{state}=[circle, draw, minimum size=1.5cm, text centered, text width=1.5cm]
        \node[state] (q1) at (5,00, 0,00) {q1};
        \node[state, accepting] (q2) at (-2,50, 4,33) {q2};
        \node[state, initial] (q0) at (-2,50, -4,33) {q0};
        \path[->] (q0) edge node {$a$} (q1);
        \path[->] (q1) edge node {$b$} (q2);
        \path[->] (q0) edge node {$c$} (q2);
        \end{tikzpicture}
        \end{document}

This LaTeX code can be compiled using any LaTeX editor (such as Overleaf, TeXShop, etc.) to generate the state diagram.
### Testing with Leaflet

After generating the LaTeX code and compiling it to obtain the state diagram, you can use tools like Leaflet to display the diagram interactively on a webpage for testing and visualization purposes

### Program Execution

To run the program, follow these steps:
Ensure that the TEST.txt file contains the NFA specification.
Execute the program in IntelliJ IDEA or from the command line:

        Ejecucion java
        prueba


The program will print the generated LaTeX code in the console. You can copy and paste this code into a LaTeX editor to generate the state diagram.