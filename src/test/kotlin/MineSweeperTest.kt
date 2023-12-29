import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineSweeperTest {

    @Test
    fun `지뢰가 없는 맵을 그린다`() {
        val mineSweeper = MineSweeper(5, 5)

        val cells = mineSweeper.cells()

        assertThat(cells)
            .containsExactlyInAnyOrderEntriesOf(allPositions(5, 5).associateWith { "C" })
    }

    private fun allPositions(width: Int, height: Int): List<Position> {
        return (0 until width).flatMap { x ->
            (0 until height).map { y ->
                Position(y, x)
            }
        }
    }
}
