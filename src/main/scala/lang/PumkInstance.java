package pumpkinp.lang;


import java.util.HashMap;
import java.util.Map;

class PumkInstance {
    private OOPumpkinClass klass;
    private final Map<String, Object> fields = new HashMap<>();

    PumkInstance(OOPumpkinClass klass) {
        this.klass = klass;
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        PumKtion method = klass.findMethod(name.lexeme);
        if (method != null) return method.bind(this);

        throw new RuntimeError(name,
                "Undefined property '" + name.lexeme + "'.");
    }

    void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }

    @Override
    public String toString() {
        return klass.name + " instance";
    }
}
