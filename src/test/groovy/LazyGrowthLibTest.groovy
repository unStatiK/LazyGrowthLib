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
        def growthLib = LazyGrowthLib.of([feature])

        expect:
        growthLib.check(feature.name).withParameters(['cost': 1, 'sender': 'test_sender', 'archive': true]).execute()
        growthLib.check(feature.name).withParameters(['cost': 1, 'sender': 'test_sender', 'archive': true, 'tax': -1]).execute()
    }

    def 'test negative check feature in storage'() {
        given:
        def rule = new RuleBuilder()
                .addNumberAttribute('cost', 1.0)
                .addStringAttribute('sender', 'test_sender')
                .addBooleanAttribute('archive', true)
                .build()
        def feature = new Feature(name: 'archive_feature', rules: [rule])
        def growthLib = LazyGrowthLib.of([feature])

        expect:
        !growthLib.check(feature.name).withParameters(['cost': 1, 'sender': 'test_sender', 'archive': false]).execute()
        !growthLib.check(feature.name).withParameters(['cost': 1, 'sender': 'test_sender']).execute()
        !growthLib.check(feature.name).withParameters(['cost': 1]).execute()
        !growthLib.check('unknown').withParameters(['cost': 1, 'sender': 'test_sender']).execute()
        !growthLib.check('test_sender').execute()
    }

    def 'test exceptionally check feature in storage'() {
        given:
        def rule = new RuleBuilder()
                .addNumberAttribute('cost', 1.0)
                .addStringAttribute('sender', 'test_sender')
                .addBooleanAttribute('archive', true)
                .build()
        def feature = new Feature(name: 'archive_feature', rules: [rule])
        def growthLib = LazyGrowthLib.of([feature])

        when:
        !growthLib.check(null).withParameters(['cost': 1, 'sender': 'test_sender']).execute()

        then:
        IllegalArgumentException e = thrown()
        e.message.contains("cannot be null")
    }

    def 'test exceptionally check feature with parameters in storage'() {
        given:
        def rule = new RuleBuilder()
                .addNumberAttribute('cost', 1.0)
                .addStringAttribute('sender', 'test_sender')
                .addBooleanAttribute('archive', true)
                .build()
        def feature = new Feature(name: 'archive_feature', rules: [rule])
        def growthLib = LazyGrowthLib.of([feature])

        when:
        !growthLib.check('test_sender').withParameters(null).execute()

        then:
        IllegalArgumentException e = thrown()
        e.message.contains("cannot be null")
    }
}
