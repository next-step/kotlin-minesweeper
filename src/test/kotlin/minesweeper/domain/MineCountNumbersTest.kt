package minesweeper.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import minesweeper.common.value.CoordinateValue

class MineCountNumbersTest : FreeSpec({

    "지뢰 개수를 확인할 수 있다" {
        val x = CoordinateValue(1)
        val y = CoordinateValue(1)

        val position = Position(x, y)
        val nearMineCount = NearMineCount(value = 8)
        val mineCountNumber = MineCountNumber(position = position, count = nearMineCount)
        val mineCountNumbers = MineCountNumbers(listOf(mineCountNumber))

        val mineCount = mineCountNumbers.getMineCountNumber(position)
        mineCount shouldBe NearMineCount(8)
    }
})
