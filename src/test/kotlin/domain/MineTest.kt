package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineTest : StringSpec({
    "mine 생성 성공" {
        Mine(1, 2) shouldBe Mine(1, 2)
    }
})
