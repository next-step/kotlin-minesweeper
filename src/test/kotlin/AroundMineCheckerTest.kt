import domain.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class AroundMineCheckerTest {

    @Test
    fun `특정 좌표에 대한 마인 갯수 확인`() {
        val board = mutableMapOf(
            Point(0, 0) to NonMine(),
            Point(1, 0) to NonMine(),
            Point(0, 1) to NonMine(),
            Point(1, 1) to Mine(),
        )
        val aroundMineSetting = AroundMineSetting(board)

        val aroundMineChecker = AroundMineChecker(aroundMineSetting.board, Point(0, 0))
        val pointSquareMap = aroundMineChecker.getBoard()

        assertAll("주변 마인 갯수 확인", {
            assertThat(pointSquareMap.getValue(Point(0, 0)).countAroundMine()).isEqualTo(1)
            assertThat(pointSquareMap.getValue(Point(1, 0)).countAroundMine()).isEqualTo(1)
            assertThat(pointSquareMap.getValue(Point(0, 1)).countAroundMine()).isEqualTo(1)
        })
    }
}
