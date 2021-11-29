package pumpkinp.lang;

import java.util.List;

interface PumKallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
