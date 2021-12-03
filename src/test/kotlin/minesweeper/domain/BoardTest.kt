package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import minesweeper.domain.block.Mine
import minesweeper.domain.block.None
import minesweeper.domain.block.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BoardTest {

    @Test
    fun `보드를 생성할 수 있다`() {
        val givenWidth = Width(2)
        val givenHeight = Height(2)
        val givenArea = Area(givenWidth, givenHeight)
        val givenBlocks = listOf(
            Mine(Position(0, 0)),
            None(Position(0, 1)),
            None(Position(1, 0)),
            None(Position(1, 1))
        )
        assertThat(Board(givenArea, givenBlocks)).isNotNull
    }

    @Test
    fun `지뢰가 존재하지않으면 예외를 던진다`() {
        val givenWidth = Width(2)
        val givenHeight = Height(2)
        val givenArea = Area(givenWidth, givenHeight)
        val givenBlocks = listOf(
            None(Position(0, 0)),
            None(Position(0, 1)),
            None(Position(1, 0)),
            None(Position(1, 1))
        )
        assertThrows<IllegalArgumentException> { Board(givenArea, givenBlocks) }
    }

    @Test
    fun `넓이와 블록의 수가 일치하지 않으면 예외를 던진다`() {
        val givenWidth = Width(2)
        val givenHeight = Height(2)
        val givenArea = Area(givenWidth, givenHeight)
        val givenBlocks = listOf(
            None(Position(0, 0)),
            None(Position(0, 1)),
            None(Position(1, 0)),
        )
        assertThrows<IllegalArgumentException> { Board(givenArea, givenBlocks) }
    }

    @Test
    fun `좌표를 입력받아 블록을 찾을 수 있다`() {
        val givenArea = Area(Width(2), Height(2))
        val givenMine = Mine(Position(0, 0))
        val givenNone1 = None(Position(0, 1))
        val givenNone2 = None(Position(1, 0))
        val givenNone3 = None(Position(1, 1))
        val givenBlocks = listOf(
            givenMine,
            givenNone1,
            givenNone2,
            givenNone3
        )
        val givenBoard = Board(givenArea, givenBlocks)

        val actual = givenBoard.findBlock(0, 0)

        assertThat(actual).isEqualTo(givenMine)
    }
}
