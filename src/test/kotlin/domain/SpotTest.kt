package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import vo.MineStatus
import vo.OpenStatus

class SpotTest {

    @Test
    fun `지뢰인지 아닌지 확인한다`() {
        val mineSpot = Spot(MineStatus.MINED)
        assertThat(mineSpot.isMine()).isTrue()
        val emptySpot = Spot(MineStatus.EMPTY)
        assertThat(emptySpot.isMine()).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8])
    fun `주변 지뢰 개수에 따라 표시 상태가 다르다`(nearMineCount: Int) {
        val spot = Spot(MineStatus.EMPTY)
        assertThat(spot.spotSymbol(nearMineCount)).isEqualTo(nearMineCount.toString())
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8])
    fun `지뢰가 있는 곳이면 지뢰 표시만 나타난다`(nearMineCount: Int) {
        val spot = Spot(MineStatus.MINED)
        assertThat(spot.spotSymbol(nearMineCount)).isEqualTo(OpenStatus.MINED.symbol)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 9])
    fun `범위 밖의 지뢰 개수를 입력하면 에러가 발생한다`(nearMineCount: Int) {
        val spot = Spot(MineStatus.EMPTY)
        assertThatIllegalArgumentException().isThrownBy {
            spot.spotSymbol(nearMineCount)
        }
    }
}
