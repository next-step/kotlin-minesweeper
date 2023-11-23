class ConsoleUserInterface {
    fun askHeight(): Int {
        println("높이를 입력하세요.")
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException("높이는 숫자여야 합니다.")
    }

    fun askWidth(): Int {
        println("너비를 입력하세요.")
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException("너비는 숫자여야 합니다.")
    }

    fun askMineCount(): Int {
        println("지뢰의 개수를 입력하세요.")
        return readlnOrNull()?.toIntOrNull() ?: throw IllegalArgumentException("지뢰의 개수는 숫자여야 합니다.")
    }

    fun printStartAnnouncement() {
        println("지뢰찾기 게임을 시작합니다.")
    }
}
