package domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MineMapInfoTest {

    @Test
    fun `0보다 작은 높이가 입력되면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            MineMapInfo(Point(0, 10), 1)
        }
    }

    @Test
    fun `0보다 작은 너비가 입력되면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            MineMapInfo(Point(10, 0), 1)
        }
    }

    @Test
    fun `0보다 작은 지뢰가 입력되면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            MineMapInfo(Point(10, 10), 0)
        }
    }

    @Test
    fun `지뢰가 맵의 칸 수보다 크면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            MineMapInfo(Point(10, 10), 101)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 100])
    fun `지뢰가 맵 칸 수 안에 있으면 정상적으로 생성된다`(mineCount: Int) {
        MineMapInfo(Point(10, 10), mineCount)
    }
}
