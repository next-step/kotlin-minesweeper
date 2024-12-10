package minesweeper.domain.point

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import minesweeper.domain.DefaultMineGenerator
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width

class MinesTest : BehaviorSpec({

    Given("지뢰들은") {
        When("유효한 지뢰 개수를 인자로 받아") {
            Then("생성한다.") {
                shouldNotThrow<IllegalArgumentException> {
                    Mines(
                        height = Height(5),
                        width = Width(4),
                        count = MineCount(12),
                        mineGenerator = DefaultMineGenerator(),
                    )
                }
            }
        }

        When("지뢰 개수는 전체 좌표 수보다 크면") {
            Then("에외를 발생 시킨다.") {
                shouldThrow<IllegalArgumentException> {
                    Mines(
                        height = Height(1),
                        width = Width(1),
                        count = MineCount(2),
                        mineGenerator = DefaultMineGenerator(),
                    )
                }
            }
        }
    }
})
