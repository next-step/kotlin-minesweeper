package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MinesweeperTest : StringSpec({
    val rows = Rows(10)
    val cols = Cols(10)

    "높이, 너비, 지뢰의 수를 가진 지뢰찾기 게임을 만든다." {
        val minesweeper = Minesweeper.from(rows, cols, MineValue(10, 10, 10))
        minesweeper.positions.rows shouldBe 10
        minesweeper.positions.cols shouldBe 10
    }

    "지뢰의 수가 높이, 너비의 곱과 같거나 클 경우 예외를 발생 시킨다." {
        val exception = shouldThrow<IllegalArgumentException> { Minesweeper.from(rows, cols, MineValue(100, 10, 10)) }
        exception.message shouldBe "지뢰의 수는 높이와 너비의 곱보다 작아야 합니다."
    }

    "포지션 선택 시 지뢰가 없는 인접 칸이 모두 열리게 된다." {
        val array = Array(10) {
            Array(10) {
                Position()
            }
        }

        val minesweeperArray = Positions(array)
        val minesweeper = Minesweeper(
            minesweeperArray,
            MinePositions(listOf(MinePosition(1, 2), MinePosition(2, 2), MinePosition(2, 1)))
        )

        minesweeper.open("0, 0")
        minesweeper.position(0, 0).isOpened shouldBe true
        minesweeper.position(0, 1).isOpened shouldBe true
        minesweeper.position(1, 0).isOpened shouldBe true
        minesweeper.position(1, 1).isOpened shouldBe true
    }
})
