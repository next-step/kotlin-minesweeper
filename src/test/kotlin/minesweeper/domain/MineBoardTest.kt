package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import minesweeper.view.BoardStatus

class MineBoardTest : BehaviorSpec({

    Given("높이와 너비, 지뢰 개수를 입력받아서") {
        val height = 5
        val width = 5
        val mineCount = 5
        val boardStatus = BoardStatus(height, width, mineCount)
        When("지뢰찾기 보드를 만들면") {
            val board = BoardGenerator.createBoard(boardStatus)
            Then("보드가 만들어진다.") {
                board.shouldNotBeNull()
            }
            And("요청한 사이즈대로 만들어진다.") {
                board.height shouldBe 5
                board.width shouldBe 5
            }
            And("생성된 보드의 지뢰 총 개수는 입력과 동일하다.") {
                board.boardInfo.sumOf { row -> row.rowInfo.count { cell -> cell is MineCell } } shouldBe 5
            }
        }
    }

    Given("지뢰의 개수가 보드판의 총 격자보다 큰 입력을 받아서") {
        val height = 5
        val width = 5
        val mineCount = 26
        When("보드판을 생성할 때") {
            val boardStatus = BoardStatus(height, width, mineCount)
            Then("IllegalArgumentException이 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    BoardGenerator.createBoard(boardStatus)
                }
            }
        }
    }
})
