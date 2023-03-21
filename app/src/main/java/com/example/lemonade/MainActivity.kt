package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MakeLemonade()
                }
            }
        }
    }
}


@Composable
fun MakeLemonade() {
    var result by remember { mutableStateOf(1) }
    var squeezesRequired = (2..4).random()
    val inputPrompt = when (result) {
        1 -> stringResource(R.string.selectLemon)
        2 -> stringResource(R.string.squeezeLemon)
        3 -> stringResource(R.string.drinkLemonade)
        else -> stringResource(R.string.startAgain)
    }
    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val imageDescription = when (result) {
        1 -> stringResource(R.string.lemon_tree_content_description)
        2 -> stringResource(R.string.lemon_content_description)
        3 -> stringResource(R.string.lemonade_content_description)
        else -> stringResource(R.string.empty_glass_content_description)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = inputPrompt,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            onClick = {
            if(result == 1) {  //Start
                result++
            }
            else if(result == 2){
                if (squeezesRequired in 2..4) //Calculates how often to squeeze the lemon
                    squeezesRequired--
                else if(squeezesRequired in 0..1)
                    result++
            }
            else if(result == 3) {
                result++ //Drinking lemonade
            }
            else if(result == 4) {
                result = 1 //Back to start
                squeezesRequired = (2..4).random()
            }
        }
        )
        {

            Image(
                painter = painterResource(id = imageResource),
                contentDescription = imageDescription
            )
        }
    }
}









@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        MakeLemonade()
    }
}