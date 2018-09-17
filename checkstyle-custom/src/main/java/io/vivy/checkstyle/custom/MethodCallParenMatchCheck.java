package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

/**
 * This check ensures that the opening and closing parenthesis of a method or constructor calls have matching indentation.
 */

public class MethodCallParenMatchCheck extends AbstractCheck {

    private static final String MSG_KEY = "Opening and closing parenthesis of a method or constructor calls should have the same"
       + " indentation if on different lines";

    private static final String RETURN_MSG_KEY = "Opening and closing parenthesis of a method or constructor calls should have the same"
       + " indentation with the return keyword since they are on different lines";

    @Override
    public int[] getDefaultTokens() {
        return getAcceptableTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[]{TokenTypes.METHOD_CALL, TokenTypes.CTOR_CALL, TokenTypes.METHOD_DEF};
    }


    @Override
    public int[] getRequiredTokens() {
        return CommonUtil.EMPTY_INT_ARRAY;
    }

    @Override
    public void visitToken(DetailAST ast) {
        if ((ast.getType() == TokenTypes.METHOD_CALL) || (ast.getType() == TokenTypes.CTOR_CALL)) {
            DetailAST leftParenToken = ast.getFirstChild();
            DetailAST rightParenToken = ast.getLastChild();

            if (leftParenToken.getLineNo() != rightParenToken.getLineNo()) {
                int leftParenColumnNo = leftParenToken.getColumnNo();
                String lineText = getLine(leftParenToken.getLineNo() - 1);

                if (lineText.contains("return")) {
                    leftParenColumnNo = lineText.indexOf("return");
                    if (leftParenColumnNo != rightParenToken.getColumnNo()) {
                        log(leftParenToken, RETURN_MSG_KEY);
                    }
                } else if (ast.findFirstToken(TokenTypes.DOT) != null) {
                    DetailAST dotLeftToken = ast.getFirstChild().getFirstChild();
                    DetailAST dotRightToken = ast.getFirstChild().getLastChild();
                    leftParenColumnNo = dotLeftToken.getColumnNo();
                    if (dotLeftToken.getLineNo() == dotRightToken.getLineNo()) {
                        if (leftParenColumnNo != rightParenToken.getColumnNo()) {
                            log(leftParenToken, MSG_KEY);
                        }
                    }
                } else if (leftParenColumnNo != rightParenToken.getColumnNo()) {
                    log(leftParenToken, MSG_KEY);
                }
            }
        }
    }
}
