package lazy.growthlib

import groovy.transform.CompileStatic
import groovy.transform.NullCheck
import groovy.transform.TypeChecked
import lazy.growthlib.rule.Rule

@CompileStatic
@TypeChecked
@NullCheck
record Feature(String name, List<Rule> rules) {
    String getName() { name }

    List<Rule> getRules() { rules }

    def check(Map parameters) {
        rules.any { rule ->
            rule.check(parameters)
        }
    }
}