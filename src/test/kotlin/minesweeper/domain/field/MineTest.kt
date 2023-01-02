package minesweeper.domain.field

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

internal class MineTest {
    @Test
    fun `Mine으로 설정할 수 없다`() {
        val mine = Mine()

        shouldThrow<IllegalStateException> { mine.mine() }
    }

    @Test
    fun `Safe로 설정할 수 없다`() {
        val mine = Mine()

        shouldThrow<IllegalStateException> { mine.safe(7) }
    }
}
