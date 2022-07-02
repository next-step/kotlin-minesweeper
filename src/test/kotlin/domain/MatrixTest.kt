package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MatrixTest {

    @Test
    fun `행렬은 너비와 높이를 곱한 값 만큼의 공간을 가진다`() {
        val dimension = Dimension(10, 10)
        val matrix = Matrix(dimension, MinesCounter(1))

        assertThat(matrix.cells.values.size).isEqualTo(100)
    }

    @Test
    fun `지뢰의 개수 + 땅은 전체 칸이다`() {
        val numberOfMines = MinesCounter(3)
        val dimension = Dimension(10, 10)
        val locationSelector = CustomLocationSelector(
            listOf(
                Location(LocationValue(0), LocationValue(0)),
                Location(LocationValue(1), LocationValue(1)),
                Location(LocationValue(2), LocationValue(2))
            )
        )
        val matrix = Matrix(dimension, numberOfMines, locationSelector)

        assertThat(matrix.cells.values.filter { it is Cell.Ground }.count()).isEqualTo(97)
        assertThat(matrix.cells.values.filter { it is Cell.Mine }.count()).isEqualTo(3)
    }

    class CustomLocationSelector(
        private val preparedLocations: List<Location>
    ) : LocationSelector {
        override fun select(numberOfSelection: Int, locations: List<Location>): List<Location> {
            require(preparedLocations.size == numberOfSelection)
            return preparedLocations
        }
    }
}
