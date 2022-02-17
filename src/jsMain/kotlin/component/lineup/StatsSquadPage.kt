package component.lineup

import Player
import csstype.Color
import csstype.Display
import csstype.FlexGrow
import csstype.px
import getPlayers
import kotlinext.js.jso
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.p
import mui.material.*
import org.w3c.dom.HTMLSelectElement
import react.Props
import react.dom.html.ReactHTML
import react.fc
import react.useEffectOnce
import react.useState

var p1 : Int = 1
var p2 : Int = 2
var p3 : Int = 3
var p4 : Int = 4
var p5 : Int = 5
var p6 : Int = 6
var p7 : Int = 7
var p8 : Int = 8
var p9 : Int = 9
var p10 : Int = 10
var p11 : Int = 11

fun f433(gk: Int, d1: Int, d2: Int, d3: Int,d4: Int, cdc: Int, cc1: Int, cc2: Int, att: Int, fw1: Int, fw2: Int) : dynamic {
    return js("({ squad : { " +
            "gk : { number : gk }, " +
            "df : [ { number : d1}, { number : d2 }, { number : d3 }, { number : d4 } ]," +
            "cdm : [ { number : cdc } ]," +
            "cm : [ { number : cc1 }, { number : cc2 } ]," +
            "fw : [ { number : fw1 }, { number : fw2 }, { number : att } ]} })")
}

fun f4312(gk: Int, d1: Int, d2: Int, d3: Int,d4: Int, cc1: Int, cc2: Int, cc3: Int, cam: Int, fw1: Int, fw2: Int) : dynamic {
    return js("({ squad : { " +
            "gk : { number : gk }, " +
            "df : [ { number : d1}, { number : d2 }, { number : d3 }, { number : d4 } ]," +
            "cm : [ { number : cc1 }, { number : cc2 }, { number : cc3 } ]," +
            "cam : [ { number : cam } ]," +
            "fw : [ { number : fw1 }, { number : fw2 } ]} })")
}

fun f442(gk: Int, d1: Int, d2: Int, d3: Int,d4: Int, cc1: Int, cc2: Int, cc3: Int, cc4: Int, fw1: Int, fw2: Int) : dynamic {
    return js("({ squad : { " +
            "gk : { number : gk }, " +
            "df : [ { number : d1}, { number : d2 }, { number : d3 }, { number : d4 } ]," +
            "cm : [ { number : cc1 }, { number : cc2 }, { number : cc3 }, { number : cc4 } ]," +
            "fw : [ { number : fw1 }, { number : fw2 }]} })")
}

fun f352(gk: Int, d1: Int, d2: Int, d3: Int,cc1: Int, cc2: Int, cc3: Int, cc4: Int, cc5: Int, fw1: Int, fw2: Int) : dynamic {
    return js("({ squad : { " +
            "gk : { number : gk }, " +
            "df : [ { number : d1}, { number : d2 }, { number : d3 } ]," +
            "cm : [ { number : cc1 }, { number : cc2 }, { number : cc3 }, { number : cc4 }, { number : cc5 } ]," +
            "fw : [ { number : fw1 }, { number : fw2 }]} })")
}

fun f343(gk: Int, d1: Int, d2: Int, d3: Int,cc1: Int, cc2: Int, cc3: Int, cc4: Int, fw1: Int, fw2: Int, fw3: Int) : dynamic {
    return js("({ squad : { " +
            "gk : { number : gk }, " +
            "df : [ { number : d1}, { number : d2 }, { number : d3 } ]," +
            "cm : [ { number : cc1 }, { number : cc2 }, { number : cc3 }, { number : cc4 } ]," +
            "fw : [ { number : fw1 }, { number : fw2 }, { number : fw3 }]} })")
}

val statSquad = fc<Props> {

    var players by useState(emptyList<Player>())
    var team by useState(null)
    var selectedModule by useState("433")
    var modules: Array<String> = arrayOf("433","442","4312","352","343")

    useEffectOnce {
        MainScope().launch {
            when(selectedModule) {
                "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
            }
            players = getPlayers()
        }
    }

    Box {
        attrs {
            sx = jso {
                display= Display.flex
            }
        }
        Box {
            attrs {
                sx = jso {
                    flexGrow = FlexGrow(3.0)
                }
            }
            soccerLineup {
                attrs{
                    color="#00e600"
                    pattern="circles"
                    homeTeam = team
                }
            }
        }
        Box {
            attrs {
                sx = jso {
                    flexGrow = FlexGrow(2.0)
                    marginTop = 10.px
                    marginLeft = 5.px
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    +"Modulo  "
                }
                ReactHTML.select {
                    attrs.id = "modulo"
                    attrs.onChange = {
                        selectedModule = (document.getElementById("modulo") as HTMLSelectElement).value
                    }
                    for (m in modules) {
                        ReactHTML.option {
                            attrs.value = m
                            +" $m"
                        }
                    }
                }
                Button {
                    attrs {
                        style = jso() {
                            color = Color("#20b02c")
                            borderColor = Color("#20b02c")
                            onClick = {
                                when(selectedModule) {
                                    "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                                    "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                                    "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                                    "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                                    "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                                }
                            }
                        }
                    }

                    +"Ok"
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    +"GK  "
                }
                ReactHTML.select {
                    attrs.id = "p1"
                    attrs.onChange = {
                        p1 = (document.getElementById("p1") as HTMLSelectElement).value.toInt()
                        when(selectedModule) {
                            "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                        }
                    }
                    for (p in players) {
                        ReactHTML.option {
                                attrs.value = p.shirtNumber.toString()
                                +" ${p.surname}"
                            }
                    }
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    when(selectedModule) {
                        "442" -> +"TS "
                        "433" -> +"TS "
                        "4312" -> +"TS "
                        "352" -> +"DC "
                        "343" -> +"DC "
                    }
                }
                ReactHTML.select {
                    attrs.id = "p2"
                    attrs.onChange = {
                        p2 = (document.getElementById("p2") as HTMLSelectElement).value.toInt()
                        when(selectedModule) {
                            "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                        }
                    }
                    for (p in players) {
                        ReactHTML.option {
                            attrs.value = p.shirtNumber.toString()
                            +" ${p.surname}"
                        }
                    }
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    + "DC "
                }
                ReactHTML.select {
                    attrs.id = "p3"
                    attrs.onChange = {
                        p3 = (document.getElementById("p3") as HTMLSelectElement).value.toInt()
                        when(selectedModule) {
                            "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                        }
                    }
                    for (p in players) {
                        ReactHTML.option {
                            attrs.value = p.shirtNumber.toString()
                            +" ${p.surname}"
                        }
                    }
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    +"DC "
                }
                ReactHTML.select {
                    attrs.id = "p4"
                    attrs.onChange = {
                        p4 = (document.getElementById("p4") as HTMLSelectElement).value.toInt()
                        when(selectedModule) {
                            "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                        }
                    }
                    for (p in players) {
                        ReactHTML.option {
                            attrs.value = p.shirtNumber.toString()
                            +" ${p.surname}"
                        }
                    }
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    when(selectedModule) {
                        "442" -> +"TD "
                        "433" -> +"TD "
                        "4312" -> +"TD "
                        "352" -> +"DC "
                        "343" -> +"DC "
                    }
                }
                ReactHTML.select {
                    attrs.id = "p5"
                    attrs.onChange = {
                        p5 = (document.getElementById("p5") as HTMLSelectElement).value.toInt()
                        when(selectedModule) {
                            "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                        }
                    }
                    for (p in players) {
                        ReactHTML.option {
                            attrs.value = p.shirtNumber.toString()
                            +" ${p.surname}"
                        }
                    }
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    when(selectedModule) {
                        "442" -> +"ES "
                        "433" -> +"CDC "
                        "4312" -> +"CCS "
                        "352" -> +"CCS "
                        "343" -> +"CCS "
                    }
                }
                ReactHTML.select {
                    attrs.id = "p6"
                    attrs.onChange = {
                        p6 = (document.getElementById("p6") as HTMLSelectElement).value.toInt()
                        when(selectedModule) {
                            "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                        }
                    }
                    for (p in players) {
                        ReactHTML.option {
                            attrs.value = p.shirtNumber.toString()
                            +" ${p.surname}"
                        }
                    }
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    when(selectedModule) {
                        "442" -> +"CCS "
                        "433" -> +"CCS "
                        "4312" -> +"CC "
                        "352" -> +"CC "
                        "343" -> +"CCD "
                    }
                }
                ReactHTML.select {
                    attrs.id = "p7"
                    attrs.onChange = {
                        p7 = (document.getElementById("p7") as HTMLSelectElement).value.toInt()
                        when(selectedModule) {
                            "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                        }
                    }
                    for (p in players) {
                        ReactHTML.option {
                            attrs.value = p.shirtNumber.toString()
                            +" ${p.surname}"
                        }
                    }
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    when(selectedModule) {
                        "442" -> +"CCD "
                        "433" -> +"CCD "
                        "4312" -> +"CCD "
                        "352" -> +"CCD "
                        "343" -> +"ED "
                    }
                }
                ReactHTML.select {
                    attrs.id = "p8"
                    attrs.onChange = {
                        p8 = (document.getElementById("p8") as HTMLSelectElement).value.toInt()
                        when(selectedModule) {
                            "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                        }
                    }
                    for (p in players) {
                        ReactHTML.option {
                            attrs.value = p.shirtNumber.toString()
                            +" ${p.surname}"
                        }
                    }
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    when(selectedModule) {
                        "442" -> +"ED "
                        "433" -> +"AD "
                        "4312" -> +"CAM "
                        "352" -> +"ED "
                        "343" -> +"AS "
                    }
                }
                ReactHTML.select {
                    attrs.id = "p9"
                    attrs.onChange = {
                        p9 = (document.getElementById("p9") as HTMLSelectElement).value.toInt()
                        when(selectedModule) {
                            "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                        }
                    }
                    for (p in players) {
                        ReactHTML.option {
                            attrs.value = p.shirtNumber.toString()
                            +" ${p.surname}"
                        }
                    }
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    when(selectedModule) {
                        "442" -> +"ATT "
                        "433" -> +"AS "
                        "4312" -> +"ATT "
                        "352" -> +"ATT "
                        "343" -> +"ATT "
                    }
                }
                ReactHTML.select {
                    attrs.id = "p10"
                    attrs.onChange = {
                        p10 = (document.getElementById("p10") as HTMLSelectElement).value.toInt()
                        when(selectedModule) {
                            "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                        }
                    }
                    for (p in players) {
                        ReactHTML.option {
                            attrs.value = p.shirtNumber.toString()
                            +" ${p.surname}"
                        }
                    }
                }
            }
            Stack {
                attrs {
                    sx = jso {
                        display = Display.block
                    }
                }
                Typography {
                    attrs {
                        sx = jso {
                            variant = "body1"
                            component = ReactHTML.span
                            align = TypographyAlign.left
                        }
                    }
                    when(selectedModule) {
                        "442" -> +"ATT "
                        "433" -> +"ATT "
                        "4312" -> +"ATT "
                        "352" -> +"ATT "
                        "343" -> +"AD "
                    }
                }
                ReactHTML.select {
                    attrs.id = "p11"
                    attrs.onChange = {
                        p11 = (document.getElementById("p11") as HTMLSelectElement).value.toInt()
                        when(selectedModule) {
                            "433" -> team=f433(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "442" -> team=f442(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "4312" -> team=f4312(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "352" -> team=f352(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                            "343" -> team=f343(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11)
                        }
                    }
                    for (p in players) {
                        ReactHTML.option {
                            attrs.value = p.shirtNumber.toString()
                            +" ${p.surname}"
                        }
                    }
                }
            }
        }
    }
}






