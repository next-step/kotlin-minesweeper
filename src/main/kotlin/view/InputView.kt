package view

import map.Height
import map.Index
import map.Width
import map.move.Position

object InputView {
    fun inputHeight(): Int? {
        println("높이를 입력하세요.")
        return readlnOrNull()?.trim()?.toInt()
    }

    fun inputWidth(): Int? {
        println("너비를 입력하세요.")
        return readlnOrNull()?.trim()?.toInt()
    }

    fun inputMineCount(): Int? {
        println("지뢰는 몇 개인가요?")
        return readlnOrNull()?.trim()?.toInt()
    }

    fun inputSearchPosition(
        height: Height,
        width: Width,
    ): Position? {
        print("open: ")
        return readlnOrNull()
            ?.split(",")
            ?.map(String::trim)
            ?.let {
                val row = Index.create(value = it[0].toInt(), maxSize = height.size)
                val column = Index.create(value = it[1].toInt(), maxSize = width.size)
                Position(row = row, column = column)
            }
    }
}
