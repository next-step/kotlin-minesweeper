package vo

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class MineMapInfoTest {

    @Test
    fun `0보다 작은 높이가 입력되면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            MineMapInfo(0, 10, 1)
        }
    }

    @Test
    fun `0보다 작은 너비가 입력되면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            MineMapInfo(10, 0, 1)
        }
    }

    @Test
    fun `0보다 작은 지뢰가 입력되면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            MineMapInfo(10, 10, 0)
        }
    }

    @Test
    fun `지뢰가 맵의 칸 수보다 크면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            MineMapInfo(10, 10, 101)
        }
    }
}
