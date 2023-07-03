package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MinesweeperTest : StringSpec({
    "높이, 너비, 지뢰의 수를 가진 지뢰찾기 게임을 만든다." {
        val minesweeper = Minesweeper(10, 10, 10)
        minesweeper.minesweeperArray.size shouldBe 10
    }

    "높이, 너비, 지뢰의 수가 0이거나 음수일 경우 예외를 발생 시킨다." {
        forAll(
            row(-1, 10, 10, "너비는 0보다 커야 합니다."),
            row(10, -1, 10, "높이는 0보다 커야 합니다."),
            row(10, 10, 0, "지뢰의 수는 0보다 커야 합니다."),
        ) { rows, cols, mineCount, message ->
            val exception = shouldThrow<IllegalArgumentException> { Minesweeper(rows, cols, mineCount) }
            exception.message shouldBe message
        }
    }

    "지뢰의 수가 높이, 너비의 곱과 같거나 클 경우 예외를 발생 시킨다." {
        val exception = shouldThrow<IllegalArgumentException> { Minesweeper(10, 10, 100) }
        exception.message shouldBe "지뢰의 수는 높이와 너비의 곱보다 작아야 합니다."
    }
})
