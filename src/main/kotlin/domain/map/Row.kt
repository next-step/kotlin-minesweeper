package domain.map

open class Row<T>(private val row: List<T>) {

    operator fun get(index: Int): T {
        require(index in row.indices)
        return row[index]
    }

    fun indices(): IntRange = row.indices

    fun size(): Int = row.size

    fun first(): T = row.first()

    fun getOrNull(index: Int): T? = row.getOrNull(index)
}
