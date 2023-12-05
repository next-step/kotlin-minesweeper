package view

import controller.BoardSetting
import domain.setting.Height
import domain.setting.MineCount
import domain.setting.Width

object InputView {
    fun getBoardSetting(): BoardSetting {
        val height = getHeight()
        val width = getWidth()
        val mineCount = getMineCount()
        return BoardSetting(height, width, mineCount)
    }

    private fun getHeight(): Height {
        println("높이를 입력하세요.")
        return readln().toInt().let(::Height)
    }

    private fun getWidth(): Width {
        println("너비를 입력하세요.")
        return readln().toInt().let(::Width)
    }

    private fun getMineCount(): MineCount {
        println("지뢰는 몇 개인가요?")
        return readln().toInt().let(::MineCount)
    }
}
