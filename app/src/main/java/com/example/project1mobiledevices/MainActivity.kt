package com.example.project1mobiledevices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project1mobiledevices.ui.theme.Project1MobileDevicesTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class Screen(val route: String) {
    object PlayerList : Screen("playerList")
    object Favourites : Screen("favourites")
    object ClubHistory : Screen("clubHistory")
    object PlayerDetails : Screen("playerDetails/{playerName}/{playerAge}/{playerPosition}/{playerImage}/{playerDorsal}/{playerDescription}") {
        // Esta función acepta un objeto Player y lo convierte a una ruta de navegación
        fun createRoute(player: Player): String {
            return "playerDetails/${player.name}/${player.age}/${player.position}/${player.icon}/${player.dorsal}/${player.description}"
        }
    }
}
data class Player(val name: String, val icon: Int, val age: Int, val position: String, val dorsal: Int, val description: String)

val players = listOf(
    Player("Luiz Lucio Reis Junior", R.drawable.junior, 23, "Goalkepper", 1,"Luiz Lúcio Reis Júnior (Picos, Brazil, 14th January 2001) is a young Brazilian goalkeeper who is young and has great potential. At an impressive 1.92 metres, he is quick between the posts, he is able to dominate areal duels and has excellent reflexes – all of which make him a very talented goalkeeper with a big future.\n" +
            "\n" +
            "The Rio de Janeiro-born goalkeeper arrives at Villarreal CF after a promising spell in Portugal at Futebol Clube Famalicao de Portugal, the team he joined at 18 years of age, before going on to make 140 first-team appearances.\n" +
            "\n" +
            "PREVIOUS TEAMS\n" +
            "FC Famalicao"),
    Player("Logan Evans Costa", R.drawable.logancosta, 23, "Defender", 2,"Logan Evans Costa (Saint-Denis, France, 01/04/2001) is a young, strong and tall central defender. His aggressiveness, ability to win defensive duels and aerial strength are some of his best qualities.\n" +
            "\n" +
            "Costa arrives in LALIGA EA Sports after making his name in French football, where he played for Toulouse FC and Le Mans FC. During his spell in Toulouse, he established himself as one of the best defenders in Ligue 1, making 58 appearances and scoring four goals.\n" +
            "\n" +
            "Despite his young age, the Yellows defender is a full international with Cape Verde, with 19 caps to date. As a youth player, he played for the French U16 and U17 national teams.\n" +
            "\n" +
            "PREVIOUS TEAMS\n" +
            "Stade Reims – Le Mans FC – Toulouse FC\n" +
            "ACHIEVEMENTS\n" +
            "Copa de Francia (2023"),
    Player("Raul Albiol Tortajada", R.drawable.albiol, 39, "Defender", 3,"Raúl Albiol Tortajada (Vilamarxant, 04/09/1985) is an experienced central defender with excellent positional skills. Thanks to his leadership, he has become one of Villarreal’s captains, with whom he mas made history by leading the club to their first ever UEFA Europa League trophy. The Valencian footballer has played for the Spanish national side more than 50 times, winning the World Cup (2010) and two European Championships (2008 and 2012).\n" +
            "\n" +
            "He began his football career at Ribarroja before joining the Valencia CF youth academy. He made his debut with the Los Che's first team and in the 2004/2005 season he played on loan at Getafe CF. He returned to Valencia, where he shone until he signed for Real Madrid in 2009/10. He stayed with Real Madrid for four seasons, just when he signed for Italian side SSC Napoli, for whom he played 236 times.\n" +
            "\n" +
            "PREVIOUS TEAMS:\n" +
            "Valencia CF - Real Madrid - SSC Napoli\n" +
            "ACHIEVEMENTS:\n" +
            "European Championships (2008 and 2012)\n" +
            "World Cup (2010)\n" +
            "UEFA Cup (2004)\n" +
            "LaLiga (2012)\n" +
            "Copa del Rey (2008 and 2011)\n" +
            "Spanish Super Cup (2012)\n" +
            "Coppa Italia (2014)\n" +
            "Supercoppa Italiana (2014)\n" +
            "UEFA Europa League (2021)"),
    Player("Eric Bertrand Baily", R.drawable.ericbailly, 30, "Defender", 4,"Eric Bertrand Bailly (12th April 1994, Côte d'Ivoire) is in his second spell at Villarreal CF. The defender from Bingerville, Côte d'Ivoire, played for the Yellows in the second half of the 2014/15 season and during the 2015/16 campaign.\n" +
            "\n" +
            "Bailly was also coached by Marcelino García Toral during his first period as a Yellow during which he performed at a high level that led him, among other achievements, to make his debut with the Côte d'Ivoire national team. He was also an important part of the team that reached the semi-finals of the UEFA Europa League in 2015/16 and reached the fourth position and the semi-finals of the Copa del Rey during the previous season, in 2014/15.\n" +
            "\n" +
            "His outstanding role at Villarreal led to the likes of Manchester United turning their attention to the young centre-back, who would leave for the English club in the summer of 2016 becoming one of the biggest sales in the club's history.\n" +
            "\n" +
            "After six seasons in Manchester, Bailly played on loan at Olympique Marseille (2022/23) and at Besiktas JK for the first half of the 2023/24 before signing his return to Vila-real.\n" +
            "\n" +
            "PREVIOUS TEAMS:\n" +
            "RCD Espanyol - Villarreal CF - Manchester United - Olympique de Marseille - Besiktas JK\n" +
            "ACHIEVEMENTS:\n" +
            "Africa Cup of Nations (2015)\n" +
            "Community Shield (2016)\n" +
            "League Cup (2016/17)\n" +
            "UEFA Europa League (2016/17)"),
    Player("Willy Kambawala Ndegushi",R.drawable.kambwala,20,"Defender", 5,"Willy Kambwala Ndengushi (Kinshasa, 25/08/2004) is a powerful central defender. The French-Congolese player stands out for his physical stature, his ability to win duels and his excellent aerial prowess. He came through the ranks at Manchester United, joining their academy from FC Sochaux. Kambwala played a total of ten matches with the Red Devils in the 2023/24 season, eight of which were in the Premier League. PREVIOUS TEAMS:\n" +
            "Manchester United\n" +
            "ACHIEVEMENTS:\n" +
            "FA Cup (2024)" ),
    Player("Denis Suarez Fernandez", R.drawable.denissuarez, 30, "Midfielder", 6,"Denis Suárez Fernández (Salceda de Caselas, Pontevedra, (6th January 1994) is a talented midfielder with great technical ability, vision and an accurate shot that make him a very complete footballer. His versatility is another of Denis' great virtues, as he can play on both flanks, or as a holding midfielder or a No.10.\n" +
            "\n" +
            "With his arrival at Villarreal, the Galician will experience his second spell with the club. Denis Suárez played for the Submarine in the 2015/16 season, when he played on loan from Barcelona, and left a great impression on the Yellows fans. That season Denis made a total of 48 appearances, scored five goals, provided 13 assists and was an important part of Villarreal's run to the semi-finals of the Europa League - where he was the competition's top assist provider - and fourth place in LaLiga.\n" +
            "\n" +
            "PREVIOUS TEAMS:\n" +
            "Manchester City - FC Barcelona - Sevilla FC - Arsenal FC - RC Celta - RCS Espanyol\n" +
            "ACHIEVEMENTS:\n" +
            "UEFA Europa League (2015)\n" +
            "LALIGA (2018 y 2019)\n" +
            "Copa del Rey (2017 y 2018)\n" +
            "Supercopa de España (2017 Y 2019)"),
    Player("Gerard Moreno", R.drawable.gerardmoreno, 32, "Striker", 7,"Gerard Moreno Balagueró (Santa Perpètua de Mogoda, 07/04/1992) is a high-quality, left-footed attacker with a great finishing ability. He always has an eye for goal, but is also very good at linking up with his teammates.\n" +
            "\n" +
            "The Yellows forward is the highest goal-scorer in the history of Villarreal CF, and was one of the main architects behind the Yellow’s first ever UEFA Europa league trophy. Thanks to his great performances for the club, the Catalan striker has also been called up to the Spanish National side.\n" +
            "\n" +
            "Brought up through the Villarreal academy, Gerard Moreno represented the U19s and both the Yellow’s B teams. In 2012/13, he made his debut for the first team, where he left a lasting impression during Villarreal’s promotion back up to the first division. In 2013/14, he was loaned out to Mallorca, where he scored 12 times. The season after, he returned to Villarreal, where he scored 16 goals in all competitions.\n" +
            "\n" +
            "After a spell with Espanyol, where he scored 39 times over three seasons, Gerard returned to the Submarine in the 2018/19 campaign, and, since then, he has become an important player for the side.\n" +
            "\n" +
            "PREVIOUS TEAMS:\n" +
            "CF Badalona - RCD Mallorca - RCD Espanyol\n" +
            "ACHIEVEMENTS:\n" +
            "UEFA Europa League (2021)"),
    Player("Juan Marcos Foyth", R.drawable.juanfoyth,26,"Defender", 8, "Juan Marcos Foyth (La Plata, Argetina, 12/01/1998) stands out for his ability to anticipate and recover the ball. He is also impressive with the ball at his feet, and dominates in the air. Foyth is also comfortable with the ball at his feet, and is strong in the air. Thanks to his versatility, he can play both at centre-back and at right-back. During his first season for the club, he made history by winning the first UEFA Europa League in the history of Villarreal CF.\n" +
            "\n" +
            "The Argentine originally arrived on loan from Tottenham Hotspur, but, after an excellent first campaign as a Yellow, the club made the move permanent. The defender was brought through the ranks at Estudiantes de La Plata, the club with which he made his professional debut. Foyth is also a full international with the Argentinian national side.\n" +
            "\n" +
            "PREVIOUS TEAMS:\n" +
            "Estudiantes de la Plata - Tottenham Hotspur\n" +
            "ACHIEVEMENTS:\n" +
            "UEFA Europa League (2021)"),
    Player("Daniel Parejo", R.drawable.daniparejo, 35, "Midfielder", 10,"Dani Parejo Muñoz (Coslada, 16/04/1989) is a midfielder with an excellent reading of the game, an ability to combine with his teammates and a set-piece specialist. He is one of the players who won the Europa League with Villarreal, the first trophy in the club’s history.\n" +
            "\n" +
            "The footballer from Madrid joined from Valencia CF, where he had spent nine seasons. For Los Che, he played 383 times, scoring 63 goals and providing 62 assists. Before that, the Spanish midfielder had spells at Getafe, Real Madrid and Queens Park Rangers. He has also represented the Spanish National side.\n" +
            "\n" +
            "PREVIOUS TEAMS:\n" +
            "Queens Park Rangers - Real Madrid - Getafe CF - Valencia CF\n" +
            "ACHIEVEMENTS:\n" +
            "Copa del Rey (2019)\n" +
            "UEFA Europa League (2021)"),
    Player("Illias", R.drawable.illias, 20, "Striker", 11,"Despite his young age, the Spanish-Moroccan player already stands out for his talent in the final third, which is why he has a great projection on the domestic and international football scene. Last season, Ilias played 23 games for Barça Atlètic in the Primera RFEF division, scoring one goal, and eight games for Barça's U19s (Juvenil A), scoring two goals, one in the División de Honor and the other in the UEFA Youth League. In addition, he also had his minutes with the first team in the 21/22 season with a total of three appearances; two of them in LaLiga and one in Copa del Rey. He is the captain of the Spanish U19 national team. PREVIOUS TEAMS:\n" +
            "FC Barcelona\n" +
            "ACHIEVEMENTS:\n" +
            "Bronze medal at the 2024 Paris Olympics Games"),
    Player("Juan Bernat", R.drawable.juanbernat, 31, "Defender",12,"Juan Bernat Velasco (Cullera, Valencia, 1st March 1993) is a left-footed left-back, who is quick and strong in attack.\n" +
            "\n" +
            "He is an experienced footballer who has had an impressive trajectory in top teans such as Paris Saint-Germain and FC Bayern, with whom he has played a total of 128 and 113 matches, respectively. He has also played for SL Benfica (seven matches) and Valencia CF (74 matches), with the latter being the club where he was formed as a player in their youth academy.\n" +
            "\n" +
            "In his impressive honours, he has four Bundesliga titles (2015, 2016, 2017 and 2018), two DFB-Pokals (2016 and 2019), four Ligue 1 titles (2019, 2021, 2022 and 2023) and a Coupe de France (2020), among others.\n" +
            "\n" +
            "Furthermore, Bernat is a Spain international, and has 11 caps and one international goal to his name.\n" +
            "\n" +
            "PREVIOUS TEAMS:\n" +
            "Valencia CF – FC Bayern – Paris Saint-Germain – SL Benfica\n" +
            "ACHIEVEMENTS:\n" +
            "Bundesliga (2015, 2016, 2017, 2018)\n" +
            "Copa de Alemania (2016)\n" +
            "Supercopa de Alemania (2016, 2017 y 2018)\n" +
            "Ligue 1 (2019, 2020, 2022 y 2023)\n" +
            "Copa de Francia (2020 y 2021)\n" +
            "Copa de la Liga (2020)\n" +
            "Supercopa de Francia (2019, 2020 y 2022)")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1MobileDevicesTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val favourites_list = remember { mutableStateListOf<Player>() }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            // No mostrar la barra superior si estamos en la pantalla principal (PlayerList)
            if (currentScreen != Screen.PlayerList.route) {
                val screenTitle = when {
                    currentScreen?.startsWith(Screen.PlayerDetails.route.substringBefore("/{")) == true -> "Player Details"
                    currentScreen == Screen.Favourites.route -> "Favourites"
                    currentScreen == Screen.ClubHistory.route -> "Club History"
                    else -> " "
                }

                TopNavigationBar(
                    title = screenTitle,
                    onBackClick = {
                        if (navController.previousBackStackEntry != null) {
                            navController.popBackStack()
                        }
                    }
                )
            }
        },
        bottomBar = { BottomNavigationBar(navController) }

    ) { innerPadding ->
        // Contenido principal con el NavHost
        NavHost(
            navController = navController,
            startDestination = Screen.PlayerList.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.PlayerList.route) {
                PlayerList(navController, favourites_list)
            }
            composable(Screen.PlayerDetails.route) { backStackEntry ->
                val playerName = backStackEntry.arguments?.getString("playerName")
                val playerAge = backStackEntry.arguments?.getString("playerAge")
                val playerPosition = backStackEntry.arguments?.getString("playerPosition")
                val playerImage = backStackEntry.arguments?.getString("playerImage")?.toIntOrNull()
                val playerDorsal = backStackEntry.arguments?.getString("playerDorsal")
                val playerDescription = backStackEntry.arguments?.getString("playerDescription")

                PlayerDetails(
                    name = playerName.orEmpty(),
                    age = playerAge.orEmpty(),
                    position = playerPosition.orEmpty(),
                    imageResId = playerImage ?: R.drawable.ic_launcher_background,
                    dorsal = playerDorsal.orEmpty(),
                    descripcion = playerDescription.orEmpty()
                )
            }
            composable(Screen.Favourites.route){
                Favourites(favourites_list, navController)
            }
            composable(Screen.ClubHistory.route){
                Club_history()
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

