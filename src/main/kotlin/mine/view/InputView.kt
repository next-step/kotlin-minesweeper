package mine.view

import mine.Height
import mine.Mine
import mine.Width

object InputView {
    fun inputHeight(): Height {
        println("높이를 입력하세요.")
        return Height.value(readLine()?.toIntOrNull())
    }

    fun inputWidth(): Width {
        println("너비를 입력하세요.")
        return Width.value(readLine()?.toIntOrNull())
    }

    fun inputMineCount(): Mine {
        println("지뢰는 몇 개인가요?")
        return Mine.value(readLine()?.toIntOrNull())
    }
}
