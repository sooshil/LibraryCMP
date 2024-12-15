package com.sukajee.library.book.presentation.book_list.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.sukajee.library.book.domain.Book
import com.sukajee.library.core.presentation.PulseAnimation
import com.sukajee.library.core.presentation.SandYellow
import library.composeapp.generated.resources.Res
import library.composeapp.generated.resources.book
import library.composeapp.generated.resources.book_image_content_description
import library.composeapp.generated.resources.open_book_detail_arrow_content_description
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.round

@Composable
fun BookListItem(
    book: Book,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                onClick = onClick
            ),
        color = Color.Cyan.copy(alpha = 0.2f)
    ) {
        val imageAspectRatio = 0.7f
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .aspectRatio(imageAspectRatio),
                contentAlignment = Alignment.Center
            ) {
                var imageLoadResult by remember {
                    mutableStateOf<Result<Painter>?>(null)
                }
                val painter = rememberAsyncImagePainter(
                    model = book.imageUrl,
                    onSuccess = {
                        imageLoadResult =
                            if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                                Result.success(it.painter)
                            } else {
                                Result.failure(IllegalArgumentException("Invalid image size"))
                            }
                    },
                    onError = {
                        it.result.throwable.printStackTrace()
                        imageLoadResult = Result.failure(it.result.throwable)
                    }
                )

                val painterState by painter.state.collectAsStateWithLifecycle()
                val transition by animateFloatAsState(
                    targetValue = if (painterState is AsyncImagePainter.State.Success) 1f else 0f,
                    animationSpec = tween(durationMillis = 1000)
                )

                when (val result = imageLoadResult) {
                    null -> PulseAnimation(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )

                    else -> {
                        Image(
                            painter = if (result.isSuccess) painter else painterResource(resource = Res.drawable.book),
                            contentDescription = stringResource(
                                resource = Res.string.book_image_content_description,
                                formatArgs = book.title?.let { arrayOf(it) } ?: arrayOf()
                            ),
                            contentScale = if (result.isSuccess) ContentScale.Crop else ContentScale.Fit,
                            modifier = Modifier
                                .aspectRatio(
                                    ratio = imageAspectRatio,
                                    matchHeightConstraintsFirst = true
                                )
                                .graphicsLayer {
                                    rotationX = (1f - transition) * 30f
                                    scaleX = 0.8f + (0.2f * transition)
                                    scaleY = 0.8f + (0.2f * transition)
                                }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight() //This does not fill the whole screen height because the parent (Row) has intrinsic min height.
                    .weight(1f)
            ) {
                Text(
                    text = book.title ?: "",
                    maxLines = 2,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                book.authors?.let { authors ->
                    Text(
                        text = authors.joinToString(", "),
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyLarge,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                book.averageRating?.let { averageRating ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${(round(averageRating) * 10) / 10.0}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = SandYellow
                        )
                        book.ratingsCount?.let { ratingsCount ->
                            Text(
                                text = "($ratingsCount)",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = stringResource(
                        resource = Res.string.open_book_detail_arrow_content_description
                    ),
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}