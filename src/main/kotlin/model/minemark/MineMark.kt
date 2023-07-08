package model.minemark

sealed class MineMark(openStatus: OpenStatus) {
    val isOpened: Boolean = openStatus == OpenStatus.OPENED
    abstract val isMineCount: Boolean
    abstract val isSafety: Boolean
    abstract val isMine: Boolean
    abstract val openedMark: MineMark
    abstract fun next(count: Int): MineMark

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MineMark

        return isOpened == other.isOpened
    }

    override fun hashCode(): Int {
        return isOpened.hashCode()
    }
}

class Safety(openStatus: OpenStatus = OpenStatus.CLOSED) : MineMark(openStatus) {
    override val isMineCount: Boolean = false
    override val isSafety: Boolean = true
    override val isMine: Boolean = false
    override val openedMark: MineMark by lazy { Safety(OpenStatus.OPENED) }
    override fun next(count: Int): MineMark {
        return MineCount(count)
    }
}

class Mine(openStatus: OpenStatus = OpenStatus.CLOSED) : MineMark(openStatus) {
    override val isMineCount: Boolean = false
    override val isSafety: Boolean = false
    override val isMine: Boolean = true
    override val openedMark: MineMark by lazy { Mine(OpenStatus.OPENED) }

    override fun next(count: Int): MineMark {
        throw IllegalStateException("mine can not be next mark")
    }
}

class MineCount(val count: Int, openStatus: OpenStatus = OpenStatus.CLOSED) : MineMark(openStatus) {
    override val isMineCount: Boolean = true
    override val isSafety: Boolean = false
    override val isMine: Boolean = false
    override val openedMark: MineMark by lazy { MineCount(count, OpenStatus.OPENED) }

    init {
        require(count >= 0) { "count must be greater than or equal to 0. but provided `$count`" }
    }

    override fun next(count: Int): MineMark {
        throw IllegalStateException("mine count can not be next mark")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MineCount

        return count == other.count
    }

    override fun hashCode(): Int {
        return count
    }
}
