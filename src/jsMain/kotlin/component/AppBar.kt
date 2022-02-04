package component

import kotlinext.js.jso
import csstype.Color
import csstype.TextDecoration
import mui.material.*
import mui.material.styles.Theme
import mui.material.styles.ThemeOptions
import mui.material.styles.createTheme
import react.Props
import react.dom.aria.AriaCurrent
import react.dom.aria.ariaCurrent
import react.fc
import react.router.dom.NavLink

val appBar = fc<Props> {
        AppBar {
            attrs {
                position = AppBarPosition.static
                color = AppBarColor.primary
            }
            Toolbar {
                NavLink {
                    attrs {
                        to = "/"
                        style = jso {
                            textDecoration = TextDecoration.none
                            color = Color("White")
                        }
                    }
                    ListItemButton {
                        +"Home"
                    }
                }
                NavLink {
                    attrs {
                        to = "/statsPlayers"
                        style = jso {
                            textDecoration = TextDecoration.none
                            color = Color("White")
                        }
                    }
                    ListItemButton {
                        +"Stats Players"
                    }
                }
                NavLink {
                    attrs {
                        to = "/statSquad"
                        style = jso {
                            textDecoration = TextDecoration.none
                            color = Color("White")
                        }
                    }
                    ListItemButton {
                        +"Stats Squad"
                    }
                }

            }
        }

}