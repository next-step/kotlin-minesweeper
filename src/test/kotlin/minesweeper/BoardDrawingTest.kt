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
        val boardDrawing = BoardDrawing(mutableListOf(listOf(CellType.EMPTY)))

        assertAll(
            { assertThat(boardDrawing.next()).containsExactly(CellType.EMPTY) },
            { assertThat(boardDrawing.hasNext()).isFalse() },
        )
    }
}