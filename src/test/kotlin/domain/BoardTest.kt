package domain

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BoardTest {
    @Test
    fun `Row 의 넓이가 다를경우 에러`() {
        assertThrows<IllegalArgumentException> {
            Board(
                listOf(
                    Row(listOf(GeneralCell())),
                    Row(emptyList())
                )
            )
        }
    }

    @Test
    fun `인자로 건낸 Position 의 위치가 지뢰라면 true`() {
        val board = Board(
            listOf(
                Row(listOf(GeneralCell())),
                Row(listOf(MineCell()))
            )
        )

        val actual = board.isMine(Position(0, 1))

        assertTrue(actual)
    }

    @Test
    fun `인자로 건낸 Position 의 위치가 지뢰가 아니라면 false`() {
        val board = Board(
            listOf(
                Row(listOf(GeneralCell())),
                Row(listOf(MineCell()))
            )
        )

        val actual = board.isMine(Position(0, 0))

        assertFalse(actual)
    }
}
