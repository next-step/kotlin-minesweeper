package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import minesweeper.domain.field.Mark
import minesweeper.domain.field.Position
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `사이즈와 지뢰 위치를 받아 게임판을 생성한다`() {
        val width = 10
        val height = 10
        val minePositions = setOf(Position(1, 1), Position(2, 2))

        val board = Board.create(width, height, minePositions)

        assertSoftly {
            board.markMap[Position(1, 1)] shouldBe Mark.MINE
            board.markMap[Position(2, 2)] shouldBe Mark.MINE
        }
    }
}
