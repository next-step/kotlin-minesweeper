package domain.field

import domain.status.MineStatus
import domain.status.OpenStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SpotTest {

    @Test
    fun `지뢰인지 아닌지 확인한다`() {
        // given
        val mineSpot = Spot(MineStatus.MINED)
        val emptySpot = Spot(MineStatus.EMPTY)

        // then
        assertThat(mineSpot.isMine()).isTrue()
        assertThat(emptySpot.isMine()).isFalse()
    }

    @Test
    fun `Spot의 초기 상태는 항상 covered 상태이다`() {
        val spot = Spot(MineStatus.EMPTY)
        assertThat(spot.spotSymbol()).isEqualTo(OpenStatus.COVERED.symbol)
        assertThat(spot.isOpen()).isFalse()
    }

    @Test
    fun `spot이 covered가 아니면 isOpen이다`() {
        val spot = Spot(MineStatus.EMPTY)
        spot.open(0)
        assertThat(spot.isOpen()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5, 6, 7, 8])
    fun `spot을 open한다`(nearMine: Int) {
        val spot = Spot(MineStatus.EMPTY)
        val mineStatus = spot.open(nearMine)
        assertThat(mineStatus).isEqualTo(MineStatus.EMPTY)
        assertThat(spot.spotSymbol()).isEqualTo(OpenStatus.from(MineStatus.EMPTY, nearMine).symbol)
    }
}
