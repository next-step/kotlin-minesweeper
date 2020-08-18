package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MineTest {
    private lateinit var dimension: MapDimension

    @BeforeEach
    fun setUp() {
        dimension = MapDimension(5, 5)
    }

    @Test
    fun `지뢰의 위치를 원하는 곳으로 설정할 수 있다`() {
        // when
        val mine = Mine(
            dimension,
            positionStrategy = object : PositionStrategy {
                override fun setXPosition(width: Width): Int = 3
                override fun setYPosition(height: Height): Int = 3
            }
        )

        // then
        assertThat(mine.x).isEqualTo(3)
        assertThat(mine.y).isEqualTo(3)
    }

    @Test
    fun `지뢰의 위치를 랜덤으로 할 수 있다`() {
        // when
        val mine = Mine(dimension, RandomPositionStrategy)

        // then
        println("${mine.x},${mine.y}")
    }
}
