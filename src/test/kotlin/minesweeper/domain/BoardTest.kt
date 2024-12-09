package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import minesweeper.domain.point.Mines
import minesweeper.fixture.boardFixture

class BoardTest : BehaviorSpec({

    Given("보드는 생성 시") {
        When("필요한 인자로 높이와 너비가 양수가 아니면") {
            val rows =
                listOf(
                    row(0, 0),
                    row(0, 1),
                    row(1, 0),
                    row(-1, 1),
                    row(1, -1),
                    row(-1, -1),
                )
            Then("예외를 발생시킨다.") {
                rows.forAll { (height, width) ->
                    shouldThrow<IllegalArgumentException> {
                        Board(
                            Height(height),
                            Width(width),
                            Mines(Height(height), Width(width), MineCount(0), DefaultMineGenerator()),
                        )
                    }
                }
            }
        }

        When("필요한 인자로 높이와 너비가 양수면") {
            val rows =
                listOf(
                    row(1, 1),
                    row(5, 30),
                    row(10, 10),
                )
            Then("정상 생성된다.") {
                rows.forAll { (height, width) ->
                    shouldNotThrow<IllegalArgumentException> {
                        Board(
                            Height(height),
                            Width(width),
                            Mines(Height(height), Width(width), MineCount(0), DefaultMineGenerator()),
                        )
                    }
                }
            }
        }
    }

    Given("보드는") {
        val board = boardFixture(width = 2, height = 2, mines = listOf(Pair(1, 0), Pair(0, 0)))

        Then("특정 좌표의 지뢰여보를 검증할 수 있다.") {
            forAll(
                row(0, 1),
                row(1, 1),
            ) { row, col ->
                board.isMine(row, col) shouldBe false
            }

            forAll(
                row(0, 0),
                row(1, 0),
            ) { row, col ->
                board.isMine(row, col) shouldBe true
            }
        }

        When("일부 땅을 공개하면") {
            forAll(
                row(0, 1),
            ) { row, col ->
                board.open(row, col)
            }
            Then("지뢰를 제외하고 더 공개할 땅이 있다.") {
                board.existUnopenedLand() shouldBe true
            }
        }

        When("모든 땅을 공개하면") {
            forAll(
                row(0, 1),
                row(1, 1),
            ) { row, col ->
                board.open(row, col)
            }
            Then("지뢰를 제외하고 더 공개할 땅이 없다.") {
                board.existUnopenedLand() shouldBe false
            }
        }
    }
})
