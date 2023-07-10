package minesweeper2.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineCountTest : StringSpec({
    "해당 포지션 주위의 지뢰의 수를 가져온다." {
        val minesweeperArray: Array<Array<Position>> = Array(10) {
            Array(10) { Position() }
        }
        minesweeperArray[0][1] = Position(-1)
        minesweeperArray[1][1] = Position(-1)
        minesweeperArray[1][0] = Position(-1)
        val mineCount = MineCount(Positions(minesweeperArray))
        mineCount.initMineCount()

        mineCount.positionMineCount(0, 0).value shouldBe 3
    }
})
