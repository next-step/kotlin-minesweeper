package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import minesweeper.domain.rule.RandomMineGenerationRule
import minesweeper.domain.rule.TestMineGenerationRule
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BoardTest {

    @Test
    fun `지뢰찾기 게임판은 높이,넓이,지뢰의개수,생성규칙을 받아 생성한다`() {
        shouldNotThrow<Exception> {
            Board(BoardMetadata(10, 10, 10), RandomMineGenerationRule())
        }
    }

    @Test
    fun `게임판은 좌표로 셀에 접근할 수 있다`() {
        val mineCoordinates = listOf(
            Coordinate(0, 0),
            Coordinate(0, 1),
            Coordinate(1, 0),
            Coordinate(5, 5),
            Coordinate(7, 9)
        )

        val numOfMine = mineCoordinates.size
        val board = Board(
            BoardMetadata(10, 10, numOfMine),
            TestMineGenerationRule(mineCoordinates)
        )

        mineCoordinates.forEach() {
            board.at(it.row, it.col).type shouldBe CellType.MINE
        }
        board.at(2, 2).type shouldBe CellType.EMPTY
        board.at(9, 9).type shouldBe CellType.EMPTY
    }

    @ParameterizedTest
    @MethodSource("invalidCoordinates")
    fun `존재하지 않는 좌표에는 접근할 수 없다`(coordinate: Pair<Int, Int>) {
        val board = Board(BoardMetadata(10, 10, 10), RandomMineGenerationRule())

        shouldThrow<IllegalArgumentException> {
            board.at(coordinate.first, coordinate.second)
        }.message shouldBe "존재하지 않는 좌표입니다."
    }

    companion object {
        @JvmStatic
        fun invalidCoordinates(): List<Arguments> {
            return listOf(
                Arguments.of(-1 to 0),
                Arguments.of(0 to -1),
                Arguments.of(-2 to -1),
                Arguments.of(10 to 0),
                Arguments.of(0 to 10),
                Arguments.of(10 to 10),
                Arguments.of(5 to 15),
                Arguments.of(15 to 5),
            )
        }
    }

    /*
    * 2 1 1 0
    1 2 * 2 1
    2 3 2 2 *
    * * 1 1 1
    2 2 1 0 0
     */
    @Test
    fun `게임판은 지뢰를 생성한 뒤, 주변 지뢰 개수를 계산하여 갖고 있다`() {
        val mineCoordinates = listOf(
            Coordinate(0, 0),
            Coordinate(1, 2),
            Coordinate(2, 4),
            Coordinate(3, 0),
            Coordinate(3, 1)
        )
        val numOfMine = mineCoordinates.size
        val board = Board(
            BoardMetadata(5, 5, numOfMine),
            TestMineGenerationRule(mineCoordinates)
        )
        board.countAllAroundMine()

        for (row in 0 until 5) {
            for (col in 0 until 5) {
                val currentCell = board.at(row, col)
                val mark = when (currentCell.type) {
                    CellType.MINE -> "*"
                    CellType.EMPTY -> currentCell.aroundMineCount.toString()
                }
                print("$mark ")
            }
            println()
        }

        mineCoordinates.forEach() {
            board.at(it.row, it.col).type shouldBe CellType.MINE
        }
        board.countOf(0, 1) shouldBe 2
        board.countOf(0, 2) shouldBe 1
        board.countOf(0, 3) shouldBe 1
        board.countOf(0, 4) shouldBe 0
        board.countOf(1, 0) shouldBe 1
        board.countOf(1, 1) shouldBe 2
        board.countOf(1, 3) shouldBe 2
        board.countOf(1, 4) shouldBe 1
        board.countOf(2, 0) shouldBe 2
        board.countOf(2, 1) shouldBe 3
        board.countOf(2, 2) shouldBe 2
        board.countOf(2, 3) shouldBe 2
        board.countOf(3, 2) shouldBe 1
        board.countOf(3, 3) shouldBe 1
        board.countOf(3, 4) shouldBe 1
        board.countOf(4, 0) shouldBe 2
        board.countOf(4, 1) shouldBe 2
        board.countOf(4, 2) shouldBe 1
        board.countOf(4, 3) shouldBe 0
        board.countOf(4, 4) shouldBe 0
    }
}
