package lazy.growthlib.attributes

import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.NullCheck
import groovy.transform.TypeChecked
import groovy.transform.VisibilityOptions
import groovy.transform.options.Visibility
import lazy.growthlib.value.ClosureConditionValue
import lazy.growthlib.value.ConditionValue
import lazy.growthlib.value.FunctionConditionValue
import java.util.function.Function

@CompileStatic
@TypeChecked
@NullCheck
@Immutable
@VisibilityOptions(constructor = Visibility.PRIVATE)
class ConditionAttribute extends Attribute<ConditionValue> {
    private ConditionValue value

    static ConditionAttribute of(Closure<Boolean> value) {
        new ConditionAttribute(value: new ClosureConditionValue(value: value))
    }

    static ConditionAttribute of(Function<Object, Boolean> value) {
        new ConditionAttribute(value: new FunctionConditionValue(value: value))
    }

    @Override
    ConditionValue getValue() {
        return value
    }
}
