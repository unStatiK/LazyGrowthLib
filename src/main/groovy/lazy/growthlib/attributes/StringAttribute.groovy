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
class StringAttribute extends Attribute<String> {
    private String value

    static StringAttribute of(String value) {
        new StringAttribute(value: value)
    }

    @Override
    String getValue() {
        return value
    }
}
