package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.area.Height
import minesweeper.domain.area.Width
import minesweeper.domain.block.Mine
import minesweeper.domain.block.None
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BoardTest {

    @Test
    fun `보드를 생성할 수 있다`() {
        val givenWidth = Width(2)
        val givenHeight = Height(2)
        val givenArea = Area(givenWidth, givenHeight)

        assertThat(Board(givenArea, listOf(Mine(), None(), None(), None()))).isNotNull
    }

    @Test
    fun `지뢰가 존재하지않으면 예외를 던진다`() {
        val givenWidth = Width(2)
        val givenHeight = Height(2)
        val givenArea = Area(givenWidth, givenHeight)
        val givenBlocks = listOf(None(), None(), None(), None())

        assertThrows<IllegalArgumentException> { Board(givenArea, givenBlocks) }
    }

    @Test
    fun `넓이와 블록의 수가 일치하지 않으면 예외를 던진다`() {
        val givenWidth = Width(2)
        val givenHeight = Height(2)
        val givenArea = Area(givenWidth, givenHeight)
        val givenBlocks = listOf(Mine(), None(), None())

        assertThrows<IllegalArgumentException> { Board(givenArea, givenBlocks) }
    }
}
