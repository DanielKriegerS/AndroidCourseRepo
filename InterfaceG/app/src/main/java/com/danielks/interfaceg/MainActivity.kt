package com.danielks.interfaceg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.danielks.interfaceg.ui.theme.InterfaceGTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterfaceGTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> // esse innerPadding é como se fosse o <HTML>
                    Component11(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// AULA 16
@Composable
fun Component11 (
    modifier: Modifier
) {
    /*
        LazyRow & LazyColumn -> Vai carregando os elementos conforme precisa renderizar.
            items   -> utilizado para percorrer listas/coleções
        Row & Column         -> Carrega tudo de uma vez -performance.
     */

    // lista com 300 registros
    val list = List(300) { "Item da lista ${it+1}" }

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
    ){
        // repetição
        items(list) {
            item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .height(80.dp),
                    colors = CardDefaults.cardColors(
                        contentColor = Color.LightGray
                    )
                ){
                    Box(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(16.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(item)
                    }
                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Component10 (
    modifier: Modifier
){
    var show by remember { mutableStateOf(false) }

    Column (
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        // botão para exibir modal
        Button(
            onClick = { show = true }
        ) {
            Text("Exibir modal")
        }

        // component modal
        if (show) {
            AlertDialog(
                onDismissRequest = { show = false },
                title = { Text("Meu primeiro modal") },
                text = { Text("Hello world") },
                confirmButton = {
                    Button(
                        { show = false }
                    ) {
                        Text("Fechar")
                    }
                },
                dismissButton = {
                    Button(
                        { show = false }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Composable
fun Component9(modifier: Modifier) {
    var email by remember {mutableStateOf("")}
    var isValidEmail by remember {mutableStateOf(false)}

    if (email.length < 5) isValidEmail = true

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            textStyle = TextStyle(color = Color.Red),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Icone email"
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            // isError = true, // marca a linha com erro ou OK em torno do text
            trailingIcon = {
                if (email.isNotEmpty()){
                    if (isValidEmail) {
                       Icon(
                           imageVector = Icons.Default.Check,
                           contentDescription = "Email valido",
                           tint = Color.Green
                       )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Email invalido",
                            tint = Color.Yellow
                        )
                    }
                }
            }
        )
    }
}

// AULA 15
// EXERCICIO
@Composable
fun Exercise1(modifier: Modifier) {
    val cards = listOf(
        Pair("Perfil", Icons.Default.AccountCircle),
        Pair("Configurações", Icons.Default.Settings),
        Pair("Email", Icons.Default.Email),
        Pair("Loja", Icons.Default.ShoppingCart),
        Pair("Favoritos", Icons.Default.Favorite),
        Pair("Mapa", Icons.Default.LocationOn),
        Pair("Fotos", Icons.Default.Face),
        Pair("Agenda", Icons.Default.DateRange),
        Pair("Sair", Icons.Default.ExitToApp)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(0.dp, 30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Menu",
                color = Color.Black,
                fontSize = 50.sp,
                modifier = Modifier
                    .padding(15.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),

            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(cards) { item ->
                CardMaker(
                    cardText = item.first,
                    iconImage = item.second
                )
            }
        }
    }
}

@Composable
fun CardMaker(cardText : String, iconImage : ImageVector) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
        modifier = Modifier
            .size(100.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconMaker(iconImage)
            Text(
                text = cardText,
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun IconMaker(iconImage : ImageVector) {
        Icon(
            imageVector = iconImage,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(60.dp)
        )
}

@Composable
fun Component8(modifier: Modifier) {
    Column (modifier = modifier
        .background(Color.White)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

    // CARD CONVENCIONAL
        Card (
            colors = CardDefaults.cardColors(
                containerColor = Color.Blue
            ),
            modifier = Modifier
                .size(150.dp) // aqui define pra width e height, pode usar cada um separado
        ){  }

        Spacer(
            modifier = Modifier.height(30.dp) // adicionando espaçamento
        )

        // CARD COM BORDA
        OutlinedCard(
            border = BorderStroke(1.dp, Color.Black),
            colors = CardDefaults.cardColors(
                containerColor = Color.Gray
            ),
            modifier = Modifier
                .size(150.dp)

        ) {  }
    }
}

@Composable
fun Component7(modifier : Modifier){
    Column(modifier = modifier.fillMaxSize()) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = Color.Black, // esse não é obrigatório, apenas para colocar cor
            modifier = modifier.size(60.dp),
        )
        Spacer(
            modifier = Modifier.height(30.dp) // adicionando espaçamento
        )
    }
}

//AULA 12
//@Composable
//fun Button(
//    text : String,
//    function : () -> Unit
//)
////    type : String) // caso precisasse do IF para verificar o tipo do botão e personalizar (exemplo)
//{
//    Button(
//        onClick = function,
//        colors = ButtonDefaults
//            .buttonColors(
//                contentColor = Color.Black,
////                containerColor = if(type == "Upper") Color.White else Color.Black
//                containerColor = Color.White
//            )
//    ) {
//        Text(text)
//    }
//
//}

@OptIn(ExperimentalMaterial3Api::class) // Annotation para informar que há uso de comandos consideradores experimentais
@Composable
fun Component6(modifier: Modifier) {

    // Linguagens
    val linguagens = listOf("C#", "Java", "Kotlin", "Python")

    // Exibição do menu (dropdown)
    var menu by remember { mutableStateOf(false) }

    // Armazenar a linguagem selecionada
    var linguagemSelecionada by remember { mutableStateOf(linguagens[0]) }

    // Estrutura de coluna
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Título
        Text(text = "Escolha uma linguagem:")

        // ESSENCIAL: usar ExposedDropdownMenuBox
        ExposedDropdownMenuBox(
            expanded = menu,
            onExpandedChange = { menu = !menu }
        ) {

            // Campo (ancora do menu)
            TextField(
                value = linguagemSelecionada,
                onValueChange = {},
                readOnly = true,
                label = { Text("Linguagem") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(menu)
                },

                // MUITO IMPORTANTE: isso conecta o menu ao campo
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(
                        type = MenuAnchorType.PrimaryNotEditable,
                        enabled = true
                    )
            )

            // Menu dropdown
            ExposedDropdownMenu(
                expanded = menu,
                onDismissRequest = { menu = false }
            ) {
                linguagens.forEach { linguagem ->
                    DropdownMenuItem(
                        text = { Text(linguagem) },
                        onClick = {
                            linguagemSelecionada = linguagem
                            menu = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Component5(modifier: Modifier) {
    // Linguagens de Programação
    val languages = listOf<String>("C#", "Java", "Kotlin", "Python")

    // Receber linguagem selecionada
    val selectedLanguage by remember { mutableStateOf("") } // predefinimos como vazio, assim dá pra por algo como label

    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        // percorrer lista de linguagens
        languages.forEach {
            Row (modifier = modifier) {
                Text(it)
            }
        }
    }
}

@Composable
fun Component4(modifier: Modifier) {
    var isChecked by remember { mutableStateOf(false) }

    Column (
        modifier = modifier
            .fillMaxSize()
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                newValue -> isChecked = newValue
            }
        )
        Text(
            text = if(isChecked == true) "ativo" else "inativo",
            fontSize = 15.sp,
            color = Color.Red,
        )
    }
}

@Composable
fun Component3(
    modifier: Modifier
) {
    var number by remember { mutableStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFA1042D)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.teste),
            contentDescription = "Imagem teste"
        )
        Text(
            text = number.toString(),
            color = Color.Black,
            fontSize = 50.sp,
            modifier = Modifier
                .padding(15.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Button("Incrementar") {
                number++
            }
            Button("Decrementar") {
                number--
            }
        }
        }
    }

/* AULA 11
// COMPONENTES
*/

@Composable
fun Component2(
    modifier: Modifier
) {
    // variavel
    var number by remember { mutableStateOf(0) }

    // estrutura
    //Text(number.toString()) // para qualquer coisa diferente de String, precisa do toString()
    Column (
        modifier = modifier
            .fillMaxSize()
            // .background(Color.LightGray), // cor das "defaults"
            .background(Color(0xFFA1042D)),
                // "0x" é o alpha, "FF" é o branco
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.teste),
            contentDescription = "Imagem teste"
        )
        Text (
            text = number.toString(),
            color = Color.Black,
            fontSize = 50.sp,
            modifier = Modifier
                .padding(15.dp)
        )
        Row (
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Button(
//                modifier = modifier // usando modifier aqui, ele pega o lá da inicialização (com fundo branco)
//                    .padding(horizontal = 5.dp),
                onClick = {
                    number++
                },
                colors = ButtonDefaults
                    .buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White
                    )
            ) {
                Text("Incrementar")
            }
            Button(
//                modifier = modifier
//                    .padding(horizontal = 5.dp),
                onClick = {
                    number--
                },
                colors = ButtonDefaults
                    .buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White
                    )
            ) {
                Text("Decrementar")
            }
        }
    }
}

@Composable
fun Component1(
    modifier: Modifier
) {
    // var text : String = "Hello" // dessa forma a variável fica "estatica"
                                    //com relação a alterações em tela (render)
   // var text = remember { mutableStateOf("") } // assim precisa do .value pra acessar o valor

    var text by remember { mutableStateOf("") }
    Column (modifier = modifier
                .fillMaxSize()
        ){
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 35.sp,
            color = Color.Red,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        // Text(text) // assim dá erro, precisa extrair o valor
        //Text(text)
        TextField( // equivale a um input
            value = text,
            onValueChange = {
                text = it
            },
//            label = {
  //              Text("Digite algo...")
    //        },
            placeholder =  {
                Text("Digite algo...")
            },
            modifier = Modifier
                .fillMaxWidth() // aqui gerou um novo modifier zerado
                .padding(horizontal = 20.dp) // pode definir de diversas formas (palavras especificas) qual padding vai alterar
        )
    }
}

/* Layouts
*  Box -> É o equivalente de uma div HTML (estrutura de agrupamento)
*  Row -> Estrutura de linhas
*  Column -> Estrutura de coluna
*  Pode ter um dentro do outro, dependendo do necessário no layout do projeto
*/

// sempre precisa do modifier, pois é ele quem faz a estilização (customização) dos componentes
@Composable
fun Structure1(modifier : Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize() // respeita toda a área da tela como parte do conteúdo
        //  .fillMaxHeight() // respeita apenas altura
        //  .fillMaxWidth() // respeita apenas largura
            .background(Color.White), // essa virgula finaliza o bloco do modifier, daqui pra baixo está falando de itens da box nativos
        //contentAlignment = Alignment.CenterEnd
    ) {
        Text("Meu primeiro layout", fontSize = 30.sp) // 1sp aproximadamente 1px
    }
}

@Composable
fun Structure2(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .background(Color.Blue)
                //.size(50.dp)
                .weight(2f) // delimita o "peso" desse elemento dentro do layout
        ) {Text("1")}
        Box(
            modifier = modifier
                .background(Color.Red)
                //.size(50.dp)
                .weight(1f)
        ) {Text("2")}
        Box(
            modifier = modifier
                .background(Color.Green)
                //.size(50.dp)
                .weight(1f)
        ) {Text("3")}
        Box(
            modifier = modifier
                .background(Color.Cyan)
                //.size(50.dp)
                .weight(1f)
        ) {Text("4")}
    }
}

@Composable
fun Structure3(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .background(Color.Blue)
                //.size(50.dp)
                .weight(1f) // delimita o "peso" desse elemento dentro do layout
                .fillMaxWidth()
            ) {Text("1")}
        Box(
            modifier = modifier
                .background(Color.Red)
                //.size(50.dp)
                .weight(1f)
                .fillMaxWidth()
            ) {Text("2")}
        Box(
            modifier = modifier
                .background(Color.Green)
                //.size(50.dp)
                .weight(1f)
                .fillMaxWidth()
            ) {Text("3")}
        Box(
            modifier = modifier
                .background(Color.Cyan)
                //.size(50.dp)
                .weight(1f)
                .fillMaxWidth()
            ) {Text("4")}
    }
}