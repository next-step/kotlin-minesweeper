package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardDrawingTest {
    @Test
    fun `다음 Row가 비어있지 않으면 true를 반환한다`() {
        val boardDrawing =
            BoardDrawing(
                mutableListOf(
                    DrawingRow(
                        listOf(
                            DrawingCell.OpenCell(1),
                            DrawingCell.MineCell,
                        ),
                    ),
                ),
            )

        assertThat(boardDrawing.hasNext()).isTrue()
    }

    @Test
    fun `다음 Row가 비어있으면 false를 반환한다`() {
        val boardDrawing = BoardDrawing(mutableListOf())

        assertThat(boardDrawing.hasNext()).isFalse()
    }

    @Test
    fun `첫 번째 행을 반환하고 값을 제거한다`() {
        val boardDrawing =
            BoardDrawing.create(
                Cells.detectCreateOf(
                    listOf(
                        Cell.createDefault(Position(0, 0)),
                        Cell.MineCell(Position(1, 0)),
                    ),
                ),
            )

        val expected =
            DrawingRow(
                listOf(
                    DrawingCell.OpenCell(1),
                    DrawingCell.MineCell,
                ),
            )
        assertAll(
            { assertThat(boardDrawing.next()).isEqualTo(expected) },
            { assertThat(boardDrawing.hasNext()).isFalse() },
        )
    }
}
