package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class IndexSquareTest : BehaviorSpec({

    given("지뢰판(3x3)이 주어졌을때") {
        val mineSweeperIndexes = MineSweeperMap(
            MineSweeperIndex(1, 1, MineStatus.NOT_MINE),
            MineSweeperIndex(1, 2, MineStatus.NOT_MINE),
            MineSweeperIndex(1, 3, MineStatus.NOT_MINE),
            MineSweeperIndex(2, 1, MineStatus.NOT_MINE),
            MineSweeperIndex(2, 2, MineStatus.NOT_MINE),
            MineSweeperIndex(2, 3, MineStatus.NOT_MINE),
            MineSweeperIndex(3, 1, MineStatus.NOT_MINE),
            MineSweeperIndex(3, 2, MineStatus.NOT_MINE),
            MineSweeperIndex(3, 3, MineStatus.MINE)
        )

        When("지뢰판의 (1,1)의 주변 사각형을 구하면") {
            val mineSweeperIndex = MineSweeperIndex(Position(1, 1), MineStatus.NOT_MINE)
            val result = IndexSquare.squareIndex(mineSweeperIndex, mineSweeperIndexes)
            Then("주변 인덱스 개수는 3이다.") {
                result.size shouldBe 3
            }
        }

        When("지뢰판의 (2,2)의 주변 사각형을 구하면") {
            val mineSweeperIndex = MineSweeperIndex(Position(2, 2), MineStatus.NOT_MINE)
            val result = IndexSquare.squareIndex(mineSweeperIndex, mineSweeperIndexes)
            Then("주변 인덱스 개수는 8이다.") {
                result.size shouldBe 8
            }
        }

        When("지뢰판의 (3,3)의 주변 사각형을 구하면") {
            val mineSweeperIndex = MineSweeperIndex(Position(3, 3), MineStatus.NOT_MINE)
            val result = IndexSquare.squareIndex(mineSweeperIndex, mineSweeperIndexes)
            Then("주변 인덱스 개수는 3이다.") {
                result.size shouldBe 3
            }
        }
    }
})
