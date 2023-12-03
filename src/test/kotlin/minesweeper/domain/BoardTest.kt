package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
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
            board.at(it.row, it.col).shouldBeInstanceOf<MineCell>()
        }
        board.at(2, 2).shouldBeInstanceOf<EmptyCell>()
        board.at(9, 9).shouldBeInstanceOf<EmptyCell>()
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

        mineCoordinates.forEach() {
            board.at(it.row, it.col).shouldBeInstanceOf<MineCell>()
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

    @Test
    fun `지뢰가 아닌 좌표를 isOpen 메서드에 입력하면 true 반환`() {
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

        board.canOpen(Coordinate(0, 1)) shouldBe true
        board.canOpen(Coordinate(0, 2)) shouldBe true
        board.canOpen(Coordinate(1, 0)) shouldBe true
        board.canOpen(Coordinate(1, 1)) shouldBe true
        board.canOpen(Coordinate(2, 2)) shouldBe true
        board.canOpen(Coordinate(2, 3)) shouldBe true
        board.canOpen(Coordinate(3, 3)) shouldBe true
        board.canOpen(Coordinate(3, 4)) shouldBe true
        board.canOpen(Coordinate(4, 3)) shouldBe true
        board.canOpen(Coordinate(4, 4)) shouldBe true
    }

    @Test
    fun `지뢰인 좌표를 isOpen 메서드에 입력하면 false 반환`() {
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

        board.canOpen(Coordinate(0, 0)) shouldBe false
        board.canOpen(Coordinate(1, 2)) shouldBe false
        board.canOpen(Coordinate(2, 4)) shouldBe false
        board.canOpen(Coordinate(3, 0)) shouldBe false
        board.canOpen(Coordinate(3, 1)) shouldBe false
    }


    @Test
    fun `주변 지뢰가 0개인 좌표를 입력하여 open 메서드를 호출하면 해당 좌표 주변의 지뢰가 아니고 숫자가 0보다 큰 주변 모든 좌표를 반환한다`() {
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

        val openedCoordinates: List<Coordinate> = board.open(Coordinate(4, 4))

        openedCoordinates.size shouldBe 6
        openedCoordinates shouldContainAll listOf(
            Coordinate(3, 2),
            Coordinate(3, 3),
            Coordinate(3, 4),
            Coordinate(4, 2),
            Coordinate(4, 3),
            Coordinate(4, 4)
        )
    }

    @Test
    fun `주변 지뢰가 0개보다 큰 좌표를 입력하여 open 메서드를 호출하면 해당 좌표만 반환한다`() {
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

        val openedCoordinates: List<Coordinate> = board.open(Coordinate(0, 1))

        openedCoordinates.size shouldBe 1
        openedCoordinates shouldContainAll listOf(Coordinate(0, 1))
    }

    @Test
    fun `지뢰인 좌표를 입력하여 open 메서드를 호출하면 빈 리스트를 반환한다`() {
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

        val openedCoordinates: List<Coordinate> = board.open(Coordinate(0, 0))

        openedCoordinates.size shouldBe 0
    }

    @Test
    fun `지뢰를 제외하고 전체가 open되지 않았다면 isAllOpened는 false 반환`() {
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

        board.isAllOpened() shouldBe false
    }

    @Test
    fun `지뢰를 제외하고 전체가 open되었다면 isAllOpened는 true 반환`() {
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

        for (row in 0 until 5) {
            for (col in 0 until 5) {
                if (mineCoordinates.contains(Coordinate(row, col))) {
                    continue
                }
                board.open(Coordinate(row, col))
            }
        }

        board.isAllOpened() shouldBe true
    }
}
