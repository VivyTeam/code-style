package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

/*
* This checks ensures that every lambda operation with single or no argument starts on the same line as its call
* */

public class SingleArgsLambdaSameLineCheck extends AbstractCheck {

    private static final String MSG_KEY = "Single Argument Lambda Call should start on the same line. ";

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
        return new int[] {
           TokenTypes.LAMBDA
        };
    }

    @Override
    public void visitToken(DetailAST ast) {
        if (ast.getType() == TokenTypes.LAMBDA) {
            DetailAST arguments = ast.findFirstToken(TokenTypes.PARAMETERS);
            final int count = arguments.getChildCount(TokenTypes.PARAMETER_DEF);

            if (count <= 1) {
                int columnNo = ast.getColumnNo();
                final int lineNo = ast.getLineNo();
                final String currentLine = getLine(lineNo - 1);

                if (CommonUtil.isBlank(currentLine.substring(columnNo + 2))) {
                    log(ast, MSG_KEY);
                }


            }
        }


    }
}
