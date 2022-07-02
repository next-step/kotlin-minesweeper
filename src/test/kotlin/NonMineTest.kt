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
            assertThat(nonMine.countAroundMine()).isEqualTo("-1")
        })
    }

    @Test
    fun `마인이 없는 객체에 주변 마인 갯수 프로퍼티로 객체 생성`() {
        val nonMine: Square = NonMine(2)

        assertThat(nonMine.countAroundMine()).isEqualTo(2)
    }
}
