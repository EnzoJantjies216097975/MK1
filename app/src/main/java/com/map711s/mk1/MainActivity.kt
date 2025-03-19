package com.map711s.mk1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.map711s.mk1.Navigation.MortalKombatNavigation
import com.map711s.mk1.ui.theme.MK1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MK1Theme {
                MortalKombatNavigation()

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MK1Theme {
        MortalKombatNavigation()
    }
}