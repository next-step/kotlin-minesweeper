package minesweeper.view

fun enterCols(): Int {
    println("높이를 입력하세요.")
    return readln().toInt()
}

fun enterRows(): Int {
    println("너비를 입력하세요.")
    return readln().toInt()
}

fun enterMineCount(): Int {
    println("지뢰는 몇 개인가요?")
    return readln().toInt()
}

fun enterOpenPosition(): String {
    print("open: ")
    return readln()
}
