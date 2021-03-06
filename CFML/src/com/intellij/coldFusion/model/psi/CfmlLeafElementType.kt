/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.coldFusion.model.psi

import com.intellij.coldFusion.model.psi.impl.CfmlTagImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.LiteralTextEscaper
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.tree.ILeafElementType

/**
 * @author Sergey Karashevich
 */
class CfmlLeafElementType(s: String) : CfmlElementType(s), ILeafElementType {

  override fun createLeafNode(leafText: CharSequence): ASTNode {
    return CfmlLeafPsiElement(this, leafText)
  }
}

class CfmlLeafPsiElement(cfmlLeafElementType: CfmlLeafElementType, leafText: CharSequence) : LeafPsiElement(cfmlLeafElementType, leafText), PsiLanguageInjectionHost {

  override fun isValidHost() = (this.parent is CfmlTagImpl && (this.parent as CfmlTagImpl).name.toLowerCase() == "cfquery")

  override fun updateText(text: String) = replaceWithText(text) as PsiLanguageInjectionHost

  override fun createLiteralTextEscaper(): LiteralTextEscaper<out PsiLanguageInjectionHost> = LiteralTextEscaper.createSimple(this)

}
