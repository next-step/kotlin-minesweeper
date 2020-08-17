import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `보드에 지뢰 설치하기`() {
        val boardSize = BoardSize(LengthOfSide(10), LengthOfSide(10))
        val board = Board(boardSize).apply {
            make(listOf(1, 2, 3))
        }

        assertThat(board.grid.count { it == MineType.MINE.symbol }).isEqualTo(3)
    }
}

class Board(private val boardSize: BoardSize) {
    private val _grid = mutableListOf<Char>()
    val grid: List<Char> get() = _grid

    fun make(mineIndexes: List<Int>) {
        repeat(boardSize.get()) {
            _grid.add(MineType.NONE.symbol)
        }
        mineIndexes.take(mineIndexes.size).map {
            _grid[it] = MineType.MINE.symbol
        }
    }
}

enum class MineType(val symbol: Char) {
    NONE('C'),
    MINE('*')
}
