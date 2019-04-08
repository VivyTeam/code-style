package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

/**
 * This check ensures that no blank line(s) follows method definition.
 */
public class EmptyLineAfterMethodDefinitionCheck extends AbstractCheck {

    private static final String MSG_KEY = "No Blank Line is allowed immediately after method or constructor definition";

    @Override
    public int[] getDefaultTokens() {
        return getAcceptableTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[] {TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF};
    }

    @Override
    public int[] getRequiredTokens() {
        return CommonUtil.EMPTY_INT_ARRAY;
    }

    @Override
    public void visitToken(DetailAST ast) {
        DetailAST statementStart = ast.findFirstToken(TokenTypes.SLIST);
        if (statementStart != null) {
            DetailAST curlyToken = statementStart.findFirstToken(TokenTypes.RCURLY);
            if (ast.getType() == TokenTypes.METHOD_DEF || ast.getType() == TokenTypes.CTOR_DEF) {
                int nextLine = statementStart.getLine();
                if (nextLine <= curlyToken.getLineNo()) {
                    String getNextLine = getLine(statementStart.getLineNo());
                    if (CommonUtil.isBlank(getNextLine.trim())) {
                        log(statementStart, MSG_KEY);
                    }
                }
            }
        }

    }
}
