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

// Home Screen showing characters and kameos
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    var searchQuery by remember { mutableStateOf("") }
    val characters = remember { MortalKombatRepository.getAllCharacters() }
    val kameos = remember { MortalKombatRepository.getAllKameoCharacters() }

    val filteredCharacters = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            characters
        } else {
            characters.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    val filteredKameos = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            kameos
        } else {
            kameos.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Mortal Kombat 1 Guide",
                        color = MKGold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MKDarkGray
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MKBlack)
        ) {
            // Character Info Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Character Avatar
                Image(
                    painter = rememberAsyncImagePainter(model = character.imageUrl),
                    contentDescription = character.name,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 16.dp),
                    contentScale = ContentScale.Crop
                )

                Column {
                    Text(
                        text = character.name,
                        color = MKGold,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    DifficultyIndicator(character.difficultyLevel)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = character.bio,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }

            Divider(color = MKRed, thickness = 2.dp)

            // Tab Row for different move types
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MKDarkGray,
                contentColor = MKGold
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Text(
                                text = title,
                                color = if (selectedTab == index) MKGold else Color.White
                            )
                        }
                    )
                }
            }

            // Content based on selected tab
            when (selectedTab) {
                0 -> {
                    // Special Moves
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        if (character.specialMoves.isEmpty()) {
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
                            items(character.specialMoves) { move ->
                                MoveListItem(move = move)
                            }
                        }
                    }
                }
                1 -> {
                    // Combos
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        if (character.combos.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No combos available",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        } else {
                            items(character.combos) { combo ->
                                ComboListItem(combo = combo)
                            }
                        }
                    }
                }
                2 -> {
                    // Fatalities
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        if (character.fatalities.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No fatalities available",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        } else {
                            items(character.fatalities) { fatality ->
                                FatalityListItem(fatality = fatality)
                            }
                        }
                    }
                }
            }
        }
    }
}
