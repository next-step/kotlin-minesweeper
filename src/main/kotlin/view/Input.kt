package view

import kotlin.system.exitProcess

object Input {
    fun inputHeight(): Int {
        println("높이를 입력하세요. 1보단 커야 합니다.")
        val height = readLineToInt()
        if (0 >= height) exitProcess(-1)
        return height
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요. 1보단 커야 합니다.")
        val width = readLineToInt()
        if (0 >= width) exitProcess(-1)
        return width
    }

    fun inputMine(maxCount: Int): Int {
        println("지뢰는 몇 개인가요? ${maxCount}를 초과할 순 없습니다.")
        val mineCount = readLineToInt()
        if (0 >= mineCount || mineCount > maxCount) exitProcess(-1)
        return mineCount
    }

    private fun readLineToInt(): Int {
        return readLine()?.toIntOrNull() ?: exitProcess(-1)
    }
}
