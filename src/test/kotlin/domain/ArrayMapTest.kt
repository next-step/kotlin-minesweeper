package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ArrayMapTest {

    private val arrayMap = ArrayMap(
        listOf(
            listOf(Spot(MineStatus.EMPTY), Spot(MineStatus.EMPTY), Spot(MineStatus.EMPTY)),
            listOf(Spot(MineStatus.EMPTY), Spot(MineStatus.EMPTY), Spot(MineStatus.EMPTY)),
        )
    )

    @Test
    fun `맵의 높이와 너비를 가져온다`() {
        assertThat(arrayMap.height).isEqualTo(2)
        assertThat(arrayMap.width).isEqualTo(3)
    }

    @ParameterizedTest
    @CsvSource(value = ["0, 0", "1, 0", "1, 2", "1, 1"])
    fun `맵에서 특정 위치를 가져온다`(y: Int, x: Int) {
        assertThat(arrayMap.getPoint(Point(y, x)).isMine()).isFalse()
    }

    @ParameterizedTest
    @CsvSource(value = ["-1, 0", "0, -1", "2, 0", "0, 3"])
    fun `맵에서 범위를 벗어나면 에러가 발생한다`(y: Int, x: Int) {
        assertThatIllegalArgumentException().isThrownBy {
            arrayMap.getPoint(Point(y, x))
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["-1, 0", "0, -1", "2, 0", "0, 3"])
    fun `범위를 벗어나면 null을 반환한다`(y: Int, x: Int) {
        assertThat(arrayMap.getPointOrNull(Point(y, x))).isNull()
    }
}
