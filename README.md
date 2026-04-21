# LazyGrowthLib
Embeddable rule library for JVM.

## Examples

groovy code:
```groovy
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
growthLib.check('is_payment_allowed')
         .withParameters(['cost': 2.0, 'sender': 'Bob', 'with_tax': true, 'retry_count', 1])
         .execute()
```
java code:
```java
import org.codehaus.groovy.runtime.MethodClosure;

public static Boolean isRetryExceeded(Object it) {
    return (Integer) it < 2;
}

// init rule
Rule rule = new RuleBuilder()
                .addNumberAttribute("cost", 2.0)
                .addStringAttribute("sender", "Bob")
                .addBooleanAttribute("with_tax", true)
                .addConditionAttribute("retry_count", new MethodClosure(YourClassWithMethod.class, "isRetryExceeded"))
                .build();

// configure feature with rules
Feature feature = new Feature("is_payment_allowed", Collections.singletonList(rule));

// build growthLib with features
LazyGrowthLib growthLib = LazyGrowthLib.of(Collections.singletonList(feature));

// check parameters by feature
growthLib.check("is_payment_allowed")
         .withParameters(Map.of("cost", 2.0, "sender", "Bob", "with_tax", true, "retry_count", 1))
         .execute();
```
kotlin code:
```kotlin
import groovy.lang.Closure

fun <R> closureOf(block: (Any?) -> R): Closure<R> {
    return object : Closure<R>(null) {
        override fun call(arg: Any?): R {
            return block(arg)
        }
    }
}

// init rule
val rule = RuleBuilder()
            .addNumberAttribute("cost", 2.0)
            .addStringAttribute("sender", "Bob")
            .addBooleanAttribute("with_tax", true)
            .addConditionAttribute("retry_count", closureOf<Boolean> { (it as Int) < 2 })
            .build()

// configure feature with rules
val feature = Feature("is_payment_allowed", listOf(rule))

// build growthLib with features
val growthLib = LazyGrowthLib.of(listOf(feature))

// check parameters by feature
growthLib.check("is_payment_allowed")
    .withParameters(mapOf("cost" to  2.0, "sender" to "Bob", "with_tax" to true, "retry_count" to 1))
    .execute()
```

## Keywords
groovy jvm rules-engine
