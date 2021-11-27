package domain

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MinesPositionTest {
    @Test
    fun `인자로 건낸 Position 이 마인의 위치라면 true`() {
        val minesPosition = MinesPosition(
            listOf(Position(1, 2))
        )

        val actual = minesPosition.contains(Position(1, 2))

        assertTrue(actual)
    }

    @Test
    fun `인자로 건낸 Position 이 마인의 위치가 아니라면 false`() {
        val minesPosition = MinesPosition(
            listOf(Position(1, 2))
        )

        val actual = minesPosition.contains(Position(0, 2))

        assertFalse(actual)
    }
}
