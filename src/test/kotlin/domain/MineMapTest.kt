package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import vo.Spot

class MineMapTest {

    // 10 x 10, 10개의 지뢰
    private val testMap = listOf(
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

    private fun List<Int>.toMineMapLine(): List<Spot> = map { Spot(it == 1) }

    @ParameterizedTest
    @CsvSource(value = ["0, 10", "10, 0", "-1, 0", "0, -1"])
    fun `맵의 범위를 벗어난 범위의 지뢰를 확인하면 에러가 발생한다`(x: Int, y: Int) {
        val mineMap = MineMap(testMap)
        assertThatIllegalArgumentException().isThrownBy {
            mineMap.isMineOn(x, y)
        }
    }

    @Test
    fun `맵의 지뢰 수는 MineMapInfo의 값과 동일하다`() {
        val mineMap = MineMap(testMap)
        var mineCount = 0
        repeat(100) { i ->
            if (mineMap.isMineOn(i % 10, i / 10) == "*") {
                mineCount++
            }
        }
        assertThat(mineCount).isEqualTo(10)
    }

    @Test
    fun `맵의 높이와 너비를 가져온다`() {
        val mineMap = MineMap(testMap)
        assertThat(mineMap.getHeight()).isEqualTo(10)
        assertThat(mineMap.getWidth()).isEqualTo(10)
    }
}
