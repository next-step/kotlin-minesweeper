import domain.Spaces
import domain.Space
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SpacesTest {
    @Test
    fun `emptyRow가 주어진 size만큼 생성되어야 한다`() {
        assertThat(
            Spaces.emptySpaces(5).list()
        )
            .size()
            .isEqualTo(5)
    }

    @Test
    fun `emptyRow에는 Space_Empty만 들어있어야 한다`() {
        assertThat(
            Spaces.emptySpaces(5)
                .list()
                .all { it is Space.Empty }
        ).isTrue()
    }

    @Test
    fun `plantMines 테스트`() {
        val indexToPlantMine = listOf(1, 4)
        val minePlantedSpaces = Spaces.emptySpaces(5).apply {
            plantMinesAt(indexToPlantMine)
        }
        minePlantedSpaces.list()
            .forEachIndexed { index, space ->
                if (index in indexToPlantMine) assertThat(space).isEqualTo(Space.Mine)
                else assertThat(space).isEqualTo(Space.Empty)
            }
    }
}
