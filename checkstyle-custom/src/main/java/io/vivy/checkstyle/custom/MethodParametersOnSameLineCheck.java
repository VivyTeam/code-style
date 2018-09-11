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

        int totalLength = 0;

        if (ast.getType() == TokenTypes.METHOD_DEF || ast.getType() == TokenTypes.CTOR_DEF) {

            final DetailAST params = ast.findFirstToken(TokenTypes.PARAMETERS);
            final int count = params.getChildCount(TokenTypes.PARAMETER_DEF);

            DetailAST methodName = ast.findFirstToken(TokenTypes.IDENT);
            int methodNameLength = methodName.getText().length();

            totalLength = totalLength + methodNameLength;

            DetailAST returnType = ast.getFirstChild().getNextSibling();
            if (returnType.getFirstChild() != null) {
                int returnTypeLength = returnType.getFirstChild().getText().length();
                totalLength = totalLength + returnTypeLength;
            }

            if (ast.getFirstChild().getFirstChild() != null) {
                totalLength = totalLength + getModifiersLength(ast.getFirstChild());
            }

            if (params.getFirstChild() != null) {
                totalLength = totalLength + getParametersLength(params.getFirstChild());
            }
            if ((count < max) && ((totalLength + 2) <= 185)) {
                //check if the first left parenthesis is not on the same line with the parameter listing
                if (leftParentToken.getLineNo() != rightParentToken.getLineNo()) {
                    log(leftParentToken, MSG_KEY);
                }

            }
        }

    }

    public int getParametersLength(DetailAST ast) {
        int parametersLength = 0;
        int parametersCount = 0;

        DetailAST firstChild = ast.getFirstChild().getNextSibling();
        parametersLength = firstChild.getText().length();

        while (firstChild.getNextSibling() != null) {
            DetailAST nextChild = firstChild.getNextSibling();
            parametersLength = parametersLength + nextChild.getText().length();
            firstChild = nextChild;
            parametersCount = parametersCount + 1;
        }

        return (parametersLength + parametersCount);
    }

    public int getModifiersLength(DetailAST modifier) {
        int modifierLength = 0;
        int modifierCount = 0;

        DetailAST firstModifier = modifier.getFirstChild();
        modifierLength = modifierLength + firstModifier.getText().length();
        modifierCount = modifierCount + 1;

        while (firstModifier.getNextSibling() != null) {
            DetailAST nextSibling = firstModifier.getNextSibling();
            modifierCount = modifierCount + 1;
            modifierLength = modifierLength + nextSibling.getText().length();
            firstModifier = nextSibling;

        }
        return  modifierLength + modifierCount;
    }

}
