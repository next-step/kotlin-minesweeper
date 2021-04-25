package domain.position

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class PositionTest {

    @Test
    fun aroundPosition() {
        val aroundPosition = Position(0, 1).aroundPositions()

        Assertions.assertThat(aroundPosition).containsExactlyInAnyOrder(
            Position(0, 0), Position(0, 2),
            Position(1, 0), Position(1, 1), Position(1, 2)
        )
    }
}
