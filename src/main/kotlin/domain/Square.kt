package domain

sealed class Square {
    object Mine : Square()
    object NonMine : Square()
}
