import domain.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MinesTest {
    @Test
    fun `지뢰의 집합에서 지뢰가 있는 위치가 맞게 찾는지 테스트`() {
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
