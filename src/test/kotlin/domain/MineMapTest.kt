package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import vo.MineMapInfo

class MineMapTest {

    @Test
    fun `맵의 높이보다 크거나 같은 값을 요청하면 에러가 발생한다`() {
        val mineMap = MineMap(MineMapInfo(10, 10, 10))
        assertThatIllegalArgumentException().isThrownBy {
            mineMap.mapByLine(10)
        }
    }

    @Test
    fun `맵의 지뢰 수는 MineMapInfo의 값과 동일하다`() {
        val mineMap = MineMap(MineMapInfo(10, 10, 10))
        var mineCount = 0
        for (h in 0 until 10) {
            mineCount += mineMap.mapByLine(h).filter { it == 1 }.size
        }
        assertThat(mineCount).isEqualTo(10)
    }
}
