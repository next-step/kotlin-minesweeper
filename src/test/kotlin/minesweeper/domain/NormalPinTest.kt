package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class NormalPinTest : FunSpec({
    test("pin 의 surroundMineNumber 프로퍼티 연산이 가능하다") {
        val pin = NormalPin()

        pin.addsurroundMineNumber()

        pin.surroundMineNumber shouldBe 1
    }
})
