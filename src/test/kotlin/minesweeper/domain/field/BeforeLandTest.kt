package minesweeper.domain.field

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

internal class BeforeLandTest {
    @Test
    fun `지도가 생성되지 않은 경우 출력되지 않는다`() {
        val beforeLand = BeforeLand()

        shouldThrow<IllegalStateException> { beforeLand.toString() }
    }

    @Test
    fun `Mine으로 설정할 수 있다`() {
        val beforeLand = BeforeLand()

        beforeLand.mine().shouldBeInstanceOf<Mine>()
    }

    @Test
    fun `Safe로 설정할 수 있다`() {
        val beforeLand = BeforeLand()

        beforeLand.safe(7).shouldBeInstanceOf<Safe>()
    }
}
