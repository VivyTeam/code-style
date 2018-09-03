package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

/*
* This check ensures method and constructor definitions stays on the same line if number of arguments is not up to three */


public class MethodParametersOnSameLineCheck extends AbstractCheck {

    private static final String MSG_KEY = "Number of arguments is not more than three, definitions and parameters should stay on the same line";

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

            if (count <= max) {
                //check if the first left parenthesis is not on the same line with the parameter listing
                if (leftParentToken.getLineNo() != rightParentToken.getLineNo()) {
                    log(leftParentToken, MSG_KEY);
                }


            }
        }

    }


}
