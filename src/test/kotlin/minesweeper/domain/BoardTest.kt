package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import minesweeper.domain.board.Board
import minesweeper.domain.board.BoardCellsCreationStrategy
import minesweeper.domain.board.RandomBasedCreationStrategy
import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.ClearCell
import minesweeper.domain.point.Point

class CustomBoardCreationStrategy(
    val cells: Cells
) : BoardCellsCreationStrategy {
    override fun create(width: Int, height: Int, countOfMine: Int): Cells = cells
}

class BoardTest : ShouldSpec({
    context("5 by 5 크기의 지도를 생성 하려고 한다.") {
        val height = 5
        val width = 5
        val board: (countOfMine: Int) -> Board =
            { countOfMine -> Board.of(height, width, countOfMine, RandomBasedCreationStrategy()) }
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
        val clearCells = Point.square(3, 3).map { ClearCell(it) }
        val cells = Cells.from(clearCells)

        cells.createMine(Point(2, 2))

        val board = Board.of(3, 3, 1, CustomBoardCreationStrategy(cells))
        board.open(Point(0, 0))

        should("주변 셀이 연쇄해서 오픈 된다.") {
            listOf(
                Point(0, 0), Point(1, 0), Point(2, 0),
                Point(0, 1), Point(1, 1), Point(2, 1),
                Point(0, 2), Point(1, 2)
            ).forAll { cells.at(it).isOpened shouldBe true }
        }

        should("게임은 종료 상태 이다.") {
            board.isClear() shouldBe true
        }
    }
})
