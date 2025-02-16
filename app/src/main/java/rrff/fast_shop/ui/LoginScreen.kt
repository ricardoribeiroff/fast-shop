package rrff.fast_shop.ui

import android.util.Log
import android.widget.Toast
import androidx.benchmark.perfetto.Row
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.math.roundToInt
import rrff.fast_shop.ui.theme.fastShopTheme
import rrff.fast_shop.ui.theme.WorkSansFontFamily
import rrff.fast_shop.ui.theme.white
import rrff.fast_shop.R
import rrff.fast_shop.service.AuthService
import rrff.fast_shop.ui.components.ErrorText


/**
 * LoginScreen exibe a tela de login com um overlay que mostra uma imagem PNG.
 * O overlay cobre toda a tela e pode ser removido com um gesto de swipe up, onde
 * a imagem se move gradualmente para cima acompanhando o gesto do usuário.
 */
@Composable
fun LoginScreen(navController: NavController) {
    // Estados para os campos de email e senha.
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    val authService = remember {AuthService()}

    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background // Ou uma cor personalizada
        ){
            // Conteúdo da tela de login (camada inferior).
            fastShopTheme {
                Image(
                    painter = painterResource(id = R.drawable.login_background),
                    contentDescription = "background",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.08f)
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.retail),
                        contentDescription = "Logo")
                    Text(
                        text = "FAST SHOP",
                        fontFamily = WorkSansFontFamily,
                        fontWeight = FontWeight(700),
                        fontSize = 48.sp
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = white,
                            unfocusedContainerColor = white,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Senha") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = white,
                            unfocusedContainerColor = white,
                        ),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = if (passwordVisible) Icons.Filled.Lock else Icons.Filled.Lock,
                                    contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha"
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp), // Ajuste o padding conforme necessário
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { /* Navegar para tela de cadastro */ }
                        ) {
                            Text("Cadastro",
                                fontSize = 18.sp)
                        }
                        Button(
                            onClick = {
                                if (email.isNotEmpty() && password.isNotEmpty()) {
                                    authService.login(
                                        email = email,
                                        password = password,
                                        onSuccess = {navController.navigate("HomeScreen")},
                                        onFailure = { showError = true }
                                    )
                                } else {
                                    showError = true
                                }
                            }
                        ) {
                            Text("Entrar", fontSize = 18.sp)
                        }
                    }
                    if (showError) {
                        ErrorText(R.string.error_login_failed)
                    }
                }

                // Overlay com o drawable PNG que cobre toda a tela e responde ao gesto de swipe up.
            SwipeToRevealLogin(drawableRes = R.drawable.login_splash)
            }
        }

    }
}

@Composable
fun SwipeToRevealLogin(drawableRes: Int) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val scope = rememberCoroutineScope()
        val screenHeight = maxHeight
        val screenHeightPx = with(LocalDensity.current) { screenHeight.toPx() }

        // Controla o offset da imagem
        val offsetY = remember { Animatable(0f) }

        // Imagem que pode ser arrastada
        Image(
            painter = painterResource(id = drawableRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(0, offsetY.value.roundToInt()) }
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragEnd = {
                            // Decide se deve manter aberto ou fechado baseado na posição
                            val shouldOpen = offsetY.value < -screenHeightPx * 0.4f
                            scope.launch {
                                if (shouldOpen) {
                                    offsetY.animateTo(-screenHeightPx)
                                } else {
                                    offsetY.animateTo(0f)
                                }
                            }
                        },
                        onVerticalDrag = { change, dragAmount ->
                            scope.launch {
                                // Atualiza o offset limitando entre -alturaTotal e 0
                                val newOffset = offsetY.value + dragAmount
                                offsetY.snapTo(newOffset.coerceIn(-screenHeightPx, 0f))
                            }
                        }
                    )
                }
        )
    }
}
