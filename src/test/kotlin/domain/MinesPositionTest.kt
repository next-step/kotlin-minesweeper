package domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

    @Test
    fun `생성요청한 마인의 개수가 셀의 개수를 초과할 때 에러`() {
        assertThrows<IllegalArgumentException> {
            MinesPosition.generate(
                Width(0),
                Height(0),
                1
            )
        }
    }

    @Test
    fun `생성요청한 마인의 개수가 셀의 개수를 초과하지 않는다면 마인의 개수만큼 포지션 생성`() {
        val minesPosition = MinesPosition.generate(
            Width(2),
            Height(2),
            1
        )

        Assertions.assertEquals(1, minesPosition.size)
    }
}
