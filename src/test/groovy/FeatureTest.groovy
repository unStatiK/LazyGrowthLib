import lazy.growthlib.Feature
import lazy.growthlib.attributes.BooleanAttribute
import lazy.growthlib.attributes.NumberAttribute
import lazy.growthlib.attributes.StringAttribute
import lazy.growthlib.rule.RuleBuilder
import spock.lang.Specification

class FeatureTest extends Specification {
    def 'test build feature'() {
        given:
        def rule = new RuleBuilder()
                .addNumberAttribute('cost', 1.1)
                .addStringAttribute('sender', 'test_sender')
                .addBooleanAttribute('archive', true)
                .build()
        def feature = new Feature(name: 'archive_feature', rules: [rule])

        expect:
        feature.name == 'archive_feature'
        feature.rules.size() == 1
        feature.rules.first().attributes['cost'].value == NumberAttribute.of(1.1).value
        feature.rules.first().attributes['sender'].value == StringAttribute.of('test_sender').value
        feature.rules.first().attributes['archive'].value == BooleanAttribute.of(true).value
    }

    def 'test check feature with single rule'() {
        given:
        def rule = new RuleBuilder()
                .addNumberAttribute('cost', 1.0)
                .addStringAttribute('sender', 'test_sender')
                .addBooleanAttribute('archive', true)
                .build()
        def feature = new Feature(name: 'archive_feature', rules: [rule])

        expect:
        feature.check(['cost': 1.0, 'sender': 'test_sender', 'archive': true])
        feature.check(['cost': 1.0, 'sender': "test_sender", 'archive': true])
        feature.check(['cost': 1, 'sender': 'test_sender', 'archive': true])
        feature.check(['cost': 1L, 'sender': 'test_sender', 'archive': true])
        feature.check(['cost': 1.0, 'sender': 'test_sender', 'archive': true, 'tax': -1])
        !feature.check(['cost': 1.0, 'sender': 'test_sender'])
    }

    def 'test check feature with multiple rule'() {
        given:
        def notArchiveRule = new RuleBuilder()
                .addNumberAttribute('cost', 1.2)
                .addStringAttribute('sender', 'test_sender')
                .addBooleanAttribute('archive', false)
                .build()
        def archiveRule = new RuleBuilder()
                .addNumberAttribute('cost', 1.0)
                .addStringAttribute('sender', 'test_sender')
                .addBooleanAttribute('archive', true)
                .build()
        def feature = new Feature(name: 'archive_feature', rules: [notArchiveRule, archiveRule])

        expect:
        feature.check(['cost': 1.0, 'sender': 'test_sender', 'archive': true])
        feature.check(['cost': 1.0, 'sender': "test_sender", 'archive': true])
        feature.check(['cost': 1, 'sender': 'test_sender', 'archive': true])
        feature.check(['cost': 1L, 'sender': 'test_sender', 'archive': true])
        feature.check(['cost': 1.0, 'sender': 'test_sender', 'archive': true, 'tax': -1])
        !feature.check(['cost': 1.0, 'sender': 'test_sender'])
        !feature.check(['cost': 1.1, 'sender': 'test_sender', 'archive': true])
        !feature.check(['cost': 1.0, 'sender': 'sender', 'archive': true])
        !feature.check(['cost': 1.0, 'sender': 'test_sender', 'archive': false])
        feature.check(['cost': 1.2, 'sender': 'test_sender', 'archive': false])
        feature.check(['cost': 1.2, 'sender': "test_sender", 'archive': false])
        feature.check(['cost': 1.2, 'sender': 'test_sender', 'archive': false, 'tax': -1])
        !feature.check(['cost': 1.2, 'sender': 'test_sender'])
        !feature.check(['cost': 1.3, 'sender': "test_sender", 'archive': false])
        !feature.check(['cost': 1.2, 'sender': "sender", 'archive': false])
        !feature.check(['cost': 1.2, 'sender': "test_sender", 'archive': true])
    }
}
