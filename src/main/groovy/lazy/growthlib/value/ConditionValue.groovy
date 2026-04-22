package lazy.growthlib.value

import groovy.transform.CompileStatic
import groovy.transform.NullCheck
import groovy.transform.TypeChecked

@CompileStatic
@NullCheck
@TypeChecked
abstract class ConditionValue<T> {
    abstract ConditionValueType getType()
    abstract T getValue()
}
