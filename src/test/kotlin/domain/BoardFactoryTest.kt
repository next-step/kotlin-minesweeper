package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class BoardFactoryTest {
    @Test
    fun `높이가 3 넓이가 2 마인의 위치가 ( (0,1), (1,2) )일때 (0,1), (1,2) 위치에 마인을 가진 3 * 2 의 지뢰찾기 보드가 생성된다`() {
        val board = BoardFactory.create(
            Width(2,),
            Height(3),
            MinesPosition(
                listOf(Position(0, 1), Position(1, 2))
            )
        )

        assertEquals(3, board.height)
        assertEquals(2, board.width)

        assertFalse(board.isMine(Position(0, 0)))
        assertTrue(board.isMine(Position(0, 1)))
        assertFalse(board.isMine(Position(0, 2)))

        assertFalse(board.isMine(Position(1, 0)))
        assertFalse(board.isMine(Position(1, 1)))
        assertTrue(board.isMine(Position(1, 2)))
    }
}
