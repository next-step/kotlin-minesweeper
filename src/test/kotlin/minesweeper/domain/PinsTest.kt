package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PinsTest : FunSpec({
    test("pin 일급 컬렉션에 특정 위치의 일반핀의 surroundMineNumber 프로퍼티 수정을 할 수 있다") {
        val size = GameBoardSize(2, 2)
        val pins = Pins.of(size)

        pins.addMineNumber(0)

        (pins.getPinAt(0) as NormalPin).surroundMineNumber shouldBe 1
    }
})
