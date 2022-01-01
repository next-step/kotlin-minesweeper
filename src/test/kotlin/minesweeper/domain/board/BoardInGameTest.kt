package minesweeper.domain.board

import minesweeper.domain.cell.BlockCell
import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.FlagCell
import minesweeper.domain.cell.MineCell
import minesweeper.domain.position.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@Suppress("NonAsciiCharacters")
class BoardInGameTest {

    @Test
    fun `지뢰가 아닌 Cell을 open하면 게임이 끝나지 않는다`() {
        val cells = Cells(
            mapOf(
                Position.from(row = 1, column = 1) to BlockCell(MineCount(1)),
                Position.from(row = 1, column = 2) to MineCell(),
                Position.from(row = 2, column = 1) to BlockCell(MineCount(1)),
                Position.from(row = 2, column = 2) to BlockCell(MineCount(1)),
            )
        )
        val board = BoardInGame(cells)
        val result = board.open(Position.from(row = 1, column = 1))

        assertThat(result.isFinish).isFalse
    }

    @Test
    fun `지뢰인 Cell을 open하면 게임에서 패배한다`() {
        val cells = Cells(
            mapOf(
                Position.from(row = 1, column = 1) to BlockCell(MineCount(1)),
                Position.from(row = 1, column = 2) to MineCell(),
            )
        )
        val board = BoardInGame(cells)
        val result = board.open(Position.from(row = 1, column = 2))

        assertAll(
            { assertThat(result.isFinish).isTrue },
            { assertThat(result).isInstanceOf(BoardResult.Lose::class.java) },
        )
    }

    @Test
    fun `지뢰인 Cell을 open하면 게임에서 패배한다 - Flag일 경우`() {
        val cells = Cells(
            mapOf(
                Position.from(row = 1, column = 1) to BlockCell(MineCount(1)),
                Position.from(row = 1, column = 2) to FlagCell(MineCell()),
            )
        )
        val board = BoardInGame(cells)
        val result = board.open(Position.from(row = 1, column = 2))

        assertAll(
            { assertThat(result.isFinish).isTrue },
            { assertThat(result).isInstanceOf(BoardResult.Lose::class.java) },
        )
    }

    @Test
    fun `지뢰가 아닌 모든 Cell을 open 했다면 승리한다`() {
        val cells = Cells(
            mapOf(
                Position.from(row = 1, column = 1) to BlockCell(MineCount(1), isOpen = true),
                Position.from(row = 1, column = 2) to MineCell(),
                Position.from(row = 2, column = 1) to BlockCell(MineCount(1), isOpen = true),
                Position.from(row = 2, column = 2) to BlockCell(MineCount(1)),
            )
        )
        val board = BoardInGame(cells)
        val result = board.open(Position.from(row = 2, column = 2))

        assertAll(
            { assertThat(result.isFinish).isTrue },
            { assertThat(result).isInstanceOf(BoardResult.Win::class.java) },
        )
    }

    @Test
    fun `지뢰가 아닌 모든 Cell을 open 했다면 승리한다 - Flag된 MineCell을 포함하는 경우`() {
        val cells = Cells(
            mapOf(
                Position.from(row = 1, column = 1) to BlockCell(MineCount(1), isOpen = true),
                Position.from(row = 1, column = 2) to MineCell(),
                Position.from(row = 2, column = 1) to BlockCell(MineCount(1), isOpen = true),
                Position.from(row = 2, column = 2) to BlockCell(MineCount(1)),
                Position.from(row = 1, column = 2) to FlagCell(MineCell()),
            )
        )
        val board = BoardInGame(cells)
        val result = board.open(Position.from(row = 2, column = 2))

        assertAll(
            { assertThat(result.isFinish).isTrue },
            { assertThat(result).isInstanceOf(BoardResult.Win::class.java) },
        )
    }
}
