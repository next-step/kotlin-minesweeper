package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MineTest {
    private lateinit var mapArea: Pair<Int, Int>

    @BeforeEach
    fun setUp() {
        mapArea = Pair(5, 5)
    }

    @Test
    fun `지뢰의 위치를 원하는 곳으로 설정할 수 있다`() {
        // when
        val mine = Mine.createMine(
            mapArea,
            positionStrategy = object : PositionStrategy {
                override fun setXPosition(width: Width): Int = 3
                override fun setYPosition(height: Height): Int = 3
            }
        )

        // then
        assertThat(mine).isEqualTo(Mine(Pair(3, 3)))
    }

    @Test
    fun `지뢰의 위치를 랜덤으로 할 수 있다`() {
        // when
        val mine = Mine.createMine(mapArea, RandomPositionStrategy)

        // then
        println("${mine.getX()},${mine.getY()}")
    }
}
