package lazy.growthlib.value

import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.ImmutableOptions
import groovy.transform.NullCheck
import groovy.transform.TypeChecked

import java.util.function.Function

@CompileStatic
@TypeChecked
@NullCheck
@Immutable
@ImmutableOptions(knownImmutableClasses = [Function.class])
class FunctionConditionValue extends ConditionValue<Function<Object, Boolean>> {

    private Function<Object, Boolean> value

    static FunctionConditionValue of(Function<Object, Boolean> value) {
        new FunctionConditionValue(value: value)
    }

    @Override
    ConditionValueType getType() {
        return ConditionValueType.FUNCTION
    }

    @Override
    Function<Object, Boolean> getValue() {
        return value
    }
}
