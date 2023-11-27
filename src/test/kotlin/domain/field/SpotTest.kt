package domain.field

import domain.status.MineStatus
import domain.status.OpenStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
    }
}
