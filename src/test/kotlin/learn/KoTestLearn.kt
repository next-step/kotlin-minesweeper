package learn

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll

class KoTestLearn : StringSpec({

    "kotest의 기능들에 대해 학습한다" {
        println("https://kotest.io/docs/proptest/property-test-functions.html")
    }

    "forAll : String size" {
        forAll<String, String>() { a, b ->
            print((a + b).length == a.length + b.length)
        }
    }
})
