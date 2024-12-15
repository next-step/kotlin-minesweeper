package minesweeper.application

import io.kotest.matchers.shouldBe
import minesweeper.domain.MinedCell
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

        val countMine = board.cells.count { it is MinedCell }
        countMine shouldBe 10
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

        val maxY = board.cells.maxOf { it.coordinate.y }
        maxY + 1 shouldBe 10
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

        val maxX = board.cells.maxOf { it.coordinate.x }
        maxX + 1 shouldBe 10
    }
}
