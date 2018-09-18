package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

/**
 * This checks ensures that every lambda operation with single or no argument starts on the same line as its call.
 * The comment approach uses argument(s) counts but the present checkstyle API throws null pointer
 * if the argument is not surrounded by ().
 * The approach assumes that the EXPR will be the second child of a lambda token only if there is only one
 * argument else this check will ignore.
 */
public class SingleArgsLambdaSameLineCheck extends AbstractCheck {

    private static final String MSG_KEY = "Single Argument Lambda expression should start on the same line, No line break immediately"
        + " after the lambda sign";

    @Override
    public int[] getDefaultTokens() {
        return getRequiredTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return getRequiredTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[]{
            TokenTypes.LAMBDA
        };
    }

    @Override
    public void visitToken(DetailAST ast) {
        if (ast.getType() == TokenTypes.LAMBDA) {

            DetailAST firstChild = ast.getFirstChild();
            DetailAST secondChild = firstChild.getNextSibling();

            if ((secondChild != null) && (secondChild.getText() == "EXPR")) {
                int columnNo = ast.getColumnNo();
                int lineNo = ast.getLineNo();
                String currentLine = getLine(lineNo - 1);
                if (CommonUtil.isBlank(currentLine.substring(columnNo + 2))) {
                    log(ast, MSG_KEY);
                }
            }

        }

    }
}
