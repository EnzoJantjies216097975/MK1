package com.map711s.mk1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.map711s.mk1.data.MortalKombatRepository
import com.map711s.mk1.ui.theme.MKBlack
import com.map711s.mk1.ui.theme.MKDarkGray
import com.map711s.mk1.ui.theme.MKGold
import com.map711s.mk1.ui.theme.MKRed
import java.security.AccessController

// Kameo Character Detail Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KameoDetailScreen(

    navController: NavController,
    kameoId: String
) {
    val kameo = remember { MortalKombatRepository.getKameoCharacterById(kameoId) }

    if (kameo == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MKBlack),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Kameo character not found",
                color = Color.White,
                fontSize = 18.sp
            )
        }
        return
    }

    // Get combos that require this kameo
    val kameoSpecificCombos = remember { MortalKombatRepository.getCombosForKameo(kameoId) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "${kameo.name} (Kameo)",
                        color = MKGold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MKGold
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MKDarkGray
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MKBlack)
        ) {
            // Kameo Info Section
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Kameo Avatar
                    Image(
                        painter = rememberAsyncImagePainter(model = kameo.imageUrl),
                        contentDescription = kameo.name,
                        modifier = Modifier
                            .size(120.dp)
                            .padding(bottom = 16.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = kameo.name,
                        color = MKGold,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = kameo.bio,
                        color = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Kameo Special Moves
            item {
                Text(
                    text = "Special Moves",
                    color = MKGold,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (kameo.specialMoves.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No special moves available",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            } else {
                items(kameo.specialMoves) { move ->
                    MoveListItem(move = move)
                }
            }

            // Character-specific combos that require this kameo
            item {
                Text(
                    text = "Character Combos with ${kameo.name}",
                    color = MKGold,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (kameoSpecificCombos.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No character-specific combos available",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            } else {
                kameoSpecificCombos.forEach { (character, combo) ->
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MKDarkGray
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = character.name,
                                    color = MKGold,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                ComboListItem(combo = combo)
                            }
                        }
                    }
                }
            }
        }
    }
}
)
}
) { paddingValues ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MKBlack)
    ) {
        // Search Bar
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MKDarkGray,
                textColor = Color.White,
                cursorColor = MKGold,
                focusedIndicatorColor = MKRed,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = { Text("Search characters or kameos...", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = MKGold) },
            singleLine = true
        )

        // Main Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            // Kameo Characters section
            item {
                Text(
                    text = "Kameo Fighters",
                    color = MKGold,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
            }

            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    items(filteredKameos) { kameo ->
                        KameoCard(
                            kameo = kameo,
                            onClick = {
                                navController.navigate("kameo/${kameo.id}")
                            }
                        )
                    }
                }
            }

            // Main Characters section
            item {
                Text(
                    text = "Main Fighters",
                    color = MKGold,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
            }

            items(filteredCharacters) { character ->
                CharacterCard(
                    character = character,
                    onClick = {
                        navController.navigate("character/${character.id}")
                    }
                )
            }
        }
    }
}
}