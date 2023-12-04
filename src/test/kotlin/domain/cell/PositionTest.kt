package domain.cell

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.Position
import java.lang.IllegalArgumentException

class PositionTest : StringSpec({
    "0이상인 행과 0이상인 열로 위치를 생성" {
        val row = 0
        val column = 2

        val result = Position(row, column)

        result.row shouldBe row
        result.column shouldBe column
    }

    "행이 0보다 작다면 위치 생성 불가" {
        val row = -1

        shouldThrowExactly<IllegalArgumentException> {
            Position(row, 2)
        }
    }

    "열이 0보다 작다면 위치 생성 불가" {
        val column = -1

        shouldThrowExactly<IllegalArgumentException> {
            Position(2, column)
        }
    }
})
