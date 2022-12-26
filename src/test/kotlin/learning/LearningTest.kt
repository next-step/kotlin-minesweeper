package learning

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeLessThan
import kotlin.random.Random

class LearningTest:StringSpec ({

    "RandomUtil test: nextInt의 파라미터값은 포함되지 않는다."{
        for(i in 0..1000) {
            val nextInt = Random.nextInt(100)
            nextInt shouldBeLessThan 100
        }
    }
})
