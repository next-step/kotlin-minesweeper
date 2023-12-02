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
        val mineCoordinates = listOf(0 to 0, 0 to 1, 1 to 0, 5 to 5, 7 to 9)
        val numOfMine = mineCoordinates.size
        val board = Board(
            BoardMetadata(10, 10, numOfMine),
            TestMineGenerationRule(mineCoordinates)
        )

        mineCoordinates.forEach() {
            board.at(it.first, it.second).type shouldBe CellType.MINE
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
        val mineCoordinates = listOf(0 to 0, 1 to 2, 2 to 4, 3 to 0, 3 to 1)
        val numOfMine = mineCoordinates.size
        val board = Board(
            BoardMetadata(5, 5, numOfMine),
            TestMineGenerationRule(mineCoordinates)
        )

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
            board.at(it.first, it.second).type shouldBe CellType.MINE
        }
        board.at(0, 0).aroundMineCount shouldBe 0
        board.at(0, 1).aroundMineCount shouldBe 2
        board.at(0, 2).aroundMineCount shouldBe 1
        board.at(0, 3).aroundMineCount shouldBe 1
        board.at(0, 4).aroundMineCount shouldBe 0
        board.at(1, 0).aroundMineCount shouldBe 1
        board.at(1, 1).aroundMineCount shouldBe 2
        board.at(1, 2).aroundMineCount shouldBe 0
        board.at(1, 3).aroundMineCount shouldBe 2
        board.at(1, 4).aroundMineCount shouldBe 1
        board.at(2, 0).aroundMineCount shouldBe 2
        board.at(2, 1).aroundMineCount shouldBe 3
        board.at(2, 2).aroundMineCount shouldBe 2
        board.at(2, 3).aroundMineCount shouldBe 2
        board.at(2, 4).aroundMineCount shouldBe 0
        board.at(3, 0).aroundMineCount shouldBe 0
        board.at(3, 1).aroundMineCount shouldBe 0
        board.at(3, 2).aroundMineCount shouldBe 1
        board.at(3, 3).aroundMineCount shouldBe 1
        board.at(3, 4).aroundMineCount shouldBe 1
        board.at(4, 0).aroundMineCount shouldBe 2
        board.at(4, 1).aroundMineCount shouldBe 2
        board.at(4, 2).aroundMineCount shouldBe 1
        board.at(4, 3).aroundMineCount shouldBe 0
        board.at(4, 4).aroundMineCount shouldBe 0
    }
}
