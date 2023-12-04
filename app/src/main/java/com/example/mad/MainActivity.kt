package com.example.mad

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mad.ui.theme.MADTheme
import com.example.mad.ui.theme.screens.*

import com.example.mad.viewmodel.RemindersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    RemindersNavHost(navController = navController, modifier = Modifier)
                }
            }
        }
    }
}



@Composable
private fun RemindersNavHost(
    navController: NavHostController, modifier: Modifier
) {
    val viewModel: RemindersViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = RemindersScreens.RemindersList.name,
        modifier = modifier
    )
    {
        composable(RemindersScreens.RemindersList.name) {
            RemindersListScreen(navController, viewModel)
        }
        composable(RemindersScreens.NewReminder.name) {
            AddReminderScreen(navController, viewModel)
        }
    }

     if (navController.currentDestination?.route == RemindersScreens.NewReminder.name) {
        AddReminderScreenFab(navController)
    }
}






@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MADTheme {
        val navController = rememberNavController()
        RemindersNavHost(navController = navController, modifier = Modifier)
    }
}