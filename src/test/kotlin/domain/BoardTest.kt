package domain

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
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

    @Test
    fun `높이가 3 넓이가 2 마인의 위치가 ( (0,1), (1,2) )일때 (0,1), (1,2) 위치에 마인을 가진 3 * 2 의 지뢰찾기 보드가 생성된다`() {
        val board = Board.build(
            Width(2),
            Height(3),
            MinesPosition(
                listOf(Position(0, 1), Position(1, 2))
            )
        )

        assertAll(
            "보드 생성 테스트",
            { assertEquals(3, board.height) },
            { assertEquals(2, board.width) },
            { assertFalse(board.isMine(Position(0, 0))) },
            { assertTrue(board.isMine(Position(0, 1))) },
            { assertFalse(board.isMine(Position(0, 2))) },
            { assertFalse(board.isMine(Position(1, 0))) },
            { assertFalse(board.isMine(Position(1, 1))) },
            { assertTrue(board.isMine(Position(1, 2))) },
        )
    }

    @Test
    fun `지뢰가 없는 인접한 칸이 모두 열리게 된다`() {
        val board = Board(
            listOf(
                Row(listOf(GeneralCell(0), GeneralCell(1), GeneralCell(3), GeneralCell(3), GeneralCell(3))),
                Row(listOf(GeneralCell(0), GeneralCell(1), GeneralCell(3), GeneralCell(3), GeneralCell(3))),
                Row(listOf(GeneralCell(0), GeneralCell(1), GeneralCell(3), GeneralCell(3), GeneralCell(3))),
                Row(listOf(GeneralCell(1), GeneralCell(1), GeneralCell(3), GeneralCell(3), GeneralCell(3))),
                Row(listOf(GeneralCell(3), GeneralCell(3), GeneralCell(3), GeneralCell(3), GeneralCell(3)))
            )
        )

        board.open(Position(1, 1))

        board.rows.forEach { row ->
            val cells = row.cells
            cells.forEach { print("${it.isOpen()} ") }
            println()
        }
    }
}
