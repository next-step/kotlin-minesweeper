package mine_tdd

import mine_tdd.Board.Companion.findMineCount
import mine_tdd.cell.Cells
import mine_tdd.cell.MineCell
import mine_tdd.cell.NoneCell
import mine_tdd.cell.Position
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MineTest {
    @Test
    fun `너비가 5인 객체가 생성된다`() {
        val widthVal = 5
        val width = Width(widthVal)

        Assertions.assertThat(width.value()).isEqualTo(widthVal)
    }

    @Test
    fun `높이가 5인 객체가 생성된다`() {
        val heightVal = 5
        val height = Height(heightVal)

        Assertions.assertThat(height.value()).isEqualTo(heightVal)
    }

    @Test
    fun `너비가 5, 높이가 5 크기의 25개의 셀을 가진 게임이 보드가 생성된다`() {
        val width = 5
        val height = 5
        val board = Board.createBoard(Width(width), Height(height))

        Assertions.assertThat(board.row()).isEqualTo(width)
        Assertions.assertThat(board.column()).isEqualTo(height)
        Assertions.assertThat(board.cells.size()).isEqualTo(25)
    }

    @Test
    fun `25개의 셀 중에 지뢰를 5개 가진 게임이 보드가 생성된다`() {
        val mine = 5
        val board = Board.createBoard(Width(5), Height(5), Mine(mine))

        Assertions.assertThat(board.cells.size()).isEqualTo(25)
        Assertions.assertThat(board.cells.getMineCellCount()).isEqualTo(5)
    }

    @Test
    fun `Position()에는 음수를 입력할 수 없다`() {
        val x = -1
        val y = 0

        assertThrows<IllegalArgumentException> { Position(x, y) }
    }

    @Test
    fun `셀의 위치 정보를 통해 찾을 수 있다`() {
        val position = Position(0, 0)

        val board = Board.createBoard(Width(5), Height(5))

        Assertions.assertThat(board.findCell(position)).isNotNull
    }

    @Test
    fun `첫 번째 열에 해당하는 cell들을 가져올 수 있다`() {
        val row = 1
        val width = 5
        val board = Board.createBoard(Width(width), Height(5))

        val cells = board.getRowCells(row)

        Assertions.assertThat(cells.size()).isEqualTo(width)
    }

    @Test
    fun `2번째 줄에 해당하는 cell들을 가져올 수 있다`() {
        val column = 2
        val height = 5
        val board = Board.createBoard(Width(5), Height(height))

        val cells = board.getColumnCells(column)

        Assertions.assertThat(cells.size()).isEqualTo(height)
    }

    @Test
    fun `Positiion(1,1) 주변 Position의 지뢰 개수를 구할 수 있다`() {
        val position = Position(1, 1)
        val mineCellList = listOf(
            MineCell(Position(0, 0)),
            MineCell(Position(1, 0)),
            MineCell(Position(4, 0)),
        )

        val count = position.findMineCount(mineCellList)

        Assertions.assertThat(count).isEqualTo(2)
    }

    @Test
    fun `지뢰를 클릭하면 게임이 끝난다`() {
        val position = Position(2, 2)
        val board = Board(
            Width(2),
            Height(2),
            Cells(
                listOf(
                    NoneCell(Position(0, 0), 0),
                    NoneCell(Position(0, 1), 0),
                    MineCell(Position(2, 2)),
                )
            )
        )
        val expected = GameStatus.OVER

        val status = board.clickedItem(position)

        Assertions.assertThat(status).isEqualTo(expected)
    }

    @Test
    fun `일반 셀을 클릭하고, 지뢰를 제외한 모든 셀이 열린 경우 게임에서 승리한다`() {
        val position = Position(0, 0)
        val board = Board(
            Width(2),
            Height(2),
            Cells(
                listOf(
                    NoneCell(Position(0, 0), 0),
                    NoneCell(Position(0, 1), 0),
                    MineCell(Position(2, 2)),
                )
            )
        )
        val expected = GameStatus.WIN

        val status = board.clickedItem(position)

        Assertions.assertThat(status).isEqualTo(expected)
    }

    @Test
    fun `일반 셀을 클릭하고, 지뢰를 제외한 모든 셀이 열라지 않은 경우 게임은 계속된다`() {
        val position = Position(0, 0)
        val board = Board(
            Width(2),
            Height(2),
            Cells(
                listOf(
                    NoneCell(Position(0, 0), 0),
                    MineCell(Position(1, 1)),
                    NoneCell(Position(0, 1), 0),
                    MineCell(Position(2, 2)),
                    NoneCell(Position(2, 4), 0),
                )
            )
        )
        val expected = GameStatus.CONTINUE

        val status = board.clickedItem(position)

        Assertions.assertThat(status).isEqualTo(expected)
    }
}
