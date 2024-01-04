package minesweeper.board.render

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.board.BoardElement
import minesweeper.board.DefaultGameBoard
import minesweeper.cell.Cell
import minesweeper.cell.CellType
import minesweeper.position.Position

class DefaultBoardRenderTest: BehaviorSpec ({

    Given("높이와 너비가 주어진다면") {
        val boardElement = BoardElement(3, 3)
        val mines = emptySet<Position>()
        When("게임판 생성기를 통해") {
            val actual = DefaultBoardRender(mines)(boardElement, 'C')
            Then("2차원의 문자를 가지는 게임판을 생성한다.") {
                val expect = DefaultGameBoard(
                    listOf(
                        listOf(
                            Cell(Position(0, 0), CellType.NORMAL, 'C'),
                            Cell(Position(0, 1), CellType.NORMAL, 'C'),
                            Cell(Position(0, 2), CellType.NORMAL, 'C')
                        ),
                        listOf(
                            Cell(Position(1, 0), CellType.NORMAL, 'C'),
                            Cell(Position(1, 1), CellType.NORMAL, 'C'),
                            Cell(Position(1, 2), CellType.NORMAL, 'C')
                        ),
                        listOf(
                            Cell(Position(2, 0), CellType.NORMAL, 'C'),
                            Cell(Position(2, 1), CellType.NORMAL, 'C'),
                            Cell(Position(2, 2), CellType.NORMAL, 'C')
                        )
                    ),
                    9
                )
                actual shouldBe expect
            }
        }
    }

})
