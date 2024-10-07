package com.example.project1mobiledevices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PlayerList(navController: NavController, favorites_list: MutableList<Player>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Yellow)
            .padding(8.dp)
    ) { Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.villareal2),
            contentDescription = "Villareal",
            modifier = Modifier.width(80.dp)
                .height(70.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Villareal CF Players",
            color = Color.Blue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )


    }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(players) { player ->
                Spacer(modifier = Modifier.height(16.dp))
                PlayerRow(
                    player = player,
                    favorites_list = favorites_list,
                    onClick = { navController.navigate(Screen.PlayerDetails.createRoute(player)) }
                )
            }
        }
    }
}



@Composable
fun PlayerRow(player: Player, onClick: () -> Unit, favorites_list: MutableList<Player>) {
    val isFavourite = remember { mutableStateOf(favorites_list.contains(player)) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.Blue)
            .clickable {
                // Navegación al hacer clic, pasando datos del jugador
                onClick()
            }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = player.icon),
            contentDescription = player.name,
            modifier = Modifier
                .weight(1f)
                .height(215.dp)
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

            Text(text = "Shirt number: ${player.dorsal}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium)

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
        Icon(imageVector = if (isFavourite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Add to favourite",
            tint = if (isFavourite.value) Color.Red else Color.Black,
            modifier = Modifier.clickable {
                if (isFavourite.value) {
                    favorites_list.remove(player)
                } else {
                    favorites_list.add(player)
                }
                isFavourite.value = !isFavourite.value
            }
        )
    }
}


@Composable
fun PlayerDetails(name: String, age: String, position: String, imageResId: Int, dorsal: String, descripcion: String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Yellow)
            .padding(16.dp)
    ) {
        item {
            Text(
                text = name,
                color = Color.Blue,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(
                text = descripcion,
                color = Color.Blue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}



@Composable
fun Favourites(favorites_list: MutableList<Player>, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.Yellow).padding(16.dp)
    ) {
        if (favorites_list.isEmpty()) {
            Text(text = "No players in favourites")
        } else {
            LazyColumn() {
                items(favorites_list) { player ->
                    Spacer(modifier = Modifier.height(16.dp))
                    PlayerRow(player,
                        onClick = {navController.navigate(Screen.PlayerDetails.createRoute(player))},
                        favorites_list = favorites_list)
                }
            }
        }
    }
}

@Composable
fun Club_history(){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .background(Color.Blue).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.villareal),
                contentDescription = "Villareal",
                modifier = Modifier.height(100.dp).width(250.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))
        }
        item {
            Text(
                text = "The club was founded on 10th March 1923 by a group of fans from Vila-real\n" +
                        "\n" +
                        "\n" +
                        "Today is Villarreal CF’s 93rd birthday. In one of its sweetest moments, the Yellows are healthy, sit in fourth place in LaLiga and are immersed in the fight to win their Round of 16 UEFA Europa League tie to get through to the Quarter-finals of the competition.\n" +
                        "\n" +
                        "Founded on 10th March 1923 by a group of fans from Vila-real, one of its great promoters was José Calduch Almela. Despite not competing in official matches until the next decade, the club, at the time called Club Deportivo Villarreal, started to define the identity that we know so well today. One of its first decisions was, in fact, to purchase the surface for El Madrigal stadium, constructing what today is one of the oldest stadiums in the top flight.\n" +
                        "\n" +
                        "Since then, Villarreal CF have played a total of 16 seasons in the First Division (LaLiga), even managing a second place finish in the 2007/08 season. In Europe, the Yellows reached the UEFA Champions League Semi-finals, after their first time participating in the tournament, and were eliminated by Arsenal (2005/06 season).\n" +
                        " \n" +
                        "The player who has played the most official matches for Villarreal CF is Marcos Senna (363). The Brazilian born Spaniard wore the Yellow uniform in 292 LaLiga matches, 16 in the Copa del Rey, 13 in the Europa League, 11 in the UEFA Cup, and 4 in the Intertoto Cup. Nowadays, Senna is a member of the Club’s Institutional Relations department.\n" +
                        " \n" +
                        "Furthermore, the top goal scorer in Villarreal CF history is Giuseppe Rossi (82, 22 from the penalty spot). The Italian-American (Italian of American origin) scored, wearing the Villarreal CF uniform, 54 goals in LaLiga (13p), 7 in the Copa del Rey (1p), 5 in the Champions League (1p), and 16 goals in the Europa League (7p).",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
