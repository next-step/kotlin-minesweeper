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
}
