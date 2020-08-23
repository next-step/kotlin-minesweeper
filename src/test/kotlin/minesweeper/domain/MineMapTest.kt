package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MineMapTest {

    @DisplayName(value = "이와 높이를 입력했을때, 맵의 크기는 넓이x높아와 같아야한다.")
    @Test
    fun checkMinMapSize() {
        val height = 5
        val width = 5
        val map = MineMap(height, width)
        println(map)
        assertThat(map.getMapSize()).isEqualTo(height * width)
    }

    @DisplayName(value = "지뢰를 설정한 후, 전체 Cells의 크기는 같아야한다.")
    @Test
    fun checkSetMinesCount() {
        val height = 5
        val width = 5
        val map = MineMap(height, width)
        map.setMines(5)
        println(map)
        assertThat(map.getMapSize()).isEqualTo(height * width)
    }

    @DisplayName(value = "전체 맵의 크기보다 많은 수의 지뢰를 심을 수 없다.")
    @Test
    fun checkSetMinesOverCount() {
        val height = 5
        val width = 5
        val map = MineMap(height, width)
        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy {
                map.setMines(30)
            }
    }
}
