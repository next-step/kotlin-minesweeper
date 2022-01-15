package mine

import mine.cell.Cells
import mine.cell.MineCell
import mine.cell.NoneCell
import mine.cell.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest {

    @Test
    fun `너비가 10, 높이가 10 크기의 게임이 보드가 생성된다`() {
        val width = 10
        val height = 10
        val board = Board.createBoard(Width(width), Height(height))

        assertThat(board.cells.row()).isEqualTo(width)
        assertThat(board.cells.column()).isEqualTo(height)
    }

    @Test
    fun `너비와 높이의 값이 0보다 작은 경우 예외가 발생한다`() {
        val width = -1
        val height = 10

        assertThrows<IllegalArgumentException> { Board.createBoard(Width(width), Height(height)) }
    }

    @Test
    fun `지뢰인 칸을 클릭한 경우 게임 끝난다`() {
        val board = Board(
            Cells(
                listOf(
                    MineCell(Position(0, 0)),
                    NoneCell(Position(1, 0), 0),
                    NoneCell(Position(2, 0), 0),
                    NoneCell(Position(3, 0), 0),
                    NoneCell(Position(0, 1), 0),
                    NoneCell(Position(0, 2), 0),
                    NoneCell(Position(0, 3), 0),
                    NoneCell(Position(1, 1), 0),
                    NoneCell(Position(1, 2), 0),
                    NoneCell(Position(1, 3), 0),
                )
            )
        )

        val position = Position(0, 0)
        val status = board.run {
            position.clickedCell()
        }

        assertThat(status).isEqualTo(GameStatus.GAMEOVER)
    }

    @Test
    fun `마지막 칸을 클릭한 경우 게임에서 승리한다`() {
        val board = Board(
            Cells(
                listOf(
                    NoneCell(Position(0, 0), 0),
                    NoneCell(Position(0, 1), 0).apply { isClicked = true },
                    NoneCell(Position(0, 2), 0).apply { isClicked = true },
                    NoneCell(Position(0, 3), 0).apply { isClicked = true },
                    NoneCell(Position(1, 0), 0).apply { isClicked = true },
                    NoneCell(Position(1, 1), 0).apply { isClicked = true },
                    NoneCell(Position(1, 2), 0).apply { isClicked = true },
                    NoneCell(Position(1, 3), 0).apply { isClicked = true },
                    NoneCell(Position(2, 0), 0).apply { isClicked = true },
                    NoneCell(Position(2, 1), 0).apply { isClicked = true },
                    NoneCell(Position(2, 2), 0).apply { isClicked = true },
                )
            )
        )

        val position = Position(0, 0)
        val status = board.run {
            position.clickedCell()
        }

        assertThat(status).isEqualTo(GameStatus.WIN)
    }

    @Test
    fun `지뢰가 아닌 칸을 클릭한 경우 게임이 계속 진행된다`() {
        val board = Board(
            Cells(
                listOf(
                    NoneCell(Position(0, 0), 0),
                    NoneCell(Position(0, 1), 0),
                    NoneCell(Position(0, 2), 0),
                    NoneCell(Position(0, 3), 0),
                    NoneCell(Position(1, 0), 0),
                    NoneCell(Position(1, 1), 0),
                    NoneCell(Position(1, 2), 0),
                    NoneCell(Position(1, 3), 0),
                    NoneCell(Position(2, 0), 0),
                    NoneCell(Position(2, 1), 0),
                    NoneCell(Position(2, 2), 0),
                    MineCell(Position(2, 3)),
                )
            )
        )

        val position = Position(0, 0)
        val status = board.run {
            position.clickedCell()
        }

        assertThat(status).isEqualTo(GameStatus.CONTINUE)
    }

    @Test
    fun `지뢰가 없는 인접한 칸은 모두 open된다`() {
        val board = Board(
            Cells(
                listOf(
                    NoneCell(Position(0, 0), 0),
                    NoneCell(Position(0, 1), 0),
                    NoneCell(Position(0, 2), 0),
                    NoneCell(Position(0, 3), 0),
                    NoneCell(Position(1, 0), 0),
                    NoneCell(Position(1, 1), 0),
                    NoneCell(Position(1, 2), 1),
                    NoneCell(Position(1, 3), 1),
                    NoneCell(Position(2, 0), 0),
                    NoneCell(Position(2, 1), 1),
                    NoneCell(Position(2, 2), 3),
                    MineCell(Position(2, 3)),
                    NoneCell(Position(3, 0), 0),
                    NoneCell(Position(3, 1), 1),
                    MineCell(Position(3, 2)),
                    MineCell(Position(3, 3))
                )
            )
        )

        val position = Position(0, 0)
        board.run {
            position.clickedCell()
        }

        assertThat(board.cells.findCell(Position(0, 0))?.isClicked).isEqualTo(true)
        assertThat(board.cells.findCell(Position(0, 1))?.isClicked).isEqualTo(true)
        assertThat(board.cells.findCell(Position(0, 2))?.isClicked).isEqualTo(true)
        assertThat(board.cells.findCell(Position(0, 3))?.isClicked).isEqualTo(true)
        assertThat(board.cells.findCell(Position(1, 0))?.isClicked).isEqualTo(true)
        assertThat(board.cells.findCell(Position(1, 1))?.isClicked).isEqualTo(true)
        assertThat(board.cells.findCell(Position(2, 1))?.isClicked).isEqualTo(true)
        assertThat(board.cells.findCell(Position(3, 0))?.isClicked).isEqualTo(true)
    }
}
