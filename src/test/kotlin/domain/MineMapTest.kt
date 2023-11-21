package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import vo.MineMapInfo

class MineMapTest {

    @ParameterizedTest
    @CsvSource(value = ["0, 10", "10, 0", "-1, 0", "0, -1"])
    fun `맵의 범위를 벗어난 범위의 지뢰를 확인하면 에러가 발생한다`(x: Int, y: Int) {
        val mineMap = MineMap(MineMapInfo(10, 10, 10))
        assertThatIllegalArgumentException().isThrownBy {
            mineMap.isMineOn(x, y)
        }
    }

    @Test
    fun `맵의 지뢰 수는 MineMapInfo의 값과 동일하다`() {
        val mineMap = MineMap(MineMapInfo(10, 10, 10))
        var mineCount = 0
        repeat(100) { i ->
            if (mineMap.isMineOn(i % 10, i / 10) == "*") {
                mineCount++
            }
        }
        assertThat(mineCount).isEqualTo(10)
    }
}
