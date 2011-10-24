package pl.mjedynak.idea.plugins.notation.action.handler;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.mjedynak.idea.plugins.notation.converter.NotationConverter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangeNotationActionHandlerTest {

    private static final String CAMEL_CASE_TEXT = "camelCaseText";
    private static final String UNDER_SCORE_TEXT = "UNDER_SCORE_TEXT";
    private static final int SELECTION_START = 0;
    private static final int SELECTION_END = 1;
    private static final String ANY_CONVERSION_RESULT = "result";

    @InjectMocks
    private ChangeNotationActionHandler changeNotationActionHandler;

    @Mock private NotationConverter notationConverter;
    @Mock private DataContext dataContext;
    @Mock private Editor editor;
    @Mock private SelectionModel selectionModel;
    @Mock private Document document;

    @Before
    public void setUp() {
        when(editor.getSelectionModel()).thenReturn(selectionModel);
        when(editor.getDocument()).thenReturn(document);
        when(selectionModel.getSelectionStart()).thenReturn(SELECTION_START);
        when(selectionModel.getSelectionEnd()).thenReturn(SELECTION_END);
    }

    @Test
    public void shouldChangeCamelCaseTextToUnderscore() {
        // given
        when(selectionModel.getSelectedText()).thenReturn(CAMEL_CASE_TEXT);
        when(notationConverter.convertToUnderscoreUpperCase(CAMEL_CASE_TEXT)).thenReturn(ANY_CONVERSION_RESULT);

        // when
        changeNotationActionHandler.executeWriteAction(editor, dataContext);

        // then
        verify(document).replaceString(SELECTION_START, SELECTION_END, ANY_CONVERSION_RESULT);
    }

    @Test
    public void shouldChangeUnderscoreTextToCamelCase() {
        // given
        when(selectionModel.getSelectedText()).thenReturn(UNDER_SCORE_TEXT);
        when(notationConverter.convertToCamelCase(UNDER_SCORE_TEXT)).thenReturn(ANY_CONVERSION_RESULT);

        // when
        changeNotationActionHandler.executeWriteAction(editor, dataContext);

        // then
        verify(document).replaceString(SELECTION_START, SELECTION_END, ANY_CONVERSION_RESULT);
    }

}
