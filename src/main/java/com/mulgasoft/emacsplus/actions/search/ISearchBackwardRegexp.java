package com.mulgasoft.emacsplus.actions.search;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ISearchBackwardRegexp extends ISearchBackward {
  protected ISearchBackwardRegexp() {
    super(new Handler());
  }

  public static class Handler extends EditorActionHandler {
    @Override
    protected void doExecute(@NotNull Editor editor, @Nullable Caret caret, DataContext dataContext) {
      ISearch searcher = ISearch.from(editor);
      if (searcher != null) {
        searcher.getFindModel().setRegularExpressions(true);
      }

    }
  }
}
