package lazy.growthlib.utils

import groovy.transform.Immutable
import groovy.transform.NullCheck
import groovy.transform.VisibilityOptions
import groovy.transform.options.Visibility
import lazy.growthlib.attributes.Attribute
import lazy.growthlib.attributes.AttributeValueCandidateExtension
import lazy.growthlib.attributes.BooleanAttribute
import lazy.growthlib.attributes.NumberAttribute
import lazy.growthlib.attributes.StringAttribute

@Immutable
@NullCheck
@VisibilityOptions(constructor = Visibility.PRIVATE)
class AttributesHelper {
    static Attribute convertToAttribute(def value) {
        use(AttributeValueCandidateExtension) {
            return value.when(
                    [{ it instanceof Number }      : { NumberAttribute.of(it) },
                     { it instanceof CharSequence }: { StringAttribute.of(it) },
                     { it instanceof Boolean }     : { BooleanAttribute.of(it) }]
            )
        }
    }
}
