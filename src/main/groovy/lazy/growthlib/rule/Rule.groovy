package lazy.growthlib.rule

import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.NullCheck
import groovy.transform.TypeChecked
import groovy.transform.VisibilityOptions
import groovy.transform.options.Visibility
import lazy.growthlib.attributes.Attribute
import lazy.growthlib.attributes.ConditionAttribute
import lazy.growthlib.utils.AttributesHelper
import lazy.growthlib.value.ConditionValue
import lazy.growthlib.value.ConditionValueType
import java.util.function.Function

@CompileStatic
@TypeChecked
@NullCheck
@Immutable
@VisibilityOptions(constructor = Visibility.PRIVATE)
// Not record, because https://issues.apache.org/jira/browse/GROOVY-11644
class Rule {
    private Map<String, Attribute> attributes
    private Set<String> attributesKeySet

    static Rule of(Map attributes) {
        new Rule(attributes: attributes, attributesKeySet: attributes.keySet())
    }

    Boolean check(Map<String, Object> parameters) {
        switch (attributesKeySet.intersect(parameters.keySet()).size() == attributesKeySet.size()) {
            case true:
                !attributesKeySet.any { attribute ->
                    def ruleAttribute = attributes[attribute]
                    if (!(ruleAttribute instanceof ConditionAttribute)) {
                        ruleAttribute.value != AttributesHelper.convertToAttribute(parameters[attribute]).value
                    } else {
                        ConditionValue conditionValue = (ConditionValue) ruleAttribute.value
                        switch (conditionValue.type) {
                            case ConditionValueType.CLOSURE:
                                !((Closure) conditionValue.value)(parameters[attribute])
                                break
                            case ConditionValueType.FUNCTION:
                                !((Function) conditionValue.value)(parameters[attribute])
                                break
                        }
                    }
                }
                break
            default: false
        }
    }
}
