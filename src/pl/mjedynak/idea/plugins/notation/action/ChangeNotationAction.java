package pl.mjedynak.idea.plugins.notation.action;

import com.intellij.openapi.editor.actions.TextComponentEditorAction;
import pl.mjedynak.idea.plugins.notation.action.handler.ChangeNotationActionHandler;
import pl.mjedynak.idea.plugins.notation.converter.impl.NotationConverterImpl;

public class ChangeNotationAction extends TextComponentEditorAction {

    public ChangeNotationAction() {
        super(new ChangeNotationActionHandler(new NotationConverterImpl()));
    }
}

