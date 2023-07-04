package mine.sweeper.view

import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width

object InputView {
    fun getHeight(): Height {
        println("높이를 입력하세요.")
        val input = readln()
        require(input.toIntOrNull() != null) { "높이를 숫자를 입력해주세요!" }
        return Height(input.toInt())
    }

    fun getWidth(): Width {
        println("너비를 입력하세요.")
        val input = readln()
        require(input.toIntOrNull() != null) { "너비를 숫자를 입력해주세요!" }
        return Width(input.toInt())
    }

    fun getMines(): MineCount {
        println("지뢰는 몇 개인가요?")
        val input = readln()
        require(input.toIntOrNull() != null) { "지뢰의 개수를 숫자를 입력해주세요!" }
        return MineCount(input.toInt())
    }
}
