package domain

const val NON_MINE = 'C'
const val MINE = '*'

data class BoardInfo(
    val values: List<List<Cell>>
)