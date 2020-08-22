package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MineMapTest {

    @DisplayName(value = "넓이와 높이를 입력했을때, 맵의 크기는 넓이x높아와 같아야한다.")
    @Test
    fun checkMinMapSize() {
        val height = 5
        val width = 5
        val map = MineMap(height, width)
        println(map.toShowString())
        assertThat(map.getMapSize()).isEqualTo(height * width)
    }
}
