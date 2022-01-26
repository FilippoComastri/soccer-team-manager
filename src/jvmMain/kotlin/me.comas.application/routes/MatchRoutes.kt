package me.comas.application.routes.match

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import matchlist
import Match
import MatchPlayerStatistics
import org.litote.kmongo.MongoOperator

fun Application.matchRoutes() {
    routing {
        createMatchRoute()
        getMatchListRoute()
        deleteMatchRoute()
        updateMatchRoute()
    }
}

fun Route.createMatchRoute() {
    post (Match.path){
        //matches.insertOne(call.receive<Match>())
        matchlist += call.receive<Match>()
        call.respond(HttpStatusCode.OK)
    }
}
fun Route.getMatchListRoute() {
    get (Match.path){
        //call.respond(matches.find().toList())
        call.respond(matchlist)
    }
}
fun Route.deleteMatchRoute() {
    delete("${Match.path}/{id}") {
        val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
        //matches.deleteOne(Match::id eq id)
        //matchlist.removeIf()
        call.respond(HttpStatusCode.OK)
    }
}
fun Route.updateMatchRoute() {
    post("${Match.path}/{id}"){
        val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
        val match = call.receive<Match>()
        matchlist.first { it.id==id }.madeGoals= match.madeGoals
        matchlist.first { it.id==id }.concededGoals= match.concededGoals
        matchlist.first { it.id==id }.played = true
        call.respond(HttpStatusCode.OK)
    }
}

