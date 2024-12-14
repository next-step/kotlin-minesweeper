package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import minesweeper.config.BoardSize
import minesweeper.config.MinesWeeperSetting

class DefaultMineGeneratorTest : BehaviorSpec({
    Given("지뢰 생성기는") {
        When("인자로 받은 높이, 너비, 지뢰 개수 만큼") {
            val rows =
                listOf(
                    row(Height(3), Width(3), MineCount(3)),
                    row(Height(3), Width(2), MineCount(2)),
                    row(Height(5), Width(10), MineCount(10)),
                )
            Then("지뢰들을 생성한다.") {
                rows.forAll { (height, width, mineCount) ->
                    val size = BoardSize(height, width)
                    val setting = MinesWeeperSetting(size, mineCount)
                    DefaultMineGenerator().generate(setting).size shouldBe mineCount.count
                }
            }
        }
    }
})
