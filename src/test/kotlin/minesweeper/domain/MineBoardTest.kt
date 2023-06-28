package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.application.MineSweeperService
import minesweeper.domain.SymbolType.MINE
import minesweeper.domain.SymbolType.ONE
import minesweeper.domain.SymbolType.THREE
import minesweeper.domain.SymbolType.TWO
import minesweeper.domain.SymbolType.ZERO
import minesweeper.domain.state.ResultState
import minesweeper.fixture.TestMineBoardCreateStrategy
import minesweeper.request.MineBoardCreateRequest

class MineBoardTest : FreeSpec({
    val service = MineSweeperService(mineBoardCreateStrategy = TestMineBoardCreateStrategy)

    "지뢰찾기 보드의 각 좌표 정보는 인접한 8개의 좌표의 지뢰 갯수만큼 카운팅된다." - {
        val request = MineBoardCreateRequest(height = 3, width = 3, mineCapacity = 3)

        /**
         *  1 X 1
         *  2 3 2
         *  X 2 X
         */
        val expectedLines = Lines(
            listOf(
                Line(listOf(SymbolPoint(0, 0, ONE), SymbolPoint(1, 0, MINE), SymbolPoint(2, 0, ONE))),
                Line(listOf(SymbolPoint(0, 1, TWO), SymbolPoint(1, 1, THREE), SymbolPoint(2, 1, TWO))),
                Line(listOf(SymbolPoint(0, 2, MINE), SymbolPoint(1, 2, TWO), SymbolPoint(2, 2, MINE)))
            )
        )

        TestMineBoardCreateStrategy.updateBoardSetUp(input = createFixtureLines())

        service.createMineBoard(request).apply {
            area.height shouldBe request.height
            area.width shouldBe request.width
            lines shouldHaveSize request.height

            lines shouldContainExactly expectedLines
        }
    }

    "3x3의 지뢰판이 있다고 할 때 " - {
        "유효한 좌표정보로 마킹할 수 있다." {
            val targetPoint = Point(0, 0)
            val request = MineBoardCreateRequest(height = 3, width = 3, mineCapacity = 3)
            TestMineBoardCreateStrategy.updateBoardSetUp(input = createFixtureLines())
            val mineBoard = service.createMineBoard(request)

            mineBoard.marking(targetPoint)
            val actual: SymbolPoint = mineBoard.findPoint(targetPoint)

            actual.isMarked() shouldBe true
        }

        "유효하지 않은 좌표정보로 마킹할 수 없다." {
            val targetPoint = Point(4, 5)
            val request = MineBoardCreateRequest(height = 3, width = 3, mineCapacity = 3)
            TestMineBoardCreateStrategy.updateBoardSetUp(input = createFixtureLines())
            val mineBoard = service.createMineBoard(request)

            shouldThrow<IndexOutOfBoundsException> {
                mineBoard.marking(targetPoint)
            }
        }

        "마킹을 시도한 좌표 정보가" - {
            "지뢰일 경우 Lose 결과를 반환한다." {
                val targetPoint = Point(1, 0)
                val request = MineBoardCreateRequest(height = 3, width = 3, mineCapacity = 3)
                TestMineBoardCreateStrategy.updateBoardSetUp(input = createFixtureLines())
                val mineBoard = service.createMineBoard(request)

                val actual = mineBoard.marking(targetPoint)

                actual shouldBe ResultState.LOSE
            }
        }

        "마킹을 모두 성공하면" - {
            "승리한다." {
                val request = MineBoardCreateRequest(height = 3, width = 3, mineCapacity = 3)
                TestMineBoardCreateStrategy.updateBoardSetUp(input = createFixtureLines())
                val mineBoard = service.createMineBoard(request)

                listOf(Point(0, 0), Point(2, 0)).forEach(mineBoard::marking)

                val actual = mineBoard.marking(Point(1, 2))

                actual shouldBe ResultState.WIN
            }
        }
    }
})

/**
 *  0 X 0
 *  0 0 0
 *  X 0 X
 */
fun createFixtureLines(): Lines = Lines(
    listOf(
        Line(listOf(SymbolPoint(0, 0, ZERO), SymbolPoint(1, 0, MINE), SymbolPoint(2, 0, ZERO))),
        Line(listOf(SymbolPoint(0, 1, ZERO), SymbolPoint(1, 1, ZERO), SymbolPoint(2, 1, ZERO))),
        Line(listOf(SymbolPoint(0, 2, MINE), SymbolPoint(1, 2, ZERO), SymbolPoint(2, 2, MINE)))
    )
)
