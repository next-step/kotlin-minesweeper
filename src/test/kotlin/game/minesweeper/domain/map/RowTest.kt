package game.minesweeper.domain.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("지뢰 지도 한 줄")
internal class RowTest {

    @Test
    fun `생성자의 width 길이 만큼 fragment 생성`() {
        val row = Row.from(1, 6)
        assertThat(row.fragments).hasSize(6)
    }

    @Test
    fun `지뢰 매설`() {
        val row = Row.from(1, 6)
        row.setMines(listOf(Coordinate(1, 2), Coordinate(1, 3)))
        assertThat(row.fragments).hasSize(6)
        assertThat(row.fragments.filter { it.hasMine }).hasSize(2)
    }
}
