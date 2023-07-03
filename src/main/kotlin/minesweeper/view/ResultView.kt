package minesweeper.view

fun printStart() {
    println("지뢰찾기 게임 시작")
}

fun printResult(minesweeperArray: Array<IntArray>) {
    minesweeperArray.forEach {
        println(
            it.contentToString()
                .replace("-1", "*")
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
        )
    }
}
