package com.mulgasoft.emacsplus.actions.search;

import com.intellij.find.FindUtil;
import com.intellij.ide.actions.SearchBackAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbAware;
import com.mulgasoft.emacsplus.util.ActionUtil;
import com.mulgasoft.emacsplus.util.EmacsIds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class ISearchBackward extends EditorAction implements DumbAware {
  public static class Handler extends EditorActionHandler {
    protected void doExecute(@NotNull Editor editor, @Nullable Caret caret, DataContext dataContext) {
      ISearch searcher = ISearch.from(editor);
      if (searcher == null) {
        ActionUtil.dispatch(EmacsIds.ISEARCH_ID, dataContext);
        searcher = ISearch.from(editor);
      } else {
        searcher.requestFocus();
        FindUtil.configureFindModel(false, editor, searcher.getFindModel(), false);
      }

      if (searcher != null) {
        searcher.searchBackward();
      }

    }


    @Override
    protected boolean isEnabledForCaret(@NotNull Editor editor, @NotNull Caret caret, DataContext dataContext) {
      return !editor.isOneLineMode();
    }

  }

  public ISearchBackward() {
    this(new Handler());
  }

  protected ISearchBackward(EditorActionHandler defaultHandler) {
    super(defaultHandler);
    setEnabledInModalContext(true);
  }

  protected static ISearch delegateAction(AnActionEvent e) {
    Editor editor = FileEditorManager.getInstance(e.getProject()).getSelectedTextEditor();
    ISearch searcher = ISearch.from(editor);

    return searcher;
  }
}
