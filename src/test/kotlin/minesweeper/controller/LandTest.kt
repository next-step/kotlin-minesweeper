package minesweeper.controller

import io.kotest.matchers.shouldBe
import minesweeper.domain.spot.MineSpot
import minesweeper.view.ResultView
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LandTest {
    @Test
    fun `10x10 크기의 땅을 만든다`() {
        assertDoesNotThrow {
            ResultView.showLand(Land.from(10, 10, 10))
        }
    }

    @Test
    fun `음수 크기의 땅을 만들수 없다`() {
        assertThrows<IllegalStateException> {
            ResultView.showLand(Land.from(-10, 10, 10))
        }
    }

    @Test
    fun `지뢰 가 없는 땅을 만들수 없다`() {
        assertThrows<IllegalStateException> {
            ResultView.showLand(Land.from(10, 10, 0))
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [10, 20, 30])
    fun `생성된 지뢰 갯수를 확인한다`(count: Int) {
        val land = Land.from(10, 10, count)
        val actualCount: Int =
            land.spots.sumOf { lines ->
                lines.sumOf {
                    when (it) {
                        is MineSpot -> 1.toInt()
                        else -> 0.toInt()
                    }
                }
            }
        actualCount shouldBe count
    }
}
