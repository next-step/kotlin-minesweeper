import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NonMineTest {

    @Test
    fun `마인이 없는 객체의 정보`() {
        val nonMine: Square = NonMine()

        assertAll("정보 확인", {
            assertThat(nonMine.isMine()).isFalse
            assertThat(nonMine.display()).isEqualTo("C")
        })
    }
}
