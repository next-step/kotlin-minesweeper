package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.tdddomain.MineSweeperIndex2
import minesweeper.tdddomain.MineSweeperMap2

class IndexSquareTest : BehaviorSpec({

    given("지뢰판(3x3)이 주어졌을때") {
        val mineSweeperIndexes = MineSweeperMap2(
            MineSweeperIndex2(1, 1, MineStatus.NOT_MINE),
            MineSweeperIndex2(1, 2, MineStatus.NOT_MINE),
            MineSweeperIndex2(1, 3, MineStatus.NOT_MINE),
            MineSweeperIndex2(2, 1, MineStatus.NOT_MINE),
            MineSweeperIndex2(2, 2, MineStatus.NOT_MINE),
            MineSweeperIndex2(2, 3, MineStatus.NOT_MINE),
            MineSweeperIndex2(3, 1, MineStatus.NOT_MINE),
            MineSweeperIndex2(3, 2, MineStatus.NOT_MINE),
            MineSweeperIndex2(3, 3, MineStatus.MINE)
        )

        When("지뢰판의 (1,1)의 주변 사각형을 구하면") {
            val mineSweeperIndex = MineSweeperIndex2(Position(1, 1), MineStatus.NOT_MINE)
            val result = IndexSquare.squareIndex(mineSweeperIndex, mineSweeperIndexes)
            Then("주변 인덱스 개수는 3이다.") {
                result.size shouldBe 3
            }
        }

        When("지뢰판의 (2,2)의 주변 사각형을 구하면") {
            val mineSweeperIndex = MineSweeperIndex2(Position(2, 2), MineStatus.NOT_MINE)
            val result = IndexSquare.squareIndex(mineSweeperIndex, mineSweeperIndexes)
            Then("주변 인덱스 개수는 8이다.") {
                result.size shouldBe 8
            }
        }

        When("지뢰판의 (3,3)의 주변 사각형을 구하면") {
            val mineSweeperIndex = MineSweeperIndex2(Position(3, 3), MineStatus.NOT_MINE)
            val result = IndexSquare.squareIndex(mineSweeperIndex, mineSweeperIndexes)
            Then("주변 인덱스 개수는 3이다.") {
                result.size shouldBe 3
            }
        }
    }
})
