package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PinsInRowTest : FunSpec({
    test("주입받은 숫자만큼 NormalPin 이 생성된다") {
        val size = 10

        val pinsInRow = PinsInRow.of(size)

        pinsInRow.getPinsSize() shouldBe size
    }

    test("정해진 범위만큼 핀을 리턴받는다") {
        val size = 10
        val pinsInRow = PinsInRow.of(size)

        val pins = pinsInRow.getPinsBetween(1, size - 1)

        pins.size shouldBe (size - 1)
    }

    test("특정 index 에 위치한 일반핀을 지뢰로 바꿀 수 있다") {
        val size = 10
        val pinsInRow = PinsInRow.of(size)

        pinsInRow.changeMine(1)
        val targetPin = pinsInRow.getPinAt(1)

        targetPin.isMinePin() shouldBe true
    }
})
