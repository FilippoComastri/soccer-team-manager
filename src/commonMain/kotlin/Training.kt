import kotlinx.serialization.Serializable

@Serializable
data class Training(
    val dayHour: String,
    val focus: String,
    val descrizione: String
)
{
    val id: Int = dayHour.hashCode()
    companion object {
        const val path = "/trainingList"
    }
}