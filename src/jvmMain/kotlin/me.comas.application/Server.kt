import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.KMongo
import com.mongodb.ConnectionString
import me.comas.application.routes.match.matchRoutes
import me.comas.application.routes.playerRoutes
import me.comas.application.routes.trainingRoutes

var matchlist = mutableListOf(
    Match("Milan",true,2,0,true,"12 Dicembre 2021 , 20:45"),
    Match("Juve",true,2,1,false,"10 Gennaio 2022 , 20:45"),
    Match("Atalanta",false,-1,-1,false,"12 Gennaio 2022 , 20:45")
)

var traininglist = mutableListOf(
    Training("2 gennaio 2022","Potenziamento","Forza gambe e braccia"),
    Training("3 gennaio 2022","Aerobico","Abbiamo fatto tante belle cose")
)

var players = mutableListOf(
    Player(1,"Samir","Handanovic", listOf("POR"),0,13,500,20,6.5,"Slovenia","03 dicembre 1985",3000000,"Bravo nelle uscite, affidabile",2,0,false,false,1, mutableListOf()),
    Player(10,"Lionel","Messi",listOf("ATT","AD"),300,0,1000,40,8.6,"Argentina","15 giugno 1987",100000000,"Fuoriclasse",2,0,false,false,1,mutableListOf())
)

/*val client = KMongo.createClient().coroutine
val database = client.getDatabase("tournament-manager")
val matches = database.getCollection<Match>()
val players = database.getCollection<Player>()*/

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 9090
    embeddedServer(Netty, port) {
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Delete)
            anyHost()
        }
        install(Compression) {
            gzip()
        }

        matchRoutes()
        trainingRoutes()
        playerRoutes()
        routing {
            get("/") {
                call.respondText(
                    this::class.java.classLoader.getResource("index.html")!!.readText(),
                    ContentType.Text.Html
                )
            }
            static("/") {
                resources("")
            }
        }
    }.start(wait = true)
}