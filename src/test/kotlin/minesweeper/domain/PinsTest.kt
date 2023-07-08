package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.pin.MinePin
import minesweeper.domain.pin.NormalPin

class PinsTest : FunSpec({
    test("핀의 사이즈는 높이와 너비를 곱한 값과 같다") {
        val height = 10
        val width = 10
        val size = GameBoardSize(height, width)
        val board = Pins.of(size)

        val pinSize = board.getPinsSize()

        pinSize shouldBe (width * height)
    }

    test("특정 높이와 너비에 위치한 핀을 가져올 수 있다") {
        val size = GameBoardSize(10, 10)
        val board = Pins.of(size)

        val pin = board.getPinsAt(0, 1)

        (pin is NormalPin) shouldBe true
    }

    test("특정 높이와 너비에 위치한 핀의 경우 사이즈를 벗어난 경우 에러가 발생한다") {
        val size = GameBoardSize(10, 10)
        val board = Pins.of(size)

        shouldThrow<IllegalArgumentException> {
            board.getPinsAt(1000, 1)
        }
    }

    test("특정 행과 열에 위치한 값의 핀을 지뢰로 변경할 수 있다") {
        val size = GameBoardSize(10, 10)
        val board = Pins.of(size)

        board.changeMine(1, 1)

        val pin = board.getPinsAt(1, 1)
        val checkPin1 = board.getPinsAt(1, 0)
        val checkPin2 = board.getPinsAt(2, 0)

        (pin is MinePin) shouldBe true
        (checkPin1 is NormalPin) shouldBe true
        (checkPin2 is NormalPin) shouldBe true
        (checkPin1 as NormalPin).surroundMineNumber shouldBe 1
        (checkPin2 as NormalPin).surroundMineNumber shouldBe 1
    }

    test("모든 핀들을 초기에 닫혀있다") {
        val size = GameBoardSize(10, 10)
        val pins = Pins.of(size)

        for (i in 0 until 10) {
            for (j in 0 until 10) {
                val targetPin = pins.getPinsAt(i, j)
                targetPin.isOpenable() shouldBe true
            }
        }
    }
})
