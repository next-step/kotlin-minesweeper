package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PositionsTest {
    @Test
    fun `인자로 들어온 list 가 바뀌어도 Positions 는 바뀌면 안됨`() {
        val mutablePositions = mutableListOf(Position.get(1, 2))
        val positions = Positions(mutablePositions)

        assertThat(positions.size).isEqualTo(mutablePositions.size)

        mutablePositions.add(Position.get(2, 3))
        assertThat(positions.size).isNotEqualTo(mutablePositions.size)
    }
}
