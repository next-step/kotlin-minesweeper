package game.minesweeper.domain.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("지뢰 지도 조각")
internal class FragmentTest {

    @Test
    fun `처음 생성하면 지뢰 없는 상태`() {
        val fragment = Fragment.of(1, 1)
        assertThat(fragment.hasMine).isFalse
    }

    @Test
    fun `지뢰 심기`() {
        val fragment = Fragment.of(1, 1)
        fragment.setMine()
        assertThat(fragment.hasMine).isTrue
    }
}
