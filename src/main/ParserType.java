package main;

enum ParserType implements Symbol {
    VAL, VALS, BOOL, NULL, STRING, CHARS, CHAR, ARRAY, OBJECT, PAIRS, PAIR, NUMBER, TERMINAL;

    @Override
    public ParserType type() {
        return this;
    }

    public Symbol[][] production() {
        switch (this) {
            case VAL: {
                Symbol[][] arr = {
                        { BOOL },
                        { NULL },
                        { STRING },
                        { ARRAY },
                        { OBJECT },
                        { NUMBER }
                };
                return arr;
            }
            case VALS: {
                Symbol[][] arr = {
                        { VAL },
                        { VAL, VALS }
                };
                return arr;
            }
            case BOOL: {
                Symbol[][] arr = { {
                        new Terminal('t'),
                        new Terminal('r'),
                        new Terminal('u'),
                        new Terminal('e'),
                }, {
                        new Terminal('f'),
                        new Terminal('a'),
                        new Terminal('l'),
                        new Terminal('s'),
                        new Terminal('e'),
                } };
                return arr;
            }
            case NULL: {
                Symbol[][] arr = { {
                        new Terminal('n'),
                        new Terminal('u'),
                        new Terminal('l'),
                        new Terminal('l'),
                } };
                return arr;
            }
            case STRING: {
                Symbol[][] arr = { {
                        new Terminal('"'),
                        CHARS,
                        new Terminal('"')
                }
                };
                return arr;
            }
            case CHARS: {
                Symbol[][] arr = {
                        { CHAR },
                        { CHAR, CHARS }
                };
                return arr;
            }
            case ARRAY: {
                Symbol[][] arr = { {
                        new Terminal('['),
                        VALS,
                        new Terminal(']') }
                };
                return arr;
            }
            case OBJECT: {
                Symbol[][] arr = { {
                        new Terminal('{'),
                        VALS,
                        new Terminal('}') } };
                return arr;
            }
            case PAIRS: {
                Symbol[][] arr = { { PAIR, PAIRS }, { PAIR } };
                return arr;
            }
            case PAIR: {
                Symbol[][] arr = { { STRING, new Terminal(':'), VAL } };
                return arr;
            }
            case NUMBER: {
                Symbol[][] arr = {
                    {CHARS}
                };
                return arr;
            }

            default:
                return null;
        }
    }
}