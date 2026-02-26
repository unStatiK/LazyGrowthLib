package lazy.growthlib.attributes

import groovy.transform.Immutable
import groovy.transform.NullCheck

@Category(Object)
@NullCheck
@Immutable
class AttributeValueCandidateExtension {
    Attribute when(Map<Closure, Closure> cases) {
        cases.find { it.key(this) }?.value?.call(this)
    }
}