package com.divinixx.vocateai.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Blue-based color scheme
private val lightBlueColorScheme = lightColorScheme(
    primary = Blue80,
    onPrimary = Blue20,
    primaryContainer = Blue90,
    onPrimaryContainer = Blue10,
    secondary = LightBlue80,
    onSecondary = LightBlue20,
    secondaryContainer = LightBlue90,
    onSecondaryContainer = LightBlue10,
    tertiary = Cyan80,
    onTertiary = Cyan20,
    tertiaryContainer = Cyan90,
    onTertiaryContainer = Cyan10,
    error = Red80,
    onError = Red20,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = BlueGrey99,
    onBackground = BlueGrey10,
    surface = BlueGrey99,
    onSurface = BlueGrey10,
    surfaceVariant = BlueGrey90,
    onSurfaceVariant = BlueGrey30,
    outline = BlueGrey50
)

private val darkBlueColorScheme = darkColorScheme(
    primary = Blue40,
    onPrimary = Blue100,
    primaryContainer = Blue30,
    onPrimaryContainer = Blue90,
    secondary = LightBlue40,
    onSecondary = LightBlue100,
    secondaryContainer = LightBlue30,
    onSecondaryContainer = LightBlue90,
    tertiary = Cyan40,
    onTertiary = Cyan100,
    tertiaryContainer = Cyan30,
    onTertiaryContainer = Cyan90,
    error = Red40,
    onError = Red100,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = BlueGrey10,
    onBackground = BlueGrey90,
    surface = BlueGrey10,
    onSurface = BlueGrey90,
    surfaceVariant = BlueGrey30,
    onSurfaceVariant = BlueGrey80,
    outline = BlueGrey60
)

@Composable
fun VocateAITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkBlueColorScheme
        else -> lightBlueColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}