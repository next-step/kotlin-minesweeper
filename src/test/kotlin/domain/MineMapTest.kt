package domain

import domain.field.Point
import domain.field.Spot
import domain.map.ArrayMap
import domain.status.MineStatus
import domain.status.OpenStatus
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MineMapTest {

    // 10 x 10, 10개의 지뢰
    private val testMap = ArrayMap(
        listOf(
            listOf(0, 0, 1, 0, 0, 0, 0, 0, 0, 0).toMineMapLine(),
            listOf(0, 0, 0, 0, 0, 0, 1, 0, 0, 1).toMineMapLine(),
            listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0).toMineMapLine(),
            listOf(0, 1, 0, 0, 0, 1, 0, 0, 0, 0).toMineMapLine(),
            listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 1).toMineMapLine(),
            listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0).toMineMapLine(),
            listOf(0, 0, 0, 1, 0, 0, 0, 0, 0, 0).toMineMapLine(),
            listOf(0, 0, 0, 1, 0, 0, 0, 1, 0, 0).toMineMapLine(),
            listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0).toMineMapLine(),
            listOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0).toMineMapLine()
        )
    )

    private fun List<Int>.toMineMapLine(): List<Spot> = map {
        Spot(if (it == 1) MineStatus.MINED else MineStatus.EMPTY)
    }

    @ParameterizedTest
    @CsvSource(value = ["0, 10", "10, 0", "-1, 0", "0, -1"])
    fun `맵의 범위를 벗어난 범위의 지뢰를 확인하면 에러가 발생한다`(x: Int, y: Int) {
        val mineMap = MineMap(testMap)
        assertThatIllegalArgumentException().isThrownBy {
            mineMap.resultMineStatus(Point(y, x))
        }
    }

    @Test
    fun `맵의 높이와 너비를 가져온다`() {
        val mineMap = MineMap(testMap)
        assertThat(mineMap.getHeight()).isEqualTo(10)
        assertThat(mineMap.getWidth()).isEqualTo(10)
    }

    @Test
    fun `open한다`() {
        val mineMap = MineMap(testMap)
        mineMap.open(Point(0, 0))
        assertThat(mineMap.resultMineStatus(Point(0, 0))).isEqualTo(OpenStatus.ZERO.symbol)
        assertThat(mineMap.resultMineStatus(Point(0, 1))).isEqualTo(OpenStatus.ONE.symbol)
        assertThat(mineMap.resultMineStatus(Point(1, 0))).isEqualTo(OpenStatus.ZERO.symbol)
        assertThat(mineMap.resultMineStatus(Point(1, 1))).isEqualTo(OpenStatus.ONE.symbol)
    }
}
