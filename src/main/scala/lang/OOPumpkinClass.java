package pumpkinp.lang;

import java.util.Map;
import java.util.List;

class OOPumpkinClass implements PumKallable {
    final String name;
    final OOPumpkinClass superclass;
    private final Map<String, PumKtion> methods;

    OOPumpkinClass(String name,OOPumpkinClass superclass, Map<String, PumKtion> methods) {
        this.superclass = superclass;
        this.name = name;
        this.methods = methods;
    }

    PumKtion findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        if (superclass != null) {
            return superclass.findMethod(name);
        }

        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object call(Interpreter interpreter,
                       List<Object> arguments) {
        PumkInstance instance = new PumkInstance(this);
        PumKtion initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }

        return instance;
    }

    @Override
    public int arity() {
        PumKtion initializer = findMethod("init");
        if (initializer == null) return 0;
        return initializer.arity();
    }
}
