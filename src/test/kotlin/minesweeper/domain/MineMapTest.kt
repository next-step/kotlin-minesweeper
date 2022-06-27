package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineMapTest : StringSpec({

    "입력받은 높이(height), 너비(weight) 만큼 칸을 만든다." {
        val mineMap = MineMap.build(10, 5)
        mineMap.map().size shouldBe  10
        mineMap.map().all { it.size == 5 } shouldBe true
    }

})
