package me.comas.application.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import Match
import MatchListDb
import org.litote.kmongo.eq

fun Application.matchRoutes() {
    routing {
        createMatchRoute()
        getMatchListRoute()
        deleteMatchRoute()
        updateMatchRoute()
    }
}

fun Route.createMatchRoute() {
    post (Match.path) {
        MatchListDb.insertOne(call.receive<Match>())
        call.respond(HttpStatusCode.OK)
    }
}
fun Route.getMatchListRoute() {
    get (Match.path){
        call.respond(MatchListDb.find().toList())
    }
}
fun Route.deleteMatchRoute() {
    delete("${Match.path}/{id}") {
        val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
        MatchListDb.deleteOne(Match::id eq id)
        call.respond(HttpStatusCode.OK)
    }
}
fun Route.updateMatchRoute() {
    post("${Match.path}/{id}"){
        val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
        val match = call.receive<Match>()
        MatchListDb.updateOne(Match::id eq id,match)
        call.respond(HttpStatusCode.OK)
    }
}

