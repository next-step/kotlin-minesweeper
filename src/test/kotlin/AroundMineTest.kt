import domain.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AroundMineTest {

    @Test
    fun `AroundMine 주변 카운트 값 확인`() {
        val board = mutableMapOf(
            Point(0, 0) to NonMine(),
            Point(1, 0) to NonMine(),
            Point(0, 1) to NonMine(),
            Point(1, 1) to Mine(),
        )

        val countMine = AroundMine(board, Point(0, 0)).countMine()
        assertThat(countMine).isEqualTo(1)
    }
}
