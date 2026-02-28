package lazy.growthlib

import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.NullCheck
import groovy.transform.TypeChecked
import groovy.transform.VisibilityOptions
import groovy.transform.options.Visibility

@CompileStatic
@TypeChecked
@NullCheck
@Immutable
@VisibilityOptions(constructor = Visibility.PRIVATE)
class LazyGrowthLib {
    private Map<String, Feature> features = [:]

    static LazyGrowthLib of(List<Feature> features) {
        Map<String, Feature> featuresMap = features.collectEntries { feature ->
            [(feature.name): feature]
        }
        new LazyGrowthLib(features: featuresMap)
    }

    Context check(String featureName) {
        def feature = features[featureName]
        def context = new Context()
        feature ? context.withFeature(features[featureName]) : context
    }
}
