package lazy.growthlib

import groovy.transform.CompileStatic
import groovy.transform.NullCheck
import groovy.transform.TypeChecked

@NullCheck
@CompileStatic
@TypeChecked
class Context {
    private Map parameters
    private Feature feature

    Context withFeature(Feature feature) {
        this.feature = feature
        this
    }

    Context withParameters(Map parameters) {
        this.parameters = parameters
        this
    }

    Boolean execute() {
        feature?.check(parameters) ?: false
    }
}
