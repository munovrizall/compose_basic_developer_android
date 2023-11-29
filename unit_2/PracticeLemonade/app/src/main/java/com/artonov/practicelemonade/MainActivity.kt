package com.artonov.practicelemonade

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artonov.practicelemonade.ui.theme.PracticeLemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticeLemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    PracticeLemonadeTheme {
        LemonadeApp()
    }
}

@Composable
fun LemonadeApp() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var currentStep by remember { mutableStateOf(1) }
        var squeezeCount by remember { mutableStateOf(0)}

        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    imageResourceId = R.drawable.lemon_tree,
                    textResourceId = R.string.lemon_select,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }

            2 -> {
                LemonTextAndImage(
                    imageResourceId = R.drawable.lemon_squeeze,
                    textResourceId = R.string.lemon_squeeze,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }

            3 -> {
                LemonTextAndImage(
                    imageResourceId = R.drawable.lemon_drink,
                    textResourceId = R.string.lemon_drink,
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }

            4 -> {
                LemonTextAndImage(
                    imageResourceId = R.drawable.lemon_restart,
                    textResourceId = R.string.lemon_empty_glass,
                    onImageClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Composable
private fun LemonTextAndImage(
    imageResourceId: Int,
    textResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(onClick = onImageClick) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = null,
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(id = textResourceId),
        fontSize = 18.sp
    )
}