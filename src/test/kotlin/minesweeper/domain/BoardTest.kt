package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldNotBeInstanceOf
import minesweeper.domain.board.Board
import minesweeper.domain.board.BoardCellsCreationStrategy
import minesweeper.domain.board.RandomBasedCreationStrategy
import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.ClearCell
import minesweeper.domain.cell.NotOpenedCell
import minesweeper.domain.point.Point

class CustomBoardCreationStrategy(
    override val height: Int,
    override val width: Int,
    override val countOfMine: Int,
    val cells: Cells
) : BoardCellsCreationStrategy {
    override fun create(): Cells = cells
}

class BoardTest : ShouldSpec({
    context("5 by 5 크기의 지도를 생성 하려고 한다.") {
        val height = 5
        val width = 5
        val board: (countOfMine: Int) -> Board =
            { countOfMine -> Board(RandomBasedCreationStrategy(height, width, countOfMine)) }
        should("지뢰 숫자가 지도 크기(25)보다 크면 지도를 생성할 수 없다.") {
            shouldThrow<RuntimeException> { board(26) }
        }
        should("지뢰 숫자가 1개 보다 적으면 지도를 생성할 수 없다.") {
            shouldThrow<IllegalArgumentException> { board(0) }
            shouldThrow<IllegalArgumentException> { board(-1) }
        }
        should("지뢰 숫자가 적정 범위(1개 이상 - 지도 크기 이하)일 경우 지도를 생성할 수 있다.") {
            (1..25).forEach { countOfMine ->
                shouldNotThrow<IllegalArgumentException> { board(countOfMine) }
            }
        }
    }

    context("3 by 3 / 지뢰 -> (2, 2) 지도가 있을 때 (0, 0)을 오픈 하면") {
        val cells = Cells()
        Point.square(3, 3)
            .map { ClearCell(it) }
            .forEach { cells.add(it) }
        cells.createMine(Point(2, 2))
        cells.closeAll()
        val board = Board(CustomBoardCreationStrategy(3, 3, 1, cells))
        board.open(Point(0, 0))

        should("주변 셀이 연쇄해서 오픈 된다.") {
            listOf(
                Point(0, 0), Point(1, 0), Point(2, 0),
                Point(0, 1), Point(1, 1), Point(2, 1),
                Point(0, 2), Point(1, 2)
            ).forAll { it.shouldNotBeInstanceOf<NotOpenedCell>() }
        }

        should("게임은 종료 상태 이다.") {
            board.isClear() shouldBe true
        }
    }
})
