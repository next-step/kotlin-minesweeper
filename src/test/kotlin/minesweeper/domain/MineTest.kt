package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineTest : BehaviorSpec({
    Given("위치와 지뢰 여부가 주어지면") {
        val position = Position(Size(1), Size(1))
        val isMine = true
        When("지뢰는") {
            val mine = Mine(position, isMine)
            Then("주어진 x좌표와 y좌표를 갖는 지뢰가 생성된다.") {
                mine.position shouldBe position
                mine.isMine shouldBe isMine
            }
        }
    }
})
