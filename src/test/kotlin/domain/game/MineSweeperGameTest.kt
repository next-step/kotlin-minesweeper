package domain.game

import domain.MineSweeperInitProperty
import domain.map.Coordinate
import domain.map.RealMineMapFactory
import domain.math.toPositive
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineSweeperGameTest : BehaviorSpec({

    Given("1, 1에 지뢰가 존재할 때") {
        val initProperty = MineSweeperInitProperty(
            height = 10.toPositive(),
            width = 10.toPositive(),
            mineCount = 1.toPositive()
        )
        val mineMap = RealMineMapFactory { setOf(Coordinate(1, 1)) }.create(initProperty)
        val game = MineSweeperGame(mineMap)

        When("1, 1을 열면") {
            val openResult = game.open(Coordinate(1, 1))

            Then("지뢰를 열었다는 결과를 반환한다") {
                (openResult is OpenResult.MineOpened) shouldBe true
            }
        }
    }

    Given("3 * 3 맵에 지뢰가 4개 존재할 때") {
        val initProperty = MineSweeperInitProperty(
            height = 3.toPositive(),
            width = 3.toPositive(),
            mineCount = 4.toPositive()
        )
        val mockMineCoordinates = setOf(
            Coordinate(2, 0),
            Coordinate(2, 1),
            Coordinate(0, 2),
            Coordinate(1, 2),
        )
        /*
         C C *
         C C *
         * * C
         */

        When("0, 0을 열면") {
            val mineMap = RealMineMapFactory { mockMineCoordinates }.create(initProperty)
            val game = MineSweeperGame(mineMap)
            val result = game.open(Coordinate(0, 0)) as OpenResult.GroundOpened

            Then("(0, 0) (0, 1) (1, 0), (1, 1)이 열린다") {
                val cells = result.mapCapture.run {
                    listOf(cells[0][0], cells[0][1], cells[1][0], cells[1][1],)
                }
                cells.all { it.isOpen() } shouldBe true
            }
        }

        When("2, 2를 열면") {
            val mineMap = RealMineMapFactory { mockMineCoordinates }.create(initProperty)
            val game = MineSweeperGame(mineMap)
            val result = game.open(Coordinate(2, 2)) as OpenResult.GroundOpened

            Then("(2, 2) 셀만 열린다") {
                result.mapCapture.cells[2][2].isOpen() shouldBe true
                result.mapCapture.cells.flatten().count { it.isOpen() } shouldBe 1
            }
        }

        When("(0, 0)과 (2, 2)를 열면") {
            val mineMap = RealMineMapFactory { mockMineCoordinates }.create(initProperty)
            val game = MineSweeperGame(mineMap)
            game.open(Coordinate(0, 0))
            val result = game.open(Coordinate(2, 2))

            Then("지뢰를 모두 찾았다는 결과를 반환한다") {
                (result is OpenResult.AllMineFound) shouldBe true
            }
        }
    }
})
