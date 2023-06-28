package minesweeper.ui

object MineSweeperGameConfigurer {
    fun height(): Int {
        println("높이를 입력하세요.")
        return readln().toInt()
    }
    fun width(): Int {
        println("너비를 입력하세요.")
        return readln().toInt()
    }
    fun mine(): Int {
        println("지뢰는 몇 개인가요?")
        return readln().toInt()
    }
}
