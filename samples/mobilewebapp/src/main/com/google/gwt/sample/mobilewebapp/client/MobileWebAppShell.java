/*
 * Copyright 2011 Google Inc.
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
package com.google.gwt.sample.mobilewebapp.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

/**
 * An interface that describes the UI Shell.
 */
public interface MobileWebAppShell extends AcceptsOneWidget, IsWidget {

  /**
   * Check if the task list is included in the shell. If true, a
   * {@link com.google.gwt.sample.mobilewebapp.client.activity.TaskListActivity}
   * will not be started.
   * 
   * @return true if included, false if not
   */
  boolean isTaskListIncluded();

  /**
   * Set the handler to invoke when the add button is pressed. If no handler is
   * specified, the button is hidden.
   * 
   * @param handler the handler to add to the button, or null to hide
   */
  void setAddButtonHandler(ClickHandler handler);
}