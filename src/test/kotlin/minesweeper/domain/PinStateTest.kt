package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import minesweeper.domain.pin.state.PinState
import org.junit.jupiter.api.Test

class PinStateTest {
    @Test
    fun `CLOSE 상태의 핀만 OPEN 할 수 있다`() {
        val pinState = PinState.OPEN

        shouldThrow<IllegalArgumentException> {
            pinState.toOpen()
        }
    }

    @Test
    fun `Close 핀을 열면 Open 상태로 변경할 수 있다`() {
        var pinState = PinState.CLOSE

        pinState = pinState.toOpen()

        pinState shouldBe PinState.OPEN
    }
}
