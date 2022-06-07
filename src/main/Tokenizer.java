package main;

import java.util.Stack;

public class Tokenizer {

    private State state = State.OUT;
    private char[] json;
    private int index = 0;
    private Stack<Symbol> stack = new Stack<>();

    private Tokenizer(char[] json) {
        this.json = json;
    }

    public static Symbol[] tokenize(String json) {
        return (new Tokenizer(json.toCharArray())).parse();
    }

    private Symbol[] parse() {
        
    }

}

interface Symbol {
    public ParserType type();
}

class Terminal implements Symbol {
    private char val;

    public Terminal(char val) {
        this.val = val;
    }

    public char val() {
        return this.val;
    }

    @Override
    public ParserType type() {
        return ParserType.TERMINAL;
    }
}

enum State {
    OUT, IN_STR
}