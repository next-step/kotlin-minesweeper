package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows

class PinsTest : FunSpec({
    test("pin 일급 컬렉션의 특정 index 에 위치한 NormalPin 프로퍼티를 수정할 수 있다") {
        val size = GameBoardSize(2, 2)
        val pins = Pins.of(size)

        pins.addMineNumber(0)

        (pins.getPinAt(0) as NormalPin).surroundMineNumber shouldBe 1
    }

    test("pin 일급 컬렉션의 index 가 벗어난 경우에는 에러가 발생한다") {
        val size = GameBoardSize(2, 2)
        val pins = Pins.of(size)

        pins.getPinsSize() shouldBe 4

        assertThrows<IllegalArgumentException> {
            pins.addMineNumber(100)
        }
    }
})
