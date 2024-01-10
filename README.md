# objects-equals-inspect
防止Objects.equals(a,b)方法误传参 , prevent error params type by mistake when use Objects.equals(a,b) method

请注意：
1. 本插件是针对 语言：Java  IDE:idea 的Objects.equals方法做检查，
主要解决的痛点：日常开发中 例如我们使用Objects.equals去比较 status(入参)，statusEnum(枚举), 很容易忘记statusEnum.getCode() 或 statusEnum.getVaule()
再比如 我们比较一个订单code时 orderCode(入参),orderDTO(其它业务对象) 很容易忘记orderDTO.getCode() 
因为Objects.equals()两个参数都是Object类型，idea默认不会提示告警, 本插件即为解决该问题 会通过报红的形式提醒开发者

2. 本插件只是检查插件，即使报红，也不会影响代码运行 旨在提醒 并不会修改您的任何代码，可以理解为一个样式

3. 本插件为解决的是日常开发使用中的问题，如果您在编写common通用类或框架时，使用到Objects.equals方法，针对未知类型的比较 可能出现报红问题（同样不会影响您的代码运行）

4. 打开项目方式：下载本项目 用idea打开，ctrl+alt+shift+s -> Modules -> 点击 + 号， 选择New Module ，路径选择该项目覆盖即可, 更详细教程请查看作者csdn博客：
   https://blog.csdn.net/qq_36268103/article/details/135478882 ,文章结尾有打开教程