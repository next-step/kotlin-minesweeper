package minesweeper.board.render

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import minesweeper.board.Board
import minesweeper.board.BoardElement
import minesweeper.board.GameBoard
import minesweeper.board.MinesweeperBoard
import minesweeper.cell.Cell
import minesweeper.cell.CellType
import minesweeper.position.Position

class MinesweeperBoardRenderTest : BehaviorSpec({

    Given("지뢰들이 존재하고") {
        val mines = setOf(
            Position(0, 0),
            Position(2, 2)
        )
        When("게임판 크기와 셀 값이 주어진다면") {
            val element = BoardElement(3, 3)

            Then("지뢰찾기 게임판을 생성한다.") {
                val actual = MinesweeperBoardRender(mines)(element, '0')
                val expect = MinesweeperBoard(
                    Board(
                        listOf(
                            listOf(
                                Cell(Position(0, 0), CellType.MINE, '0'),
                                Cell(Position(1, 0), CellType.NORMAL, '1'),
                                Cell(Position(2, 0), CellType.NORMAL, '0')
                            ),
                            listOf(
                                Cell(Position(0, 1), CellType.NORMAL, '1'),
                                Cell(Position(1, 1), CellType.NORMAL, '2'),
                                Cell(Position(2, 1), CellType.NORMAL, '1')
                            ),
                            listOf(
                                Cell(Position(0, 2), CellType.NORMAL, '0'),
                                Cell(Position(1, 2), CellType.NORMAL, '1'),
                                Cell(Position(2, 2), CellType.MINE, '0')
                            )
                        )
                    )
                )
                actual shouldBe expect
            }
        }
    }

})
