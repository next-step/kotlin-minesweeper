import domain.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MinesTest {
    @Test
    fun `Mines에 넣어준 지뢰의 위치를 가지는지 체크하면 true가 나와야함`() {
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
