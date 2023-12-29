package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MineSweeperGameTest : BehaviorSpec({
    Given("지뢰가 있는지 알고 싶은 위치가 주어지면") {
        val height = Size(10)
        val width = Size(10)
        val mineCount = Size(10)

        val minePositions = FixedPositionGenerator(height, width).generate(mineCount)
        val cellFinder = CellFinder.init(height, width)
        cellFinder.convert(minePositions)
        When("지뢰찾기게임은") {
            val mineSweeperGame = MineSweeperGame(cellFinder)
            Then("해당 위치의 지뢰 유무를 반환한다.") {
                forAll(
                    row(Position(1, 2), true),
                    row(Position(-1, -1), false),
                    row(Position(5, 5), false),
                ) { position, expected ->
                    mineSweeperGame.isMine(position) shouldBe expected
                }
            }
        }
    }
})
