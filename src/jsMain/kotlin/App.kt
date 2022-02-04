import component.HomePage.home
import component.Lineup.statSquad
import component.PlayerStatsPage.statPlayer
import component.appBar
import kotlinx.coroutines.MainScope
import react.*
import react.router.Routes
import react.router.Route
import react.router.dom.BrowserRouter

private val scope = MainScope()

val app=fc<Props>{
    BrowserRouter {
        child(appBar)
        Routes {
            Route {
                attrs{
                    path="/"
                    element = createElement(home)
                }
            }
            Route {
                attrs{
                    path="statsPlayers"
                    element = createElement(statPlayer)
                }
            }
            Route {
                attrs{
                    path="statSquad"
                    element = createElement(statSquad)
                }
            }
        }
    }
}