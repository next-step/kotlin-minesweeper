package minesweeper.domain.board

import minesweeper.domain.position.Coordinate
import minesweeper.domain.position.Position
import minesweeper.domain.tile.Blank
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class BoardTest {

    @Test
    fun `element가 비어 있는 경우 Board는 생성 불가`() {
        assertThrows<IllegalArgumentException> { Board(mapOf()) }
    }

    @Test
    fun `element가 비어 있지 않는 경우 Board는 생성 가능`() {
        assertDoesNotThrow {
            Board(
                mapOf(
                    Position(x = Coordinate.of(1), y = Coordinate.of(1)) to Blank()
                )
            )
        }
    }
}
