package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MineMapTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `지뢰 개수를 검증한다`() {
        // when
        val mineMap = MineMap(Pair(5, 5), mineCount = 10)

        // then
        assertThat(mineMap.mines.size()).isEqualTo(10)
    }
}
