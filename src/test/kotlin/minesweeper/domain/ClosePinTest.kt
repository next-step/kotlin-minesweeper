package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ClosePinTest : FunSpec({
    test("핀은 Close 핀으로 감싸서 숨길 수 있다") {
        // given
        val normalPin = NormalPin()
        val minePin = MinePin()

        // when
        val changedNormalPin = normalPin.changeToClosePin()
        val changedMinePin = minePin.changeToClosePin()

        // then
        changedMinePin.isMinePin() shouldBe true
        changedNormalPin.isMinePin() shouldBe false
        changedMinePin.open() shouldBe minePin
        changedNormalPin.open() shouldBe normalPin
    }
})
