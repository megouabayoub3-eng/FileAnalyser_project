@file:OptIn(InternalResourceApi::class)

package dac_project.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/dac_project.composeapp.generated.resources/"

internal val Res.drawable.app_logo: DrawableResource by lazy {
      DrawableResource("drawable:app_logo", setOf(
        ResourceItem(setOf(), "${MD}drawable/app_logo.png", -1, -1),
      ))
    }

internal val Res.drawable.compose_multiplatform: DrawableResource by lazy {
      DrawableResource("drawable:compose_multiplatform", setOf(
        ResourceItem(setOf(), "${MD}drawable/compose-multiplatform.xml", -1, -1),
      ))
    }

internal val Res.drawable.`file`: DrawableResource by lazy {
      DrawableResource("drawable:file", setOf(
        ResourceItem(setOf(), "${MD}drawable/file.svg", -1, -1),
      ))
    }

internal val Res.drawable.folder: DrawableResource by lazy {
      DrawableResource("drawable:folder", setOf(
        ResourceItem(setOf(), "${MD}drawable/folder.svg", -1, -1),
      ))
    }

internal val Res.drawable.upload: DrawableResource by lazy {
      DrawableResource("drawable:upload", setOf(
        ResourceItem(setOf(), "${MD}drawable/upload.svg", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectJvmMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("app_logo", Res.drawable.app_logo)
  map.put("compose_multiplatform", Res.drawable.compose_multiplatform)
  map.put("file", Res.drawable.`file`)
  map.put("folder", Res.drawable.folder)
  map.put("upload", Res.drawable.upload)
}
