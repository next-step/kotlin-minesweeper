package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineMapTest : StringSpec({
    "MineMap은 높이와 너비를 가진다" {
        val mineMap = MineMap(10, 10)
        mineMap.height shouldBe 10
        mineMap.width shouldBe 10
    }
})
