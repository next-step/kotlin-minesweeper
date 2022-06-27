package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineSweeperTest : StringSpec({

    "주변 셀에 지뢰가 없으면 0 으로 표시한다." {
        val mineMap = MineMap(2, 2, 0)
        val sweptMineMap = MineSweeper.sweepe(mineMap.map())

        sweptMineMap.flatten().count { it is MineCell } shouldBe 0
        sweptMineMap.flatten().all { it.mineCountAround == 0 } shouldBe true
    }
})
