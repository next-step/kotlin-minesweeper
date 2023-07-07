package tdd.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `Given height and width, create a board`() {
        val randomCoordinates = setOf(Coordinate(0, 2), Coordinate(1, 1), Coordinate(2, 2))
        val cells = mapOf(
            Coordinate(0, 0) to Cell(),
            Coordinate(0, 1) to Cell(),
            Coordinate(0, 2) to Cell(Mine),
            Coordinate(1, 0) to Cell(),
            Coordinate(1, 1) to Cell(Mine),
            Coordinate(1, 2) to Cell(),
            Coordinate(2, 0) to Cell(),
            Coordinate(2, 1) to Cell(),
            Coordinate(2, 2) to Cell(Mine),
        )

        val board = Board.of(height = 3, width = 3, mineCount = 3) { _: Int -> randomCoordinates }

        board.cells shouldBe cells
    }

    @Test
    fun `When board opens cell, cell opened`() {
        val cells = mapOf(
            Coordinate(0, 0) to Cell(),
            Coordinate(0, 1) to Cell(),
            Coordinate(0, 2) to Cell(Mine),
            Coordinate(1, 0) to Cell(),
            Coordinate(1, 1) to Cell(Mine),
            Coordinate(1, 2) to Cell(),
            Coordinate(2, 0) to Cell(),
            Coordinate(2, 1) to Cell(),
            Coordinate(2, 2) to Cell(Mine),
        )
        val board = Board(cells)
        val coordinate = Coordinate(1, 2)

        board.open(coordinate)
        board.cells[coordinate] shouldBe Cell(Opened(3))
    }

    @Test
    fun `When opened cell is zero, around cells should be opened continuously`() {
        val cells = mapOf(
            Coordinate(0, 0) to Cell(),
            Coordinate(0, 1) to Cell(),
            Coordinate(0, 2) to Cell(Mine),
            Coordinate(1, 0) to Cell(),
            Coordinate(1, 1) to Cell(),
            Coordinate(1, 2) to Cell(),
            Coordinate(2, 0) to Cell(),
            Coordinate(2, 1) to Cell(),
            Coordinate(2, 2) to Cell(Mine),
        )
        val board = Board(cells)
        val coordinate = Coordinate(1, 0)

        board.open(coordinate)
        board.cells shouldBe mapOf(
            Coordinate(0, 0) to Cell(Opened.of(0)),
            Coordinate(0, 1) to Cell(Opened.of(1)),
            Coordinate(0, 2) to Cell(Mine),
            Coordinate(1, 0) to Cell(Opened.of(0)),
            Coordinate(1, 1) to Cell(Opened.of(2)),
            Coordinate(1, 2) to Cell(),
            Coordinate(2, 0) to Cell(Opened.of(0)),
            Coordinate(2, 1) to Cell(Opened.of(1)),
            Coordinate(2, 2) to Cell(Mine),
        )
    }
}
