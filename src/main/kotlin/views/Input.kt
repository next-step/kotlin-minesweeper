package views

import domains.Position

object Input {
    fun getHeight(): Int {
        println("높이를 입력하세요.")
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("It is not a number")
    }

    fun getWidth(): Int {
        println("너비를 입력하세요.")
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("It is not a number")
    }

    fun getMine(): Int {
        println("지뢰는 몇 개인가요?")
        return readLine()?.toIntOrNull() ?: throw IllegalArgumentException("It is not a number")
    }

    fun openBlock(): Position {
        print("open: ")
        val input = readlnOrNull()
            ?.split(",")
            ?.map { it.toInt() } // 1부터 시작하는 사용자의 입력값과 0부터 시작하는 배열의 차이값
            ?: throw IllegalArgumentException("check 2 numbers with comma")
        if (input.size != 2) {
            throw IllegalArgumentException("It is not a position")
        }

        return Position.fromUserInput(x = input[0], y = input[1])
    }
}
