package com.example.mad.ui.theme.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mad.R
import com.example.mad.viewmodel.RemindersViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddReminderScreen(navController: NavHostController, viewModel: RemindersViewModel) {
    val context = LocalContext.current
    var newReminder by remember { mutableStateOf(String()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        content = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 16.dp, end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = newReminder,
                    // below line is used to add placeholder ("hint") for our text field.
                    placeholder = { Text(text = stringResource(id = R.string.new_reminder)) },
                    onValueChange = {
                        newReminder = it
                    },
                    label = { Text(stringResource(R.string.enter_new_reminder)) }
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    onClick = {
                        val message = if (newReminder.isNotBlank()) {
                            viewModel.addReminder(newReminder = newReminder)
                            newReminder = String() // Reset reminder text
                            navController.popBackStack()//navigate(pop//"${RemindersScreens.NewReminder.name}")
                            R.string.reminder_added
                        } else {
                            R.string.no_empty_reminder
                        }
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }) {
                    Text(text = stringResource(R.string.add_reminder))
                }
            }
        },
    )
}

@Composable
fun AddReminderScreenFab(navController: NavHostController) {
    FloatingActionButton(onClick = {
        navController.popBackStack()//navigate(pop//"${RemindersScreens.NewReminder.name}")
    }) {
        Text(text = "ADD")
    }
}
