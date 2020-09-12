package view

import model.Position
import kotlin.system.exitProcess

object Input {
    fun height(): Int {
        println("높이를 입력하세요. 1보단 커야 합니다.")
        val height = readLineToInt()
        if (0 >= height) exitProcess(-1)
        return height
    }

    fun width(): Int {
        println("너비를 입력하세요. 1보단 커야 합니다.")
        val width = readLineToInt()
        if (0 >= width) exitProcess(-1)
        return width
    }

    fun mineCount(maxCount: Int): Int {
        println("지뢰는 몇 개인가요? ${maxCount}를 초과할 순 없습니다.")
        val mineCount = readLineToInt()
        if (0 >= mineCount || mineCount > maxCount) exitProcess(-1)
        return mineCount
    }

    fun position(width: Int, height: Int): Position {
        println("좌료를 입력하세요 ex)2,1")
        val values = readLine()?.split(",") ?: exitProcess(-1)
        val value1 = values[0].toInt()
        val value2 = values[1].toInt()
        if (value1 !in 1..width || value2 !in 1..height) exitProcess(-1)
        return Position(value1 - 1, value2 - 1)
    }

    private fun readLineToInt(): Int {
        return readLine()?.toIntOrNull() ?: exitProcess(-1)
    }
}
