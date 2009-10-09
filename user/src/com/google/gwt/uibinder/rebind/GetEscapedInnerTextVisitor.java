/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.uibinder.rebind;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.uibinder.rebind.XMLElement.Interpreter;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

class GetEscapedInnerTextVisitor implements NodeVisitor {

  /**
   * Gathers a text representation of the children of the given Elem, and stuffs
   * it into the given StringBuffer. Applies the interpreter to each descendant,
   * and uses the writer to report errors.
   */
  public static void getEscapedInnerText(Element elem, StringBuffer buffer,
      Interpreter<String> interpreter, UiBinderWriter writer)
      throws UnableToCompleteException {
    new ChildWalker().accept(elem, new GetEscapedInnerTextVisitor(buffer, interpreter,
        writer));
  }

  protected final StringBuffer buffer;
  protected final Interpreter<String> interpreter;
  protected final UiBinderWriter writer;

  protected GetEscapedInnerTextVisitor(StringBuffer buffer,
      Interpreter<String> interpreter, UiBinderWriter writer) {
    this.buffer = buffer;
    this.interpreter = interpreter;
    this.writer = writer;
  }

  public void visitCData(CDATASection d) {
    // TODO(jgw): write this back just as it came in.
  }

  public void visitElement(Element e) throws UnableToCompleteException {
    String replacement =
        interpreter.interpretElement(new XMLElement(e, writer));

    if (replacement != null) {
      buffer.append(replacement);
    }
  }

  public void visitText(Text t) {
    String escaped =
        UiBinderWriter.escapeText(t.getTextContent(), preserveWhiteSpace(t));
    buffer.append(escaped);
  }

  private boolean preserveWhiteSpace(Text t) {
    Element parent = Node.ELEMENT_NODE == t.getParentNode().getNodeType()
      ? (Element) t.getParentNode() : null;

    boolean preserveWhitespace =
        parent != null && "pre".equals(parent.getTagName());
    // TODO(rjrjr) What about script blocks?
    return preserveWhitespace;
  }
}