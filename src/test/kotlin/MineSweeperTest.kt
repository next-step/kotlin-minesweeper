import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MineSweeperTest {

    @ParameterizedTest
    @MethodSource("minePositionsForDrawMap")
    fun `지뢰찾기 맵을 그린다`(minePositions: List<Position>) {
        val mineSweeper = MineSweeper(5, 5, minePositions.size) { _ -> minePositions }

        val cells = mineSweeper.cells()

        assertThat(cells)
            .isEqualTo(expectedMap(minePositions, 5, 5))
    }

    private fun expectedMap(minePositions: List<Position>, width:Int, height: Int): Map<Position, Status> {
        val expected = allPositions(width, height)
            .associateWith { Status.EMPTY }
            .toMutableMap()
        minePositions.forEach { expected[it] = Status.MINE }
        return expected
    }

    private fun allPositions(width: Int, height: Int): List<Position> {
        return (0 until width).flatMap { x ->
            (0 until height).map { y ->
                Position(y, x)
            }
        }
    }

    companion object {
        @JvmStatic
        fun minePositionsForDrawMap(): List<List<Position>> {
            return listOf(
                listOf(),
                listOf(Position(0, 0), Position(1, 1), Position(2, 2))
            )
        }
    }
}
