package lazy.growthlib.attributes

import groovy.transform.CompileStatic
import groovy.transform.NullCheck
import groovy.transform.TypeChecked

@CompileStatic
@NullCheck
@TypeChecked
abstract class Attribute<T> {
    abstract T getValue()
}
