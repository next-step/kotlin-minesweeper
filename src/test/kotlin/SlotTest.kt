import domain.Ground
import domain.Mine
import domain.Slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SlotTest {

    @Test
    fun `Slot이 주어졌을 때 지뢰 인지, 지뢰가 아닌지 확인 할 수 있다`() {
        val mineSlot: Slot = Mine(false)
        assertThat(mineSlot.isMine()).isTrue
        val groundSlot: Slot = Ground(false, 0)
        assertThat(groundSlot.isMine()).isFalse
    }

    @Test
    fun `Slot이 주어졌을 때 확인 여부를 알 수 있다`() {
        val mineSlot: Slot = Mine(false)
        assertThat(mineSlot.isChecked).isFalse
        val groundSlot: Slot = Ground(true, 0)
        assertThat(groundSlot.isChecked).isTrue
    }
}
