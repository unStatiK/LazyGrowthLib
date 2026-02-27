# LazyGrowthLib
Embeddable rule library for JVM.

Example:
```
// init rule
def rule = new RuleBuilder()
                .addNumberAttribute('cost', 2.0)
                .addStringAttribute('sender', 'Bob')
                .addBooleanAttribute('with_tax', true)
                .build()

// configure feature with rules
def feature = new Feature(name: 'is_payment_allowed', rules: [rule])

// save feature
def growthLib = new LazyGrowthLib()
growthLib.storeFeature(feature)

// check parameters by feature
growthLib.check('is_payment_allowed', ['cost': 2.0, 'sender': 'Bob', 'with_tax': true])
```
