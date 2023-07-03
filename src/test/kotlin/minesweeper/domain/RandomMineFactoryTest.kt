package minesweeper.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class RandomMineFactoryTest : FreeSpec({

    "범위 내에서 지뢰 좌표를 넘겨 준다." {
        val d10X7 = Dimension(PositiveNumber(10), PositiveNumber(7))
        val mineCount = PositiveNumber(5)
        val randomMineFactory = RandomMineFactory(d10X7, mineCount)
        val mines = randomMineFactory.mines()
        mines.size shouldBe mineCount.number
    }
})
