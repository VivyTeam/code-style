package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

/**
 * This check ensures that there is no line break immediately after an assign literal.
 */
public class NoLineBreakAfterAssignCheck extends AbstractCheck {

    private static final String MSG_KEY = "Line break immediately after assign literal is not allowed";

    @Override
    public int[] getDefaultTokens() {
        return getAcceptableTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[]{TokenTypes.ASSIGN, TokenTypes.PLUS_ASSIGN};
    }

    @Override
    public int[] getRequiredTokens() {
        return CommonUtil.EMPTY_INT_ARRAY;
    }

    @Override
    public void visitToken(DetailAST ast) {

        if (ast.getType() == TokenTypes.ASSIGN || ast.getType() == TokenTypes.PLUS_ASSIGN) {
            String text = ast.getText();
            int colNo = ast.getColumnNo();
            int lineNo = ast.getLineNo();
            String currentLine = getLine(lineNo - 1);

            if (CommonUtil.isBlank(currentLine.substring(colNo + 1))) {
                log(ast, MSG_KEY, text);
            }

        }

    }

}
