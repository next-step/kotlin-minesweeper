package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import minesweeper.domain.Height
import org.junit.jupiter.api.Test

class HeightTest {
    @Test
    fun `height는 음수 값으로 생성 시 예외가 발생한다`() {
        shouldThrow<IllegalArgumentException> { Height(0) }
    }
}
