package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

/*
* This check ensures method and constructor definitions stays on the same line if number of arguments is not up to three */


public class MethodParametersOnSameLineCheck extends AbstractCheck {

    private static final String MSG_KEY = "Number of arguments is not more than 3, definitions and parameters should stay on the same line";

    private static final int DEFAULT_MAX_PARAMETERS = 3;

    private int max = DEFAULT_MAX_PARAMETERS;

    @Override
    public int[] getDefaultTokens() {
        return getAcceptableTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[]{TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF, TokenTypes.METHOD_CALL, TokenTypes.METHOD_CALL};
    }

    @Override
    public int[] getRequiredTokens() {
        return CommonUtil.EMPTY_INT_ARRAY;
    }

    @Override
    public void visitToken(DetailAST ast) {
        DetailAST leftParentToken = ast.findFirstToken(TokenTypes.LPAREN);
        DetailAST rightParentToken = ast.findFirstToken(TokenTypes.RPAREN);

        if (ast.getType() == TokenTypes.METHOD_DEF || ast.getType() == TokenTypes.CTOR_DEF) {

            final DetailAST params = ast.findFirstToken(TokenTypes.PARAMETERS);
            final int count = params.getChildCount(TokenTypes.PARAMETER_DEF);

            if (count < max) {
                int methodDefLine = leftParentToken.getLineNo() - 1;
                int closingParenLineNo = rightParentToken.getLineNo() - 1;
                int currentLineNo = methodDefLine;
                int totalLength = 0;

                while (currentLineNo <= closingParenLineNo) {
                    final String currentLine = getLine(currentLineNo); // -1
                    System.out.println("Line No: " + currentLineNo + " Line Text: " + currentLine.trim());
                    int currentLineLength = currentLine.trim().length();

                    totalLength = totalLength + currentLineLength;
                    currentLineNo = currentLineNo + 1;

                }

                if ((leftParentToken.getLineNo() != rightParentToken.getLineNo()) && (totalLength <= 160)) {
                    log(leftParentToken, MSG_KEY);

                }
            }
        }

    }

}
