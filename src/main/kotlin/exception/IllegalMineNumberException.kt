package exception

class IllegalMineNumberException(message: String = "지뢰 갯수는 음수가 될 수 없습니다.") : RuntimeException(message)
