package tdd.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `Given height and width, create a board`() {
        val cells = mapOf(
            Coordinate(0, 0) to Cell(),
            Coordinate(0, 1) to Cell(),
            Coordinate(0, 2) to Cell(),
            Coordinate(1, 0) to Cell(),
            Coordinate(1, 1) to Cell(),
            Coordinate(1, 2) to Cell(),
            Coordinate(2, 0) to Cell(),
            Coordinate(2, 1) to Cell(),
            Coordinate(2, 2) to Cell(),
        )

        val board = Board.of(height = 3, width = 3)

        board.cells shouldBe cells
    }
}
