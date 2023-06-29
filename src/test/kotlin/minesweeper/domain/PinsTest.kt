package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PinsTest {
    @Test
    fun `10 * 10 사이즈로 핀들을 생성하면 총 100개의 핀이 생성된다`() {
        val height = 10
        val width = 10
        val size = GameBoardSize(height, width)
        val pins = Pins.of(size)

        pins.getPinsSize() shouldBe height * width
    }

    @Test
    fun `10 * 10 사이즈의 핀에서 가장 처음 위치를 Mine 으로 변경할 수 있다`() {
        val height = 10
        val width = 10
        val size = GameBoardSize(height, width)
        val pins = Pins.of(size)
        pins.changeMine(0, 0)

        val pin = pins.getPinAt(0, 0)

        (pin is MinePin) shouldBe true
    }
}
