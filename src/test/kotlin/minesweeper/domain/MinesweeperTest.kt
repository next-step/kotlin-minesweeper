package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MinesweeperTest : StringSpec({
    "높이, 너비, 지뢰의 수를 가진 지뢰찾기 게임을 만든다." {
        val minesweeper = Minesweeper(10, 10, 10)
        minesweeper.minesweeperArray.size shouldBe 10
    }
})
