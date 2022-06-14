package domain

class BoardRow(private val boardItems: List<BoardItem>): List<BoardItem> by boardItems
