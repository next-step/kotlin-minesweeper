package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CellTest : BehaviorSpec({
    Given("위치와 지뢰 여부가 주어지면") {
        val position = Position(Size(1), Size(1))
        val isMine = true
        When("셀은") {
            val mine = Cell(position, isMine)
            Then("주어진 위치와 지뢰 여부를 갖는 갖는 셀이 생성된다.") {
                mine.position shouldBe position
                mine.isMine shouldBe isMine
            }
        }
    }
})
