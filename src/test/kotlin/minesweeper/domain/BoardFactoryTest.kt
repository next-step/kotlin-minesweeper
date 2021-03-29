package minesweeper.domain

import minesweeper.domain.BoardBuilder.Companion.`⬜`
import minesweeper.domain.BoardBuilder.Companion.`💣`
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardFactoryTest {
    @Test
    internal fun `높이 너비만큼 셀을 만든다`() {
        assertThat(BoardFactory(10, 10).board(1)).hasSize(100)
    }

    @Test
    internal fun `지뢰 개수만큼 생성한다`() {
        val board: List<Cell> = BoardFactory(10, 10).board(bomb = 10)
        assertThat(board.filter { it.bomb }).hasSize(10)
    }

    @Test
    internal fun `지뢰는 최소 1개이상이어야 한다`() {
        assertThrows<IllegalArgumentException> { BoardFactory(10, 10).board(bomb = 0) }
    }

    @Test
    internal fun `너비와 높이는 1 이상이어야 한다`() {
        assertThrows<IllegalArgumentException> { BoardFactory(0, 10).board(bomb = 0) }
    }

    @Test
    internal fun `지뢰수는 전체 셀수보다 작아야 한다`() {
        assertThrows<IllegalArgumentException> { BoardFactory(2, 2).board(bomb = 4) }
    }

    @Test
    internal fun `셀 생성을 CellFactory 에게 위임한다`() {
        val board = board {
            row(`⬜`, `⬜`, `💣`)
        }.build()
        val width = 2
        val motherCells = BoardFactory(
            width, board.size / width,
            object : CellFactory {
                override fun cells(bomb: Int, matrix: Matrix): List<Cell> {
                    return board
                }
            }
        )
        assertThat(motherCells.board(1)).isEqualTo(Board(board, width))
    }

    /**
     * 0, 1, 💣, 💣
     * 0, 1, 2, 2
     * 0, 0, 0, 0
     */
    @Test
    @Disabled("다른 방식으로 검증필요")
    fun `옆 셀의 지뢰수가 기록되어 있다`() {
        val cells = board {
            row(`⬜`, `⬜`, `⬜`, `💣`, `💣`)
            row(`⬜`, `⬜`, `⬜`, `⬜`, `⬜`)
            row(`⬜`, `⬜`, `⬜`, `⬜`, `⬜`)
        }.cellFactory().cells(2, Matrix(4, 3))

        val width = 4
        val boardFactory = BoardFactory(
            width, cells.size / width,
            CellFactory.Default(
                RandomDoubles(
                    listOf(
                        1, 1, 0, 0,
                        1, 1, 1, 1,
                        1, 1, 1, 1
                    ).map { it.toDouble() } // 작은수가 지뢰
                )
            )
        )

        assertThat(boardFactory.board(2)).isEqualTo(Board(cells, width))
    }
}
