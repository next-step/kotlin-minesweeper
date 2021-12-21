package mine_tdd.view

import mine_tdd.Height
import mine_tdd.Mine
import mine_tdd.Width
import mine_tdd.cell.Position
import mine_tdd.cell.Position.Companion.ofPosition

object InputView {
    fun inputHeight(): Height {
        println("높이를 입력하세요.")
        return Height.value(readLine()?.toIntOrNull())
    }

    fun inputWidth(): Width {
        println("\n너비를 입력하세요.")
        return Width.value(readLine()?.toIntOrNull())
    }

    fun inputMine(): Mine {
        println("\n지뢰는 몇 개인가요?")
        return Mine.value(readLine()?.toIntOrNull())
    }

    fun openPosition(): Position {
        println("open: ")
        return readLine().ofPosition()
    }
}
