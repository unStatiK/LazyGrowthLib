package lazy.growthlib.attributes

import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.NullCheck
import groovy.transform.TypeChecked
import groovy.transform.VisibilityOptions
import groovy.transform.options.Visibility

@CompileStatic
@TypeChecked
@NullCheck
@Immutable
@VisibilityOptions(constructor = Visibility.PRIVATE)
class ConditionAttribute extends Attribute<Closure<Boolean>> {
    private Closure<Boolean> value

    static ConditionAttribute of(Closure<Boolean> value) {
        new ConditionAttribute(value: value)
    }

    @Override
    Closure<Boolean> getValue() {
        return value
    }
}
