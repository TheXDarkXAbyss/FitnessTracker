package com.example.fitnesstracker.ui.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.FitnessTrackerScreen
import com.example.fitnesstracker.R
import com.example.fitnesstracker.ui.components.subComponents.Placement
import com.example.fitnesstracker.ui.components.subComponents.Shadow
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme

@Composable
fun NavBar(onClickBtnExercise: () -> Unit, onClickBtnHome: () -> Unit, onClickBtnAbout: () -> Unit,modifier: Modifier = Modifier, currentScreen: FitnessTrackerScreen /*navBarViewModel: NavBarViewModel = NavBarViewModel()*/) {

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
                screen = FitnessTrackerScreen.Exercises,
                onClick = onClickBtnExercise,
                icon = R.drawable.folder,
                description = R.string.navExercisesBtnDescription,
                currentScreen = currentScreen
            )

            CustomIconButton(
                screen = FitnessTrackerScreen.Home,
                onClick = onClickBtnHome,
                icon = R.drawable.home,
                description = R.string.navHomeBtnDescription,
                currentScreen = currentScreen
            )

            CustomIconButton(
                screen = FitnessTrackerScreen.About,
                onClick = onClickBtnAbout,
                icon = R.drawable.user,
                description = R.string.navAboutBtnDescription,
                currentScreen = currentScreen
            )
        }
    }

}

@Composable
fun CustomIconButton(
    screen: FitnessTrackerScreen, onClick: () -> Unit,
    @DrawableRes icon: Int,
    @StringRes description: Int,
    currentScreen: FitnessTrackerScreen) {
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
    //Log.println(Log.DEBUG,"navbar", "Current Screen: " + navbarState.currentScreen)
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