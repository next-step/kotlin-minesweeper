package minesweeper.application

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import minesweeper.domain.Coordinate
import minesweeper.domain.UndetonatedMineCell
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class RandomBoardGeneratorTest {
    @Test
    fun `지정한 개수만큼 지뢰를 심는다`() {
        val boardGenerator = RandomBoardGenerator()
        val command =
            GenerateMinesweeperCommand(
                height = 10,
                width = 10,
                mineCount = 10,
            )

        val board = boardGenerator.generate(command)

        val mineCount = board.cells.values.count { it is UndetonatedMineCell }
        mineCount shouldBe 10
    }

    @Test
    fun `(높이 x 너비) = 넓이 개수의 칸을 생성한다`() {
        val boardGenerator = RandomBoardGenerator()
        val command =
            GenerateMinesweeperCommand(
                height = 10,
                width = 10,
                mineCount = 10,
            )

        val board = boardGenerator.generate(command)

        board.cells.count() shouldBe 100
    }

    @Test
    fun `지정한 높이의 판을 생성한다`() {
        val boardGenerator = RandomBoardGenerator()
        val command =
            GenerateMinesweeperCommand(
                height = 10,
                width = 10,
                mineCount = 10,
            )

        val board = boardGenerator.generate(command)

        val maxY = board.cells.keys.maxOf { it.y }
        (maxY + 1) shouldBe 10
    }

    @Test
    fun `지정한 너비의 판을 생성한다`() {
        val boardGenerator = RandomBoardGenerator()
        val command =
            GenerateMinesweeperCommand(
                height = 10,
                width = 10,
                mineCount = 10,
            )

        val board = boardGenerator.generate(command)

        val maxX = board.cells.keys.maxOf { it.x }
        (maxX + 1) shouldBe 10
    }

    @Test
    fun `격자를 생성한다`() {
        val boardGenerator = RandomBoardGenerator()
        val command =
            GenerateMinesweeperCommand(
                height = 10,
                width = 10,
                mineCount = 10,
            )

        val board = boardGenerator.generate(command)

        val grid =
            (0 until 10).flatMap { y ->
                (0 until 10).map { x -> Coordinate(y, x) }
            }
        board.cells.keys shouldContainExactlyInAnyOrder grid
    }
}
