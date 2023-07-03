package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineCountTest : StringSpec({
    "해당 포지션 주위의 지뢰의 수를 가져온다." {
        val minesweeperArray: Array<IntArray> = Array(10) { IntArray(10) }
        minesweeperArray[0][1] = -1
        minesweeperArray[1][1] = -1
        minesweeperArray[1][0] = -1

        mineCount(minesweeperArray, Pair(0, 0)) shouldBe 3
    }
})
