package com.example.fitnesstracker.ui.components

import android.os.Debug
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitnesstracker.FitnessTrackerAppScreens
import com.example.fitnesstracker.R
import com.example.fitnesstracker.ui.components.subComponents.Placement
import com.example.fitnesstracker.ui.components.subComponents.Shadow
import com.example.fitnesstracker.ui.navigation.NavigationViewModel
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme
import kotlin.math.log

@Composable
fun NavBar(
    navigationViewModel: NavigationViewModel,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val navigationState by navigationViewModel.navigationState.collectAsState()

    Log.e("nav", "current screen state on recomposition start: ${navigationState.currentScreen}")

    Column (
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Shadow(elevation = 4.dp, placement = Placement.top)
        Row (
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            CustomIconButton(
                onClick = {
                    navHostController.navigate(FitnessTrackerAppScreens.Exercises.name)
                    navigationViewModel.updateCurrentScreen(FitnessTrackerAppScreens.Exercises)
                    Log.e("nav", "current screen state: ${navigationState.currentScreen}")
                },
                icon = R.drawable.folder,
                description = R.string.navExercisesBtnDescription,
                currentScreen = navigationState.currentScreen,
                screen = FitnessTrackerAppScreens.Exercises
            )

            CustomIconButton(
                onClick = {
                    navHostController.navigate(FitnessTrackerAppScreens.Home.name)
                    navigationViewModel.updateCurrentScreen(FitnessTrackerAppScreens.Home)
                    Log.e("nav", "current screen state: ${navigationState.currentScreen}")
                },
                icon = R.drawable.home,
                description = R.string.navHomeBtnDescription,
                currentScreen = navigationState.currentScreen,
                screen = FitnessTrackerAppScreens.Home
            )

            CustomIconButton(
                onClick = {
                    navHostController.navigate(FitnessTrackerAppScreens.About.name)
                    navigationViewModel.updateCurrentScreen(FitnessTrackerAppScreens.About)
                    Log.e("nav", "current screen state: ${navigationState.currentScreen}")
                },
                icon = R.drawable.user,
                description = R.string.navAboutBtnDescription,
                currentScreen = navigationState.currentScreen,
                screen = FitnessTrackerAppScreens.About
            )
        }
    }

    Log.e("nav", "current screen state on recomposition end: ${navigationState.currentScreen}")

}

@Composable
fun CustomIconButton(
    onClick: () -> Unit,
    @DrawableRes icon: Int,
    @StringRes description: Int,
    currentScreen: FitnessTrackerAppScreens,
    screen: FitnessTrackerAppScreens
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = stringResource(description),
            tint =  if(currentScreen == screen) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier.size(32.dp)

        )
    }
}

@Preview
@Composable
fun NavBarPreview() {
    FitnessTrackerTheme {
        Surface {
            //NavBar()
        }
    }
}

//private enum class navButton()