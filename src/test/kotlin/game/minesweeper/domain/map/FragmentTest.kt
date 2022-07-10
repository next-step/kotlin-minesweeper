package game.minesweeper.domain.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("지뢰 지도 조각")
internal class FragmentTest {

    @Test
    fun `처음 생성하면 지뢰 없는 상태`() {
        val fragment = Fragment.of(1, 1)
        assertThat(fragment.hasMine()).isFalse
    }

    @Test
    fun `해당 좌표를 지뢰로 변환`() {
        var fragment: Fragment = Fragment.of(1, 1)
        assertThat(fragment.hasMine()).isFalse
        fragment = fragment.convertToMine()
        assertThat(fragment.hasMine()).isTrue
    }
}
