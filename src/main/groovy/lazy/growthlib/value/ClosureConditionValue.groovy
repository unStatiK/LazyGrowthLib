package lazy.growthlib.value

import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.NullCheck
import groovy.transform.TypeChecked

@CompileStatic
@TypeChecked
@NullCheck
@Immutable
class ClosureConditionValue extends ConditionValue<Closure<Boolean>> {
    private Closure<Boolean> value

    static ClosureConditionValue of(Closure<Boolean> value) {
        new ClosureConditionValue(value: value)
    }

    @Override
    ConditionValueType getType() {
        return ConditionValueType.CLOSURE
    }

    @Override
    Closure<Boolean> getValue() {
        return value
    }
}
