package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

/**
 * This Check ensures that Literal If is structured accordingly.
 */

public class IfLiteralCustomCheck extends AbstractCheck {

    private static final String MSG_KEY = "Opening and closing parenthesis of a wrapped IF literal must match "
        + "and each condition must be on a single line if more than 3 \n "
        + "Also, the condition expression must start on a new line";

    @Override
    public int[] getDefaultTokens() {
        return getAcceptableTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[] {TokenTypes.LITERAL_IF};
    }

    @Override
    public int[] getRequiredTokens() {
        return CommonUtil.EMPTY_INT_ARRAY;
    }

    @Override
    public void visitToken(DetailAST ast) {
        if (ast.getType() == TokenTypes.LITERAL_IF) {

            DetailAST firstExpression = ast.findFirstToken(TokenTypes.EXPR);
            int ifLineNo = ast.getLineNo();

//            if (ifLineNo == firstExpression.getLineNo()) {
//                log(firstExpression, MSG_KEY);
//
//            }

            DetailAST leftParenToken = ast.findFirstToken(TokenTypes.LPAREN);
            DetailAST rightParenToken = ast.findFirstToken(TokenTypes.RPAREN);

//            System.out.println("Left Paren Line: " + leftParenToken.getLineNo());
//            System.out.println("Right Paren Line: " + rightParenToken.getLineNo());
            DetailAST exprChild = ast.getFirstChild().getNextSibling();

            System.out.println("EXPR Details: " + exprChild.findFirstToken(TokenTypes.IDENT));

//            if (leftParentToken.getLineNo() != rightParentToken.getLineNo()) {
//                if (ast.getColumnNo() != rightParentToken.getColumnNo()) {
//                    log(firstExpression, MSG_KEY);
//                }
//            }
        }

    }
}
