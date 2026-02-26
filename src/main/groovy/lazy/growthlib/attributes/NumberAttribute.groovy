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
@Immutable(knownImmutableClasses = [Number.class])
@VisibilityOptions(constructor = Visibility.PRIVATE)
class NumberAttribute extends Attribute<Number> {
    private Number value

    static NumberAttribute of(Number value) {
        new NumberAttribute(value: value)
    }

    @Override
    Number getValue() {
        return value
    }
}
