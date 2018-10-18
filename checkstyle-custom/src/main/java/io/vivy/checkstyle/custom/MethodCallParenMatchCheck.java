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

    private static final String INDENT_MSG_KEY = "Ensure that the line of the closing parenthesis line has the same indentation"
            + " as that of the opening parenthesis, especially for chained method calls";

    private static final String RETURN_MSG_KEY = "Opening and closing parenthesis of a method or constructor calls should have the same"
            + " indentation with the return keyword since they are on different lines";

    private static final String INNER_METHOD_CALL_MSK_KEY = "Closing parenthesis of a method call inside another method call but starts on a "
            + "new line must match the indentation of the line of the opening parenthesis";


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

            int rightCurlyParen = 0;
            String rightParenText = getLine(rightParenToken.getLineNo() - 1);

//            System.out.println("Left Token Column: " + leftParenToken.getColumnNo() + "Left Line : "+ getLine(leftParenToken.getLineNo() - 1));
//            System.out.println("Right Token Column: " + rightParenToken.getColumnNo());
//
            int leftIndent = getLineStart(getLine(leftParenToken.getLineNo() - 1));
            int rightIndent = getLineStart(getLine(rightParenToken.getLineNo() - 1));

            if (leftParenToken.getLineNo() != rightParenToken.getLineNo()) {
                int leftParenColumnNo = leftParenToken.getColumnNo();
                String lineText = getLine(leftParenToken.getLineNo() - 1);


                if (lineText.contains("return")) {
                    leftParenColumnNo = lineText.indexOf("return");
                    if ((rightParenText.contains("))")) || (rightParenText.contains(");")) || (rightParenText.contains("})"))) {
                        //ignore if not the terminating end
                        if (rightParenText.trim().charAt(rightParenText.trim().length() - 1) != ';') {
                            return;
                        }

                        if (leftIndent != rightIndent) {
                            log(leftParenToken, INDENT_MSG_KEY);
                        }
                    } else
                        if (leftParenColumnNo != (rightParenToken.getColumnNo() + rightCurlyParen)) {
                            log(leftParenToken, RETURN_MSG_KEY);
                        }
                } else if ((rightParenText.contains("))")) || (rightParenText.contains(");")) || (rightParenText.contains("})"))) {
                    //ignore if not the terminating end
                    if (rightParenText.trim().charAt(rightParenText.trim().length() - 1) != ';') {
                        return;
                    }
                    if (leftIndent != rightIndent) {
                        log(leftParenToken, INDENT_MSG_KEY);
                    }
                } else if (ast.findFirstToken(TokenTypes.DOT) != null) {
                    DetailAST dotLeftToken = ast.getFirstChild().getFirstChild();
                    DetailAST dotRightToken = ast.getFirstChild().getLastChild();
                    leftParenColumnNo = dotLeftToken.getColumnNo();

                    if (dotLeftToken.getLineNo() == dotRightToken.getLineNo()) {
                        if (dotLeftToken.getColumnNo() != leftIndent) {
                            leftParenColumnNo = leftIndent;
                            if (leftParenColumnNo != rightParenToken.getColumnNo()) {
                                log(leftParenToken, INNER_METHOD_CALL_MSK_KEY);
                            }
                        } else if (leftParenColumnNo != (rightParenToken.getColumnNo() + rightCurlyParen)) {
                            log(leftParenToken, MSG_KEY);
                        }
                    }
                } else if (leftParenColumnNo != (rightParenToken.getColumnNo() + rightCurlyParen)) {
                    log(leftParenToken, MSG_KEY);
                }
            }
        }
    }

    private int getLineStart(String line) {
        int index = 0;
        while (Character.isWhitespace(line.charAt(index))) {
            index++;
        }
        return CommonUtil.lengthExpandedTabs(line, index, getTabWidth());
    }

}
