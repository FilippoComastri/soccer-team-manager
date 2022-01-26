import kotlinx.serialization.Serializable

@Serializable
data class Match(
    val opponent: String,
    var played: Boolean,
    var madeGoals: Int,
    var concededGoals: Int,
    val home: Boolean,
    val dayHour: String
)
{
    val id: Int = dayHour.hashCode()
    companion object {
        const val path = "/matchList"
    }
}