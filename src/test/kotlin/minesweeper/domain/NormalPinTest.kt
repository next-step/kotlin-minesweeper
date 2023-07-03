package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class NormalPinTest : FunSpec({
    test("pin 의 프로퍼티의 값을 증가시킬 수 있다") {
        val pin = NormalPin()

        pin.addSurroundMineNumber()
        pin.addSurroundMineNumber()

        pin.surroundMineNumber shouldBe 2
    }
})
