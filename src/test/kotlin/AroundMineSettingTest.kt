import domain.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AroundMineSettingTest {
    @Test
    fun `주위 마인 갯수 업데이트`() {
        val board = mutableMapOf(
            Point(0, 0) to NonMine(),
            Point(1, 0) to NonMine(),
            Point(0, 1) to NonMine(),
            Point(1, 1) to Mine(),
        )
        val aroundMineSetting = AroundMineSetting(board)

        assertThat(aroundMineSetting.board.getValue(Point(0, 0)).countAroundMine()).isEqualTo(1)
    }
}
