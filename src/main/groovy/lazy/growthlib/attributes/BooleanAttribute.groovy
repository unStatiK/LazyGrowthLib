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
class BooleanAttribute extends Attribute<Boolean> {
    private Boolean value

    static BooleanAttribute of(Boolean value) {
        new BooleanAttribute(value: value)
    }

    @Override
    Boolean getValue() {
        return value
    }
}
