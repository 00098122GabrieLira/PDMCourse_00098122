package com.gala00098122.peliculas.screens.searchScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun CustomizableSearchBar(
  query: String,
  onQueryChange: (String) -> Unit,
  onSearch: (String) -> Unit,
  searchResults: List<String>,
  onResultClick: (String) -> Unit,
  modifier: Modifier = Modifier,
  // Customization options
  placeholder: @Composable () -> Unit = { Text(text = "Buscar", color = Color.Gray) },
  leadingIcon: @Composable (() -> Unit)? = {
    Icon(
      Icons.Default.Search,
      contentDescription = "Search"
    )
  },
  trailingIcon: @Composable (() -> Unit)? = {
    if (query.isNotEmpty()) {
      IconButton(onClick = { onQueryChange("") }) {
        Icon(
          Icons.Default.Close,
          contentDescription = "Limpiar",
          tint = Color.Gray
        )
      }
    }
  },
  supportingContent: (@Composable (String) -> Unit)? = null,
  leadingContent: (@Composable () -> Unit)? = null,
) {
  // Track expanded state of search bar
  var expanded by rememberSaveable { mutableStateOf(false) }
  
  Column(modifier = modifier) {
    SearchBar(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .semantics { traversalIndex = 0f },
      inputField = {
        // Customizable input field implementation
        SearchBarDefaults.InputField(
          query = query,
          onQueryChange = onQueryChange,
          onSearch = {
            onSearch(query)
            expanded = false
          },
          expanded = false,
          onExpandedChange = {},
          placeholder = placeholder,
          leadingIcon = leadingIcon,
          trailingIcon = trailingIcon,
          
        )
      },
      expanded = false,
      onExpandedChange = {},
      colors = SearchBarDefaults.colors(
        containerColor = Color.White,
        dividerColor = Color(0xFF338046)
      )
    ) {
      // Show search results in a lazy column for better performance
      LazyColumn {
        items(count = searchResults.size) { index ->
          val resultText = searchResults[index]
          ListItem(
            headlineContent = { Text(text = resultText, color = Color.Black) },
            supportingContent = supportingContent?.let { { it(resultText) } },
            leadingContent = leadingContent,
            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
            modifier = Modifier
              .clickable {
                onResultClick(resultText)
                expanded = false
              }
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 4.dp)
          )
        }
      }
    }
  }
}