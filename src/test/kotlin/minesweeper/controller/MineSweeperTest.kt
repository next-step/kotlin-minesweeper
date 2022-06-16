package minesweeper.controller

import minesweeper.model.MineMapBuilder
import minesweeper.model.map.Cell
import minesweeper.model.map.MineMap
import minesweeper.model.map.coordinate.MapArea
import minesweeper.view.output.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MineSweeperTest {

    @Test
    fun `컨트롤러 Headless 테스트`() {

        // given
        val mapSize = MapArea(10, 10)
        val expectedOutput = "*".repeat(mapSize.cellCount)

        val map = MineMap.build(mapSize) { true } // 10* 10 , map filled with mines

        var actualOutput = ""
        val outputView = object : OutputView {

            override fun printMap(mineMap: MineMap) {
                val cells = mineMap.cells
                actualOutput = cells.joinToString(separator = "") { if (it is Cell.Mine) "*" else "C" }
            }
        }

        val controller = MineSweeper(
            mineMapBuilder = MineMapBuilder { map },
            outputView = outputView
        )

        // when
        controller.run()

        // Then
        assertThat(actualOutput).isEqualTo(expectedOutput)
    }
}
