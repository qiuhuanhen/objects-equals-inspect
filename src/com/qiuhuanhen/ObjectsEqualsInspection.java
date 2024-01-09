package com.qiuhuanhen;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;


/**
 * @author qiuhuanhen
 */
public class ObjectsEqualsInspection extends LocalInspectionTool {

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        return new MyVisitor(holder);
    }

    private static class MyVisitor extends JavaElementVisitor {
        private final ProblemsHolder holder;

        public MyVisitor(ProblemsHolder holder) {
            this.holder = holder;
        }

        @Override
        public void visitMethodCallExpression(PsiMethodCallExpression expression) {
            super.visitMethodCallExpression(expression);

            String methodName = expression.getMethodExpression().getReferenceName();


            if ("equals".equals(methodName)) {

                PsiExpressionList argumentList = expression.getArgumentList();
                PsiExpression[] expressions = argumentList.getExpressions();


                if (expressions.length == 2) {
                    PsiType arg1Type = expressions[0].getType();
                    PsiType arg2Type = expressions[1].getType();

                    // 都为空 不做提示  注：即使idea会提示 type不为空 但它完全有可能出现空值，为防止插件报NPE 所以有大量的非空判断
                    if (null == arg1Type && null == arg2Type) {
                        return;
                    }

                    // 其一为空 给出错误提示
                    if (null == arg1Type || null == arg2Type) {
                        holder.registerProblem(expression, "Objects.equals parameters are not of the same type.", ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
                        return;
                    }


                    if (isByte(arg1Type) && isByte(arg2Type)) {
                        return;
                    }
                    if (isShort(arg1Type) && isShort(arg2Type)) {
                        return;
                    }
                    if (isInt(arg1Type) && isInt(arg2Type)) {
                        return;
                    }
                    if (isLong(arg1Type) && isLong(arg2Type)) {
                        return;
                    }
                    if (isFloat(arg1Type) && isFloat(arg2Type)) {
                        return;
                    }
                    if (isDouble(arg1Type) && isDouble(arg2Type)) {
                        return;
                    }
                    if (isChar(arg1Type) && isChar(arg2Type)) {
                        return;
                    }
                    if (isBoolean(arg1Type) && isBoolean(arg2Type)) {
                        return;
                    }


                    if (!arg1Type.equals(arg2Type)) {

                        holder.registerProblem(expression, "Objects.equals parameters are not of the same type.", ProblemHighlightType.GENERIC_ERROR_OR_WARNING);
                    }
                }
            }
        }

        private boolean isInt(PsiType type) {
            PsiPrimitiveType unboxedType = PsiPrimitiveType.getUnboxedType(type);
            if (PsiType.INT.equals(unboxedType)) {
                // 是 int 类型
                return true;
            }

            return PsiType.INT.equals(type) || "java.lang.Integer".equals(type.getCanonicalText());
        }

        private boolean isLong(PsiType type) {
            PsiPrimitiveType unboxedType = PsiPrimitiveType.getUnboxedType(type);
            if (PsiType.LONG.equals(unboxedType)) {
                // 是 long 类型
                return true;
            }

            return PsiType.LONG.equals(type) || "java.lang.Long".equals(type.getCanonicalText());
        }

        private boolean isDouble(PsiType type) {
            PsiPrimitiveType unboxedType = PsiPrimitiveType.getUnboxedType(type);
            if (PsiType.DOUBLE.equals(unboxedType)) {
                return true;
            }

            return PsiType.DOUBLE.equals(type) || "java.lang.Double".equals(type.getCanonicalText());
        }

        private boolean isFloat(PsiType type) {
            PsiPrimitiveType unboxedType = PsiPrimitiveType.getUnboxedType(type);
            if (PsiType.FLOAT.equals(unboxedType)) {
                return true;
            }

            return PsiType.FLOAT.equals(type) || "java.lang.Float".equals(type.getCanonicalText());
        }

        private boolean isBoolean(PsiType type) {
            PsiPrimitiveType unboxedType = PsiPrimitiveType.getUnboxedType(type);
            if (PsiType.BOOLEAN.equals(unboxedType)) {
                return true;
            }

            return PsiType.BOOLEAN.equals(type) || "java.lang.Boolean".equals(type.getCanonicalText());
        }

        private boolean isByte(PsiType type) {
            PsiPrimitiveType unboxedType = PsiPrimitiveType.getUnboxedType(type);
            if (PsiType.BYTE.equals(unboxedType)) {
                return true;
            }

            return PsiType.BYTE.equals(type) || "java.lang.Byte".equals(type.getCanonicalText());
        }

        private boolean isChar(PsiType type) {
            PsiPrimitiveType unboxedType = PsiPrimitiveType.getUnboxedType(type);
            if (PsiType.CHAR.equals(unboxedType)) {
                return true;
            }

            return PsiType.CHAR.equals(type) || "java.lang.Char".equals(type.getCanonicalText());
        }

        private boolean isShort(PsiType type) {
            PsiPrimitiveType unboxedType = PsiPrimitiveType.getUnboxedType(type);
            if (PsiType.SHORT.equals(unboxedType)) {
                return true;
            }

            return PsiType.SHORT.equals(type) || "java.lang.Short".equals(type.getCanonicalText());
        }

    }
}
