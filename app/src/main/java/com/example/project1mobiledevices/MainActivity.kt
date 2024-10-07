package com.example.project1mobiledevices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project1mobiledevices.ui.theme.Project1MobileDevicesTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController


sealed class Screen(val route: String){
    object PlayerList : Screen("players_list")
    object PlayerDetails : Screen("player_details/{playerName}/{playerAge}/{playerPosition}/{playerImage}") {
        fun createRoute(player: Player) = "player_details/${player.name}/${player.age}/${player.position}/${player.icon}"
    }
    object Club_history : Screen("club_history")
    object Favourites : Screen("favourites")

}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.PlayerList.route) {
        composable(Screen.PlayerList.route) {
            PlayerList(navController)
        }
        composable(Screen.PlayerDetails.route) { backStackEntry ->
            val playerName = backStackEntry.arguments?.getString("playerName")
            val playerAge = backStackEntry.arguments?.getString("playerAge")
            val playerPosition = backStackEntry.arguments?.getString("playerPosition")
            val playerImage = backStackEntry.arguments?.getString("playerImage")?.toIntOrNull()

            PlayerDetails(
                name = playerName.orEmpty(),
                age = playerAge.orEmpty(),
                position = playerPosition.orEmpty(),
                imageResId = playerImage ?: R.drawable.ic_launcher_background // Placeholder por si falta imagen
            )
        }
    }
}

data class Player(val name: String, val icon: Int, val age: Int, val position: String)

val players = listOf(
    Player("Luiz Lucio Reis Junior", R.drawable.junior, 23, "Goalkepper"),
    Player("Logan Evans Costa", R.drawable.logancosta, 23, "Defender"),
    Player("Raul Albiol Tortajada", R.drawable.albiol, 39, "Defender"),
    Player("Eric Bertrand Baily", R.drawable.ericbailly, 30, "Defender"),

    Player("Gerard Moreno", R.drawable.gerardmoreno, 32, "DC"),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1MobileDevicesTheme {
                val navController = rememberNavController()
                    PlayerList(navController = navController)
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



@Composable
fun PlayerList(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Yellow)
            .padding(16.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(players) { player ->
                Spacer(modifier = Modifier.height(16.dp))
                PlayerRow(
                    player = player,
                    onClick = { navController.navigate(Screen.PlayerDetails.createRoute(player)) }
                )
            }
        }
    }
}

@Composable
fun PlayerRow(player: Player, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.Blue)
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = player.icon),
            contentDescription = player.name,
            modifier = Modifier
                .weight(1f)
                .height(225.dp)
                .width(160.dp)
        )
        Column(
            modifier = Modifier
                .weight(2f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = player.name,
                color = Color.Yellow,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Age: ${player.age}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Position: ${player.position}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun PlayerDetails(name: String, age: String, position: String, imageResId: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Yellow)
            .padding(16.dp)
    ) {
        Text(
            text = name,
            color = Color.Blue,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = name,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Age: $age",
            color = Color.Blue,
            fontSize = 22.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Position: $position",
            color = Color.Blue,
            fontSize = 22.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}