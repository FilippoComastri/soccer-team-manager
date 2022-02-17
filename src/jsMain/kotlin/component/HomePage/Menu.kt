package component.HomePage

import csstype.*
import kotlinext.js.jso
import mui.icons.material.AddRounded
import mui.material.*
import mui.system.ResponsiveStyleValue
import react.Props
import react.dom.html.ReactHTML
import react.fc

external interface MenuProps : Props {
    var openTrainingDialog: () -> Unit
    var openMatchDialog: () -> Unit
}

val menu = fc<MenuProps> { props ->
    Box {
        attrs {
            sx = jso {
                marginTop = 2.px
                flexGrow = FlexGrow(1.0)
                borderColor = Color("Red")
                alignItems = AlignItems.center
            }
            component = ReactHTML.nav

        }

        Stack {
            attrs {
                sx = jso {
                    justifyContent= JustifyContent.spaceAround
                    alignItems = AlignItems.stretch
                    spacing = ResponsiveStyleValue(0.5)
                }
                direction = ResponsiveStyleValue(StackDirection.column)
            }
            Button {
                attrs {
                    variant = ButtonVariant.outlined
                    onClick =  { props.openMatchDialog() }
                    style = jso() {
                        color = Color("#20b02c")
                        borderColor = Color("#20b02c")
                    }
                }
                +"Add Match"
                AddRounded()
            }
            Button {
                attrs {
                    variant = ButtonVariant.outlined
                    onClick =  { props.openTrainingDialog() }
                    style = jso() {
                        color = Color("#20b02c")
                        borderColor = Color("#20b02c")
                    }
                }
                +"Add Training"
                AddRounded()
            }
        }
    }
}

