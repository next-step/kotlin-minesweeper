package minesweeper.domain

class Row(val fields: MutableList<Field>) {
    operator fun set(x: Int, dot: Field) {
        require(x in 0 until fields.size)

        this.fields[x] = dot
    }

    operator fun get(x: Int): Field {
        require(x in 0 until fields.size)

        return fields[x]
    }

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
