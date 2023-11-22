package minesweeper.tdddomain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.MineStatus
import minesweeper.domain.Position
import minesweeper.domain.PositionStatus

class MineSweeperIndex2Test : BehaviorSpec({

    given("좌표(1,1)와 지뢰여부 상태가 주어졌을 때") {
        val position = Position(1, 1)
        val mineStatus = MineStatus.NOT_MINE
        When("지뢰 찾기 인덱스를 생성하면") {
            val mineSweeperIndex = MineSweeperIndex2(position, mineStatus)
            Then("좌표와 상태가 같다.") {
                mineSweeperIndex.position shouldBe position
                mineSweeperIndex.mineStatus shouldBe mineStatus
            }

            Then("열림 여부는 닫혀있다.") {
                mineSweeperIndex.openStatus shouldBe PositionStatus.CLOSED
            }
        }
    }

    given("좌표 (1,1)인 지뢰찾기 인덱스가 주어졌을때") {
        val mineSweeperIndex = MineSweeperIndex2(1, 1, MineStatus.NOT_MINE)
        When("열림 상태로 변경하면") {
            mineSweeperIndex.open()
            Then("열림 상태가 된다.") {
                mineSweeperIndex.openStatus shouldBe PositionStatus.OPENED
            }
        }
    }

    given("지뢰 인덱스(3x3)리스트가 주어지고 (3,3)가 지뢰일때") {
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
        val mineSweeperIndex = MineSweeperIndex2(1, 1, MineStatus.NOT_MINE)
        When("(1,1)좌표의 지뢰의 개수를 구하면") {
            val result = mineSweeperIndex.mineCount(mineSweeperIndexes)
            Then("지뢰의 개수는 0이다.") {
                result shouldBe 0
            }
        }

        When("(2,2)좌표의 지뢰의 개수를 구하면") {
            val result = MineSweeperIndex2(2, 2, MineStatus.NOT_MINE).mineCount(mineSweeperIndexes)
            Then("지뢰의 개수는 1이다.") {
                result shouldBe 1
            }
        }
    }

    given("지뢰 인덱스(1, 1) 지뢰일때") {
        val mineSweeperIndex = MineSweeperIndex2(1, 1, MineStatus.MINE)
        When("해당 좌표가 지뢰인지 판단하면") {
            val result = mineSweeperIndex.isMine()
            Then("지뢰이다.") {
                result shouldBe true
            }
        }
    }
})
