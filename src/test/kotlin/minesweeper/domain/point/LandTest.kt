package minesweeper.domain.point

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import minesweeper.domain.FakeMineGenerator
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width

class LandTest : BehaviorSpec({
    Given("땅은") {
        When("좌표는 음수면.") {
            val rows = listOf(
                row(-1, 1),
                row(1, -1),
                row(-1, -1)
            )
            Then("생성 시 예외가 발생한다.") {
                rows.forEach { (row, col) ->
                    shouldThrow<IllegalArgumentException> {
                        Land(
                            r = row,
                            c = col,
                            mines = Mines(Height(1), Width(1), MineCount(1), FakeMineGenerator())
                        )
                    }
                }
            }
        }

        When("공개되었다면") {
            val land =
                Land(
                    r = 0,
                    c = 0,
                    mines = Mines(
                        Height(1), Width(1), MineCount(1), FakeMineGenerator(),
                    )
                ).apply {
                    open()
                }
            Then("공개된 상태를 갖는다.") {
                land.isOpened() shouldBe true
            }
        }

        When("주변에 지뢰가 있으면") {
            val land =
                Land(
                    r = 0,
                    c = 0,
                    mines = Mines(
                        Height(1),
                        Width(1),
                        MineCount(1),
                        FakeMineGenerator(listOf(Pair(1, 0), Pair(1, 1), Pair(0, 1),Pair(0, 3))),
                    )
                )
            Then("지뢰 개수 정보를 갖는다.") {
                land.aroundMineCount shouldBe 3
            }
        }
    }


})
