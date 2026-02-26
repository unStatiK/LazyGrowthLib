import lazy.growthlib.Feature
import lazy.growthlib.LazyGrowthLib
import lazy.growthlib.rule.RuleBuilder
import spock.lang.Specification

class LazyGrowthLibTest extends Specification {
    def 'test positive check feature in storage'() {
        given:
        def rule = new RuleBuilder()
                .addNumberAttribute('cost', 1.0)
                .addStringAttribute('sender', 'test_sender')
                .addBooleanAttribute('archive', true)
                .build()
        def feature = new Feature(name: 'archive_feature', rules: [rule])
        def growthLib = new LazyGrowthLib()
        growthLib.storeFeature(feature)

        expect:
        growthLib.check(feature.name, ['cost': 1, 'sender': 'test_sender', 'archive': true])
        growthLib.check(feature.name, ['cost': 1, 'sender': 'test_sender', 'archive': true, 'tax': -1])
    }

    def 'test negative check feature in storage'() {
        given:
        def rule = new RuleBuilder()
                .addNumberAttribute('cost', 1.0)
                .addStringAttribute('sender', 'test_sender')
                .addBooleanAttribute('archive', true)
                .build()
        def feature = new Feature(name: 'archive_feature', rules: [rule])
        def growthLib = new LazyGrowthLib()
        growthLib.storeFeature(feature)

        expect:
        !growthLib.check(feature.name, ['cost': 1, 'sender': 'test_sender', 'archive': false])
        !growthLib.check(feature.name, ['cost': 1, 'sender': 'test_sender'])
        !growthLib.check(feature.name, ['cost': 1])
    }
}
