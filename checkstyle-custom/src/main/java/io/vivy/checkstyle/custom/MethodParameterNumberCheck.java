package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;


public class MethodParameterNumberCheck extends AbstractCheck {

    private static final String MSG_KEY = "Your Parameters should be one per line ";

    private static final String LEFT_PAREN_MSG_KEY = "Parameters listing should start on a new line ";

    private static final String RIGHT_PAREN_MSG_KEY = "Closing parenthesis for parameter listing should be on a new line ";

    private static final int DEFAULT_MAX_PARAMETERS = 3;

    private int max = DEFAULT_MAX_PARAMETERS;


    private void setMax(int max) {
        this.max = max;
    }

    @Override
    public int[] getDefaultTokens() {
        return getAcceptableTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[]{TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF};
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }

    @Override
    public void visitToken(DetailAST ast) {

        DetailAST leftParentToken = ast.findFirstToken(TokenTypes.LPAREN);
        DetailAST rightParentToken = ast.findFirstToken(TokenTypes.RPAREN);

        if (ast.getType() == TokenTypes.METHOD_DEF || ast.getType() == TokenTypes.CTOR_DEF) {

            final DetailAST params = ast.findFirstToken(TokenTypes.PARAMETERS);
            final int count = params.getChildCount(TokenTypes.PARAMETER_DEF);

            if (count > max) {

                DetailAST firstToken = params.findFirstToken(TokenTypes.PARAMETER_DEF);
                DetailAST secondSibling = firstToken.getNextSibling();

                //check if the first left parenthesis is not on the same line with the parameter listing
                if (leftParentToken.getLineNo() == firstToken.getLineNo()) {
                    log(leftParentToken, LEFT_PAREN_MSG_KEY);
                }

                // check if the parameters are on different list
                while (secondSibling != null) {
                    if (secondSibling.getType() == TokenTypes.PARAMETER_DEF) {
                        if (firstToken.getLineNo() == secondSibling.getLineNo()) {
                            final DetailAST name = secondSibling.findFirstToken(TokenTypes.IDENT);
                            log(name, MSG_KEY);
                        }
                    }

                    firstToken = secondSibling;
                    secondSibling = firstToken.getNextSibling();

                }

                //check if the first left parenthesis is not on the same line with the parameter listing
                if (rightParentToken.getLineNo() == firstToken.getLineNo()) {

                    log(rightParentToken, RIGHT_PAREN_MSG_KEY);
                }
            }
        }

    }


}
