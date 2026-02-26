package lazy.growthlib.rule

import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.NullCheck
import groovy.transform.TypeChecked
import groovy.transform.VisibilityOptions
import groovy.transform.options.Visibility
import lazy.growthlib.attributes.Attribute
import lazy.growthlib.utils.AttributesHelper

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

    def check(Map<String, Object> parameters) {
        switch (attributesKeySet.intersect(parameters.keySet()).size() == attributesKeySet.size()) {
            case true:
                !attributesKeySet.any { attribute ->
                    attributes[attribute].value != AttributesHelper.convertToAttribute(parameters[attribute]).value
                }
                break
            default: false
        }
    }
}
