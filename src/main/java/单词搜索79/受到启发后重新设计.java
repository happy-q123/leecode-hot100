package 单词搜索79;

public class 受到启发后重新设计 {
    public static void main(String[] args) {
        受到启发后重新设计 x=new 受到启发后重新设计();
        char[][] d=new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        String s="SEE";
        String s="ABCCED";
        boolean lo=x.exist(d,s);
        System.out.println(lo);
    }
    /*
        取行数 (Rows)
        int m = board.length;

        获取列数 (Cols)
        前提：数组不为空，且是一个标准的矩阵（每行长度一致）
        int n = board[0].length;
    * */
    public boolean exist(char[][] board, String word) {
        // 遍历每一个格子作为起点
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 如果从 (i, j) 出发找到了单词，直接返回 true
                if (compute(0, i, j, board, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    //当前层应该去匹配word中下标为index的字符、当前行号、当前列号、原始的字符矩阵、待匹配的字符word
    public boolean compute(int index,int hang,int lie,char[][] board,String word){
        //本层待匹配的字符
        char destination=word.charAt(index);
        int next_index=index;

        if(destination==board[hang][lie]){
            next_index=next_index+1;
        }else
            return false;

        //最后一个index，直接返回，不在往下递归
        if(index==word.length()-1){
            return true;
        }

        char org=board[hang][lie];
        board[hang][lie]='1';
        boolean next_result=false;

        if(hang+1<board.length&&board[hang+1][lie]!='1'){
            boolean linshi=compute(next_index,hang+1,lie,board,word);
            next_result=linshi||next_result;

        }

        if(hang-1>=0&&board[hang-1][lie]!='1'){
            boolean linshi=compute(next_index,hang-1,lie,board,word);
            next_result=linshi||next_result;

        }
        //board[0].length表示列数、board.length表示行数
        if(lie+1<board[0].length&&board[hang][lie+1]!='1'){
            boolean linshi=compute(next_index,hang,lie+1,board,word);
            next_result=linshi||next_result;
        }

        if(lie-1>=0&&board[hang][lie-1]!='1'){
            boolean linshi=compute(next_index,hang,lie-1,board,word);
            next_result=linshi||next_result;
        }
        board[hang][lie]=org;
        return next_result;
    }


}
