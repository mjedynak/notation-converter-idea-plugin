package pl.mjedynak.idea.plugins.notation.action.handler;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import pl.mjedynak.idea.plugins.notation.converter.NotationConverter;

public class ChangeNotationActionHandler extends EditorWriteActionHandler {

    private NotationConverter notationConverter;

    public ChangeNotationActionHandler(NotationConverter notationConverter) {
        this.notationConverter = notationConverter;
    }

    @Override
    public void executeWriteAction(Editor editor, DataContext dataContext) {
        SelectionModel selectionModel = editor.getSelectionModel();
        selectWordIfNoSelection(selectionModel);
        changeText(selectionModel, editor.getDocument());
    }

    private void selectWordIfNoSelection(SelectionModel selectionModel) {
        if (!selectionModel.hasSelection()) {
            selectionModel.selectWordAtCaret(true);
        }
    }

    private void changeText(SelectionModel selectionModel, Document document) {
        String text = selectionModel.getSelectedText();
        if (!text.isEmpty()) {
            int selectionStart = selectionModel.getSelectionStart();
            int selectionEnd = selectionModel.getSelectionEnd();
            if (startsWithUpperCase(text)) {
                document.replaceString(selectionStart, selectionEnd, notationConverter.convertToCamelCase(text));
            } else {
                document.replaceString(selectionStart, selectionEnd, notationConverter.convertToUnderscoreUpperCase(text));
            }
        }
    }

    private boolean startsWithUpperCase(String text) {
        return Character.isUpperCase(text.charAt(0));
    }

}