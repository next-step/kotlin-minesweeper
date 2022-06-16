import domain.MinePosition
import domain.Mines
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MinesTest {
    @Test
    fun `가진 지뢰의 위치가 일치하면 참값이 나온다`() {
        val mines = Mines(
            listOf(
                MinePosition(0, 3),
                MinePosition(2, 7)
            ).toSet()
        )

        Assertions.assertThat(mines.contains(0, 3)).isEqualTo(true)
        Assertions.assertThat(mines.contains(2, 7)).isEqualTo(true)
    }
}
