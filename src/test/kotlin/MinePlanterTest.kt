import domain.MineCount
import domain.MinePlanter
import domain.Row
import domain.Space
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MinePlanterTest {
    @Test
    fun `주어진 개수만큼 지뢰 심어졌는지 확인`() {
        val minePlantedRow = MinePlanter.plantMines(
            Row.emptyRow(5),
            MineCount(2),
        )
        Assertions.assertThat(
            minePlantedRow.spaces.count { it is Space.Mine }
        ).isEqualTo(2)
    }
}
