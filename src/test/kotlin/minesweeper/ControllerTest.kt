package minesweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Block
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

class ControllerTest : StringSpec({
    "입력 받은 높이와 넓이에 맞게 블록을 그린다" {
        val height = 3
        val width = 5
        val mineCount = 3
        var blockResult: List<List<Block>>? = null

        val inputView = object : InputView {
            override fun inputHeight(): Int = height
            override fun inputWidth(): Int = width
            override fun inputLandMine(): Int = mineCount
        }

        val resultView = object : ResultView {
            override fun drawBlocks(blocks: List<List<Block>>) {
                blockResult = blocks
            }
        }

        val controller = Controller(inputView, resultView)
        controller.drawMineSweeper()

        blockResult?.size shouldBe height
        blockResult?.get(0)?.size shouldBe width
    }
})
