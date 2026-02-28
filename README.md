# LazyGrowthLib
Embeddable rule library for JVM.

Example:
```
// init rule
def rule = new RuleBuilder()
                .addNumberAttribute('cost', 2.0)
                .addStringAttribute('sender', 'Bob')
                .addBooleanAttribute('with_tax', true)
                .addConditionAttribute('retry_count', { it < 2 })
                .build()

// configure feature with rules
def feature = new Feature(name: 'is_payment_allowed', rules: [rule])

// build growthLib with features
def growthLib = LazyGrowthLib.of([feature])

// check parameters by feature
growthLib.check('is_payment_allowed').withParameters(['cost': 2.0, 'sender': 'Bob', 'with_tax': true]).execute()
```
