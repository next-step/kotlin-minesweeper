package model.minemark

sealed class MineMark(val openStatus: OpenStatus = OpenStatus.CLOSED) {
    abstract fun next(count: Int): MineMark

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MineMark

        return openStatus == other.openStatus
    }

    override fun hashCode(): Int {
        return openStatus.hashCode()
    }
}

class Safety : MineMark() {
    override fun next(count: Int): MineMark {
        return MineCount(count)
    }
}

class Mine : MineMark() {
    override fun next(count: Int): MineMark {
        throw IllegalStateException("mine can not be next mark")
    }
}

data class MineCount(val count: Int) : MineMark() {
    init {
        require(count >= 0) { "count must be greater than or equal to 0. but provided `$count`" }
    }

    override fun next(count: Int): MineMark {
        throw IllegalStateException("mine count can not be next mark")
    }
}
