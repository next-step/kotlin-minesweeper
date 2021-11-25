package exception

class IllegalPositionException(message: String = "각 좌표는 Board 의 범위 내에 있어야 합니다.") : RuntimeException(message)
