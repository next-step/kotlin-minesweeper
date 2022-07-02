import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MineTest {

    @Test
    fun `마인 객체의 정보`() {
        val mine: Square = Mine()

        assertAll("정보 확인", {
            assertThat(mine.isMine()).isTrue
            assertThat(mine.display()).isEqualTo("*")
            assertThat(mine.countAroundMine()).isEqualTo(-1)
        })
    }
}
