package minesweeper.view

tailrec fun inputHeight(retry: Boolean = false): Int {
    if (retry) println("잘못입력했단말이야")

    println("높이를 입력하세요.")
    return readLine()?.toIntOrNull()
        ?: inputHeight(true)
}

tailrec fun inputWidth(retry: Boolean = false): Int {
    if (retry) println("잘못입력했단말이야")

    println("너비를 입력하세요.")
    return readLine()?.toIntOrNull()
        ?: inputWidth(true)
}

tailrec fun inputMineCount(retry: Boolean = false): Int {
    if (retry) println("잘못입력했단말이야")

    println("지뢰는 몇 개인가요?")
    return readLine()?.toIntOrNull()
        ?: inputMineCount(true)
}
