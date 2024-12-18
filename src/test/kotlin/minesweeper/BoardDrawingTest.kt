package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardDrawingTest {
    @Test
    fun `hasNext는 값이 비어있지 않으면 true를 반환한다`() {
        val boardDrawing = BoardDrawing(mutableListOf(listOf(CellType.EMPTY)))

        assertThat(boardDrawing.hasNext()).isTrue()
    }

    @Test
    fun `hasNext는 값이 비어있으면 false를 반환한다`() {
        val boardDrawing = BoardDrawing(mutableListOf())

        assertThat(boardDrawing.hasNext()).isFalse()
    }

    @Test
    fun `next는 첫 번째 행을 반환하고 값을 제거한다`() {
        val boardDrawing = BoardDrawing.create(
            Cells.create(
                listOf(
                    Cell(CellType.EMPTY, Position(0, 0)),
                    Cell(CellType.Mine, Position(1, 0)),
                ),
            ),
        )

        assertAll(
            { assertThat(boardDrawing.next()).containsExactly(CellType.Number(1), CellType.Mine) },
            { assertThat(boardDrawing.hasNext()).isFalse() },
        )
    }
}
