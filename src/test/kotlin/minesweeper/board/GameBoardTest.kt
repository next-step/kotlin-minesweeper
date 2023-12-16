package minesweeper.board

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class GameBoardTest: BehaviorSpec ({

    Given("높이와 너비가 주어진다면") {
        val width = Element(3)
        val height = Element(3)
        When("게임판 생성 전략을 통해") {
            Then("2차원의 문자를 가지는 게임판을 생성한다.") {
                val actual = GameBoard(defaultGameBoardRenderStrategy(width, height, 'C'))
                val expect = GameBoard(
                    arrayOf(
                        arrayOf('C','C','C'),
                        arrayOf('C','C','C'),
                        arrayOf('C','C','C')
                    )
                )
                actual shouldBe expect
            }
        }
    }

})
