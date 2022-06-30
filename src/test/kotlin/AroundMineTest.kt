import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AroundMineTest {

    @Test
    fun `AroundMine 오브젝트 타입으로 생성`() {
        assertThat(AroundMine).isEqualTo(AroundMine)
    }
}
