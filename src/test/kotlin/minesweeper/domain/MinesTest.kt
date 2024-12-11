package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MinesTest : BehaviorSpec({
    Given("지뢰들은") {
        When("지뢰 리스트를") {
            val mineList = listOf(Mine(Point(1, 0)), Mine(Point(2, 0)), Mine(Point(3, 0)))
            Then("생성 후 프로퍼티로 갖는다.") {
                val mines = Mines(mineList)

                mines.placedMines.size shouldBe mineList.size
            }
        }
    }
})
