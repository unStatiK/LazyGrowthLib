import lazy.growthlib.rule.RuleBuilder
import spock.lang.Specification

class RuleTest extends Specification {
    def 'test rule with number attribute'() {
        given:
        def rule = new RuleBuilder()
                .addNumberAttribute('cost', 1.0)
                .build()

        expect:
        rule.check(['cost': 1])
        !rule.check(['cost': 1.1])
    }

    def 'test rule with number attribute and primitive value'() {
        given:
        long msgId = 1466143705189585058
        long linkId = 1470040853375353038
        def rule = new RuleBuilder()
                .addNumberAttribute('msg_id', msgId)
                .addNumberAttribute('link_id', linkId)
                .build()

        expect:
        rule.check(["msg_id": msgId, "link_id": linkId])
    }

    def 'test rule with string attribute'() {
        given:
        def rule = new RuleBuilder()
                .addStringAttribute('sender', 'test_sender')
                .build()

        expect:
        rule.check(['sender': 'test_sender'])
        !rule.check(['sender': 'unknown'])
    }

    def 'test rule with boolean attribute'() {
        given:
        def rule = new RuleBuilder()
                .addBooleanAttribute('archive', true)
                .build()

        expect:
        rule.check(['archive': true])
        !rule.check(['archive': false])
    }

    def 'test rule with condition attribute'() {
        given:
        def rule = new RuleBuilder()
                .addConditionAttribute('tax', { it > 10 })
                .addConditionAttribute('cost', { it >= 20 })
                .addConditionAttribute('count', { it == 30 })
                .addConditionAttribute('type', { it == 'debug' || it == 'archive' })
                .build()

        expect:
        rule.check(['tax': 11, 'cost': 21, 'count': 30, 'type': 'debug'])
        rule.check(['tax': 11, 'cost': 21, 'count': 30, 'type': 'archive'])
        rule.check(['tax': 11, 'cost': 20, 'count': 30, 'type': 'archive'])
        rule.check(['tax': 11, 'cost': 20, 'count': 30, 'type': 'debug'])
        !rule.check(['tax': 10])
        !rule.check(['tax': 10, 'cost': 21, 'count': 30, 'type': 'archive'])
        !rule.check(['tax': 11, 'cost': 19, 'count': 30, 'type': 'archive'])
        !rule.check(['tax': 11, 'cost': 21, 'count': 31, 'type': 'archive'])
        !rule.check(['tax': 11, 'cost': 21, 'count': 30, 'type': 'test'])
    }
}
