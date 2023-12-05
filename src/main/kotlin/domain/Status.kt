package domain

interface Status {
    fun next(): Status
    fun isFinished(): Boolean
}
