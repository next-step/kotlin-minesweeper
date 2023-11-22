package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineSweeperMapTest : BehaviorSpec({

    given("지뢰판(3x3)과 (1,2)가 지뢰일때") {
        val mineSweeperIndexes = listOf(
            MineSweeperIndex(1, 1, MineStatus.NOT_MINE),
            MineSweeperIndex(1, 2, MineStatus.MINE),
            MineSweeperIndex(1, 3, MineStatus.NOT_MINE),
            MineSweeperIndex(2, 1, MineStatus.NOT_MINE),
            MineSweeperIndex(2, 2, MineStatus.NOT_MINE),
            MineSweeperIndex(2, 3, MineStatus.NOT_MINE),
            MineSweeperIndex(3, 1, MineStatus.NOT_MINE),
            MineSweeperIndex(3, 2, MineStatus.NOT_MINE),
            MineSweeperIndex(3, 3, MineStatus.NOT_MINE)
        )
        When("지뢰판의 (1,2)를 열면") {
            val mineSweeperMap = MineSweeperMap(mineSweeperIndexes)
            val result = mineSweeperMap.open(Position(1, 2))
            Then("게임에서 진다.") {
                result shouldBe GameStatus.LOSE
            }
        }

        When("지뢰판의 (1,1)를 열면") {
            val mineSweeperMap = MineSweeperMap(mineSweeperIndexes)
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
        When("지뢰판의 (3,4)를 열면") {
            val mineSweeperMap = MineSweeperMap(mineSweeperIndexes)
            shouldThrow<IllegalArgumentException> {
                mineSweeperMap.open(Position(3, 4))
            }
        }
    }

    given("지뢰판(3x3)과 (1,2)가 지뢰일때") {
        val mineSweeperIndexes = listOf(
            MineSweeperIndex(1, 1, MineStatus.NOT_MINE),
            MineSweeperIndex(1, 2, MineStatus.MINE),
            MineSweeperIndex(1, 3, MineStatus.NOT_MINE),
            MineSweeperIndex(2, 1, MineStatus.NOT_MINE),
            MineSweeperIndex(2, 2, MineStatus.NOT_MINE),
            MineSweeperIndex(2, 3, MineStatus.NOT_MINE),
            MineSweeperIndex(3, 1, MineStatus.NOT_MINE),
            MineSweeperIndex(3, 2, MineStatus.NOT_MINE),
            MineSweeperIndex(3, 3, MineStatus.NOT_MINE)
        )

        When("지뢰판의 (3,3)를 열면") {
            val mineSweeperMap = MineSweeperMap(mineSweeperIndexes)
            val result = mineSweeperMap.open(Position(3, 3))
            Then("해당 인덱스 상태는 열리고 근처 지뢰개수가 0인 인덱스 6개가 열린다.") {
                result shouldBe GameStatus.CONTINUE
                mineSweeperMap.mineSweeperIndexes.filter { it.openStatus == PositionStatus.OPENED }.size shouldBe 6
            }
        }
    }
})
