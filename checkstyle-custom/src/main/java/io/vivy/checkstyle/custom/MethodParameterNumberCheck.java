package io.vivy.checkstyle.custom;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

/**
 * This check ensures that arguments of a method or constructor if more than three should start on a new line
 * and each on a separate line different from the definition.
 */
public class MethodParameterNumberCheck extends AbstractCheck {

    private static final String MSG_KEY = "Your Parameters should be one per line ";

    private static final String PARAMETERS_MSG_KEY = "Parameters listing should start on a new line when they are three or more ";

    private static final String RIGHT_PAREN_PARAMETER_MSG_KEY = "Closing parenthesis for parameter listing should be on a new line ";

    private static final int DEFAULT_MAX_PARAMETERS = 3;

    @Override
    public int[] getDefaultTokens() {
        return getAcceptableTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[] {TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF, TokenTypes.CTOR_CALL, TokenTypes.METHOD_CALL};
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

            DetailAST params = ast.findFirstToken(TokenTypes.PARAMETERS);
            int count = params.getChildCount(TokenTypes.PARAMETER_DEF);

            if (count >= DEFAULT_MAX_PARAMETERS) {

                DetailAST firstToken = params.findFirstToken(TokenTypes.PARAMETER_DEF);
                DetailAST secondSibling = firstToken.getNextSibling();

                //check if the first left parenthesis is not on the same line with the parameter listing
                if (leftParentToken.getLineNo() == firstToken.getLineNo()) {
                    log(leftParentToken, PARAMETERS_MSG_KEY);
                }

                // check if the parameters are on different list
                while (secondSibling != null) {
                    if (secondSibling.getType() == TokenTypes.PARAMETER_DEF) {
                        if (firstToken.getLineNo() == secondSibling.getLineNo()) {
                            DetailAST name = secondSibling.findFirstToken(TokenTypes.IDENT);
                            log(name, MSG_KEY);
                        }
                    }

                    firstToken = secondSibling;
                    secondSibling = firstToken.getNextSibling();
                }

                //check if the first left parenthesis is not on the same line with the parameter listing
                if (rightParentToken.getLineNo() == firstToken.getLineNo()) {
                    log(rightParentToken, RIGHT_PAREN_PARAMETER_MSG_KEY);
                }
            }
        }

    }

}
