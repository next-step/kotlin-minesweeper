package mine.view

import mine.Height
import mine.Mine
import mine.Width
import mine.cell.Position
import mine.cell.Position.Companion.ofPosition

object InputView {
    fun inputHeight(): Height {
        println("높이를 입력하세요.")
        return Height.value(readLine()?.toIntOrNull())
    }

    fun inputWidth(): Width {
        println("\n너비를 입력하세요.")
        return Width.value(readLine()?.toIntOrNull())
    }

    fun inputMineCount(): Mine {
        println("\n지뢰는 몇 개인가요?")
        return Mine.valueOf(readLine()?.toIntOrNull())
    }

    fun inputPosition(): Position {
        println("open: ")
        return readLine().ofPosition()
    }
}
