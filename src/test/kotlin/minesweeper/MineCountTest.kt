package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import minesweeper.domain.MineCount
import org.junit.jupiter.api.Test

class MineCountTest {
    @Test
    fun `mineCount는 지도 크기를 초과하면 예외가 발생한다`() {
        shouldThrow<IllegalArgumentException> { MineCount(-1) }
    }
}
