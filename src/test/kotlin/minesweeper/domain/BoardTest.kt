package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import minesweeper.domain.block.Blocks
import minesweeper.domain.block.Mine
import minesweeper.domain.block.None
import minesweeper.domain.block.Position
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

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
        assertThat(Board(givenArea, Blocks(givenBlocks))).isNotNull
    }

    @Test
    fun `넓이와 블록의 수가 일치하지 않으면 예외를 던진다`() {
        val givenWidth = Width(2)
        val givenHeight = Height(2)
        val givenArea = Area(givenWidth, givenHeight)
        val givenBlocks = listOf(
            Mine(Position(0, 0)),
            None(Position(0, 1)),
            None(Position(1, 0)),
        )
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { Board(givenArea, Blocks(givenBlocks)) }.withMessage("면적과 블록의 갯수는 같아야 합니다.")
    }

    /**
     *  * 1 0
     *  1 1 0
     *  0 0 0
     */
    @Test
    fun `지뢰 검색을 시도한 보드를 리턴한다`() {
        val givenArea = Area(Width(3), Height(3))
        val givenMine = Mine(Position(0, 0))
        val givenNone1 = None(Position(0, 1))
        val givenNone2 = None(Position(0, 2))
        val givenNone3 = None(Position(1, 0))
        val givenNone4 = None(Position(1, 1))
        val givenNone5 = None(Position(1, 2))
        val givenNone6 = None(Position(2, 0))
        val givenNone7 = None(Position(2, 1))
        val givenNone8 = None(Position(2, 2))

        val givenBlocks = listOf(
            givenMine,
            givenNone1,
            givenNone2,
            givenNone3,
            givenNone4,
            givenNone5,
            givenNone6,
            givenNone7,
            givenNone8,
        )
        val givenBoard = Board(givenArea, Blocks(givenBlocks))

        val actual = givenBoard.scanMine(0, 1)

        assertThat(actual.board.blocks.blocks).containsExactly(
            Mine(Position(0, 0), null),
            None(Position(0, 1), 1),
            None(Position(0, 2), 0),
            None(Position(1, 0), 1),
            None(Position(1, 1), 1),
            None(Position(1, 2), 0),
            None(Position(2, 0), null),
            None(Position(2, 1), null),
            None(Position(2, 2), null),
        )
    }
}
