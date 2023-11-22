package minesweeper.tdddomain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.GameStatus
import minesweeper.domain.MineStatus
import minesweeper.domain.Position
import minesweeper.domain.PositionStatus

class MineSweeperMap2Test : BehaviorSpec({

    given("지뢰판(3x3)과 (1,2)가 지뢰일때") {
        val mineSweeperIndexes = listOf(
            MineSweeperIndex2(1, 1, MineStatus.NOT_MINE),
            MineSweeperIndex2(1, 2, MineStatus.MINE),
            MineSweeperIndex2(1, 3, MineStatus.NOT_MINE),
            MineSweeperIndex2(2, 1, MineStatus.NOT_MINE),
            MineSweeperIndex2(2, 2, MineStatus.NOT_MINE),
            MineSweeperIndex2(2, 3, MineStatus.NOT_MINE),
            MineSweeperIndex2(3, 1, MineStatus.NOT_MINE),
            MineSweeperIndex2(3, 2, MineStatus.NOT_MINE),
            MineSweeperIndex2(3, 3, MineStatus.NOT_MINE)
        )
        When("지뢰판의 (1,2)를 열면") {
            val mineSweeperMap = MineSweeperMap2(mineSweeperIndexes)
            val result = mineSweeperMap.open(Position(1, 2))
            Then("게임에서 진다.") {
                result shouldBe GameStatus.LOSE
            }
        }

        When("지뢰판의 (1,1)를 열면") {
            val mineSweeperMap = MineSweeperMap2(mineSweeperIndexes)
            val result = mineSweeperMap.open(Position(1, 1))
            Then("해당 인덱스 상태는 열리고 게임 상태는 계속이다.") {
                result shouldBe GameStatus.CONTINUE
                mineSweeperMap.mineSweeperIndexes.find {
                    it.position == Position(
                        1,
                        1
                    )
                }?.openStatus shouldBe PositionStatus.OPENED
            }
        }
    }
})
