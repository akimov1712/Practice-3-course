package entity

data class DoubleRange(
    val from: String?,
    val to: String?
){
    companion object{
        val EMPTY: DoubleRange get() = DoubleRange(null, null)
    }
}
