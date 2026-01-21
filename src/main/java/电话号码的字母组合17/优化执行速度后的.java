package 电话号码的字母组合17;

import java.util.ArrayList;
import java.util.List;


//使用StringBuilder 提升执行速度。
public class 优化执行速度后的 {
    // 映射表放在外面作为静态常量，避免每次调用都重复创建
    private static final String[] MAPPING = new String[]{
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        // 特殊情况处理：如果输入为空，直接返回空列表（原代码会返回包含一个空字符串的列表 [""]）
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // 使用 StringBuilder 进行路径记录
        StringBuilder path = new StringBuilder();
        backtrack(result, digits, path, 0);
        return result;
    }

    private void backtrack(List<String> result, String digits, StringBuilder path, int index) {
        // 终止条件：路径长度等于输入数字长度
        if (index == digits.length()) {
            result.add(path.toString()); // 只有在最后收集结果时才转为 String
            return;
        }

        // 获取当前数字对应的字母字符串
        String letters = MAPPING[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {
            // 1. 做选择：追加字符
            path.append(letters.charAt(i));

            // 2. 递归：进入下一层
            backtrack(result, digits, path, index + 1);

            // 3. 撤销选择（回溯）：删除最后一个字符，恢复现场，供下一次循环使用
            path.deleteCharAt(path.length() - 1);
        }
    }
}