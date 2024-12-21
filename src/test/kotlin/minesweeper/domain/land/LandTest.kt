package minesweeper.domain.land

import io.kotest.matchers.shouldBe
import minesweeper.controller.GameApp
import minesweeper.domain.spot.MineSpot
import minesweeper.view.ResultView
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
  - [] 원하는 위치에 지뢰를 심는다
  - [] 위치 포인트를 넘는 지뢰를 심을 수 없다
 */
class LandTest {
    @ParameterizedTest
    @ValueSource(ints = [10, 20, 30])
    fun `원하는 위치에 지뢰를 심는다`(point: Int) {
        val land =
            Land.from(LandSize(10, 10), 1) { _, _ ->
                listOf(point)
            }

        (land.spots[point] is MineSpot) shouldBe true
    }

    @Test
    fun `땅의 넓이를 넘는 지뢰를 심을 수 없다`() {
        assertThrows<IllegalStateException> {
            ResultView.showLand(Land.from(LandSize(10, 10), 101, GameApp.Companion::generateMines))
        }
    }

    @Test
    fun `10x10 크기의 땅을 만든다`() {
        assertDoesNotThrow {
            ResultView.showLand(Land.from(LandSize(10, 10), 10, GameApp.Companion::generateMines))
        }
    }

    @Test
    fun `음수 크기의 땅을 만들수 없다`() {
        assertThrows<IllegalStateException> {
            ResultView.showLand(Land.from(LandSize(-10, 10), 10, GameApp.Companion::generateMines))
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [10, 20, 30])
    fun `생성된 지뢰 갯수를 확인한다`(count: Int) {
        val land = Land.from(LandSize(10, 10), count, GameApp.Companion::generateMines)
        val actualCount: Int =
            land.spots.count { spot ->
                spot is MineSpot
            }
        actualCount shouldBe count
    }
}
