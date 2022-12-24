package minesweeper.domain

class Row(val fields: MutableList<Field>) {
    companion object {
        fun init(width: Int): Row {
            return Row(
                MutableList(width) {
                    Field()
                }
            )
        }
    }
}
