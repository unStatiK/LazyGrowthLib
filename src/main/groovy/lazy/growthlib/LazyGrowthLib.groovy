package lazy.growthlib

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

@CompileStatic
@TypeChecked
class LazyGrowthLib {
    Map<String, Feature> features = [:]

    def storeFeature(Feature feature) {
        features[feature.name] = feature
    }

    def check(String featureName, Map parameters) {
        features[featureName]?.check(parameters) ?: false
    }
}
