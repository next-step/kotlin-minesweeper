package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.config.BoardSize
import minesweeper.config.MinesWeeperSetting

class LandTest : BehaviorSpec({
    Given("땅은") {
        When("인자로 좌표, 지뢰들로") {
            val point = Point(1, 0)
            val points =
                listOf(
                    Point(0, 0),
                    Point(2, 0),
                    Point(1, 1),
                    Point(5, 5),
                )

            val size =
                BoardSize(
                    Height(1),
                    Width(1),
                )

            val setting =
                MinesWeeperSetting(
                    size,
                    MineCount(1),
                )
            val mines =
                Mines(FakeMineGenerator(points), setting)
            Then("생성 시 좌표, 주변 지뢰 개수, 공개 여부 정보를 프로퍼티로 갖는다.") {
                val land =
                    Land(
                        point = point,
                        aroundMineCount = mines.countAroundMines(point),
                    )
                land.point shouldBe point
                land.aroundMineCount shouldBe 3
            }
        }
    }
})
