package model

import model.position.Position
import model.row.Row
import org.junit.jupiter.api.Test

internal class BoardTest {
    @Test
    fun `open`() {
        val board = Board(
            listOf(
                Row.Fake(0),
                Row.Fake(1)
            )
        )
        board.open(Position.Fake(0))
    }
}