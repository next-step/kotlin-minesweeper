import domain.Ground
import domain.Mine
import domain.Point
import domain.Slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SlotTest {

    @Test
    fun `Slot이 주어졌을 때 지뢰 인지, 지뢰가 아닌지 확인 할 수 있다`() {
        val mineSlot: Slot = Mine(false, Point(0, 0))
        assertThat(mineSlot.isMine()).isTrue
        val groundSlot: Slot = Ground(false, Point(0, 0))
        assertThat(groundSlot.isMine()).isFalse
    }

    @Test
    fun `Slot이 주어졌을 때 확인 여부를 알 수 있다`() {
        val mineSlot: Slot = Mine(false, Point(0, 0))
        assertThat(mineSlot.isChecked).isFalse
        val groundSlot: Slot = Ground(true, Point(0, 0))
        assertThat(groundSlot.isChecked).isTrue
    }

    @Test
    fun `Slot이 주어졌을 때 좌표를 확인 할 수 있다`() {
        val slot1: Slot = Mine(false, Point(1, 4))
        assertThat(slot1.point()).isEqualTo(Point(1, 4))
        val slot2: Slot = Ground(true, Point(5, 6))
        assertThat(slot2.point()).isEqualTo(Point(5, 6))
    }
}
