package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class OneDimPinsTest : FunSpec({
    test("주입받은 숫자만큼 NormalPind 이 생성된다") {
        val size = 10

        val pinsInRow = OneDimPins.of(size)

        pinsInRow.getPinsSize() shouldBe size
        pinsInRow.getPinsBetween(0, size - 1).size shouldBe size
        (pinsInRow.getPinAt(0) is NormalPin) shouldBe true
    }

    test("정해진 범위만큼 핀을 list 타입으로 리턴받는다") {
        val size = 10
        val pinsInRow = OneDimPins.of(size)

        val pins = pinsInRow.getPinsBetween(1, size - 1)
        val targetPinAtIndexOne = pinsInRow.getPinAt(1)

        pins[0] shouldBe targetPinAtIndexOne
    }

    test("특정 index 에 위치한 일반핀을 지뢰로 바꿀 수 있다") {
        val size = 10
        val pinsInRow = OneDimPins.of(size)

        pinsInRow.changeMine(1)
        val targetPin = pinsInRow.getPinAt(1)

        (targetPin is MinePin) shouldBe true
    }
})
