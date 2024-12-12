package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import minesweeper.fixture.boardFixture

class BoardTest : BehaviorSpec({
    Given("보드는 생성 시") {
        When("인자로 넘겨받은 높이, 너비, 지뢰들로") {
            val rows =
                listOf(
                    row(2, 2),
                    row(4, 2),
                    row(100, 2),
                    row(50, 50),
                )
            Then("지뢰들과 땅들을 프로퍼티로 갖는 인스턴스로 생성된다.") {
                rows.forAll { (height, width) ->
                    val mines =
                        Mines(FakeMineGenerator(listOf(Point(0, 0))), Height(height), Width(width), MineCount(1))
                    val board = boardFixture(height, width, mines)

                    board.lands.elements.size + board.mines.elements.size shouldBe height * width
                }
            }
        }
    }

    Given("보드는") {
        val mines =
            Mines(FakeMineGenerator(listOf(Point(0, 0), Point(1, 0))), Height(1), Width(1), MineCount(1))
        val board = boardFixture(3, 3, mines)
        When("좌표 정보를 통해") {
            val mineRow = 0
            val mineCol = 0
            val landRow = 1
            val landCol = 1
            Then("지뢰인지 검증할 수 있다.") {
                board.isMine(mineRow, mineCol) shouldBe true
                board.isMine(landRow, landCol) shouldBe false
            }
        }

        When("일부 땅을 공개하면") {
            forAll(
                row(0, 1),
            ) { row, col ->
                board.openArea(row, col)
            }
            Then("지뢰를 제외하고 더 공개할 땅이 있다.") {
                board.canOpen() shouldBe true
            }
        }

        When("모든 땅을 공개하면") {
            forAll(
                row(0, 1),
                row(0, 2),
                row(1, 1),
                row(1, 2),
                row(2, 0),
                row(2, 1),
                row(2, 2),
            ) { row, col ->
                board.openArea(row, col)
            }
            Then("지뢰를 제외하고 더 공개할 땅이 없다.") {
                board.canOpen() shouldBe false
            }
        }
    }
})
