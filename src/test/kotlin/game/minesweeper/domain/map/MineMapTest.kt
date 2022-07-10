package game.minesweeper.domain.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("지뢰 지도 생성")
internal class MineMapTest {

    @Test
    fun `높이와 너비 만큼 맵 그리기`() {
        val config = MapConfig(5, 10)
        val map = MineMap.create(config)
        assertThat(map.rows).hasSize(5)
        assertThat(map.rows.first().fragments).hasSize(10)
    }

    @Test
    fun `입력한 좌표의 수 만큼 지뢰를 매설한다`() {
        val config = MapConfig(5, 10)
        val map = MineMap.create(config)
        map.setMines(listOf(Coordinate(1, 2), Coordinate(1, 3), Coordinate(5, 10)))
        assertThat(map.rows.flatMap { it.fragments }.filter { it.hasMine() }).hasSize(3)
    }
}
