package lazy.growthlib.rule

import groovy.transform.CompileStatic
import groovy.transform.NullCheck
import groovy.transform.TypeChecked
import lazy.growthlib.attributes.Attribute
import lazy.growthlib.attributes.BooleanAttribute
import lazy.growthlib.attributes.ConditionAttribute
import lazy.growthlib.attributes.NumberAttribute
import lazy.growthlib.attributes.StringAttribute

@NullCheck
@CompileStatic
@TypeChecked
class RuleBuilder {
    private Map<String, Attribute> attributes = [:]

    def addNumberAttribute(String attribute, Number value) {
        this.attributes[attribute] = NumberAttribute.of(value)
        this
    }

    def addStringAttribute(String attribute, String value) {
        this.attributes[attribute] = StringAttribute.of(value)
        this
    }

    def addBooleanAttribute(String attribute, Boolean value) {
        this.attributes[attribute] = BooleanAttribute.of(value)
        this
    }

    def addConditionAttribute(String attribute, Closure<Boolean> value) {
        this.attributes[attribute] = ConditionAttribute.of(value)
        this
    }

    def build() {
        Rule.of(this.attributes)
    }
}
