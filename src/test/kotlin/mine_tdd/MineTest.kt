package mine_tdd

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
    fun `생성되지 않은 셀의 위치를 찾을 경우 null을 반환한다`() {
        val position = Position(6, 0)

        val board = Board.createBoard(Width(5), Height(5))

        Assertions.assertThat(board.findCell(position)).isNull()
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
}
