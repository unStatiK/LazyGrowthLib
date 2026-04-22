package lazy.growthlib.value

import groovy.transform.CompileStatic
import groovy.transform.NullCheck
import groovy.transform.TypeChecked

@CompileStatic
@NullCheck
@TypeChecked
enum ConditionValueType {
    CLOSURE, FUNCTION
}